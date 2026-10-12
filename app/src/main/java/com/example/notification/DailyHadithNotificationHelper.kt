package com.example.notification

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.MainActivity
import com.example.R
import com.example.data.HadithEntity
import com.example.data.HadithGrade
import com.example.data.HadithSeedData
import java.util.Calendar

data class DailyNotificationConfig(
    val isEnabled: Boolean = false,
    val hour: Int = 6,
    val minute: Int = 0
) {
    val formattedTime: String
        get() = String.format("%02d:%02d", hour, minute)
}

object DailyHadithNotificationHelper {
    const val CHANNEL_ID = "daily_hadith_motivation_channel"
    const val CHANNEL_NAME = "Hadits Hari Ini (Motivasi Harian)"
    const val NOTIFICATION_ID = 1001
    const val EXTRA_HADITH_ID = "extra_hadith_id"

    private const val PREFS_NAME = "daily_hadith_notification_prefs"
    private const val KEY_ENABLED = "key_enabled"
    private const val KEY_HOUR = "key_hour"
    private const val KEY_MINUTE = "key_minute"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Menampilkan notifikasi rutin Hadits Hari Ini dan pengingat Sunnah Nabi ﷺ"
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    fun hasNotificationPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    fun getConfig(context: Context): DailyNotificationConfig {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return DailyNotificationConfig(
            isEnabled = prefs.getBoolean(KEY_ENABLED, false),
            hour = prefs.getInt(KEY_HOUR, 6),
            minute = prefs.getInt(KEY_MINUTE, 0)
        )
    }

    fun saveAndSchedule(context: Context, enabled: Boolean, hour: Int, minute: Int): DailyNotificationConfig {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean(KEY_ENABLED, enabled)
            .putInt(KEY_HOUR, hour)
            .putInt(KEY_MINUTE, minute)
            .apply()

        if (enabled) {
            scheduleDailyAlarm(context, hour, minute)
        } else {
            cancelDailyAlarm(context)
        }
        return DailyNotificationConfig(enabled, hour, minute)
    }

    fun scheduleDailyAlarm(context: Context, hour: Int, minute: Int) {
        createNotificationChannel(context)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyHadithReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun cancelDailyAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyHadithReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    /**
     * Picks the authentic "Hadits Hari Ini" based on the current day of the year
     * (filtering only SHAHIH and HASAN hadiths so daily motivation is always authentic).
     */
    fun getHadithOfTheDay(allHadiths: List<HadithEntity> = HadithSeedData.getInitialHadiths()): HadithEntity {
        val authenticList = allHadiths.filter {
            it.gradeEnum == HadithGrade.SHAHIH || it.gradeEnum == HadithGrade.HASAN
        }.ifEmpty { HadithSeedData.getInitialHadiths() }

        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        return authenticList[dayOfYear % authenticList.size]
    }

    fun sendHadithNotificationNow(
        context: Context,
        customHadith: HadithEntity? = null
    ): Boolean {
        createNotificationChannel(context)
        if (!hasNotificationPermission(context)) {
            return false
        }

        val hadith = customHadith ?: getHadithOfTheDay()

        val tapIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_HADITH_ID, hadith.id)
        }
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            hadith.id.hashCode(),
            tapIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val shareText = buildString {
            appendLine("✨ Hadits Hari Ini — ${hadith.title}")
            appendLine()
            appendLine(hadith.arabicText)
            appendLine()
            appendLine("\"${hadith.indonesianTranslation}\"")
            appendLine("(${hadith.bookName} No. ${hadith.hadithNumber} • ${hadith.gradeEnum.label})")
        }
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        val shareChooser = Intent.createChooser(shareIntent, "Bagikan Hadits Hari Ini")
        val sharePendingIntent = PendingIntent.getActivity(
            context,
            hadith.id.hashCode() + 1,
            shareChooser,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("✨ Hadits Hari Ini: ${hadith.title}")
            .setContentText("\"${hadith.indonesianTranslation}\" (${hadith.bookName} No. ${hadith.hadithNumber})")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .setBigContentTitle("✨ Hadits Hari Ini • ${hadith.bookName} No. ${hadith.hadithNumber}")
                    .bigText(
                        "${hadith.arabicText}\n\n\"${hadith.indonesianTranslation}\"\n\n• Perawi: ${hadith.narratorName}\n• Derajat: ${hadith.gradeEnum.label}"
                    )
                    .setSummaryText("Motivasi Sunnah Harian")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)
            .addAction(
                android.R.drawable.ic_menu_share,
                "Bagikan",
                sharePendingIntent
            )
            .build()

        return try {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
            true
        } catch (_: SecurityException) {
            false
        }
    }
}

class DailyHadithReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                val config = DailyHadithNotificationHelper.getConfig(context)
                if (config.isEnabled) {
                    DailyHadithNotificationHelper.scheduleDailyAlarm(context, config.hour, config.minute)
                }
            }
            else -> {
                DailyHadithNotificationHelper.sendHadithNotificationNow(context)
            }
        }
    }
}
