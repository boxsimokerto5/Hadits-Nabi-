package com.example

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.HadithDatabase
import com.example.data.HadithEntity
import com.example.data.HadithRemoteApi
import com.example.data.HadithRepository
import com.example.notification.DailyHadithNotificationHelper
import com.example.ui.AppTab
import com.example.ui.HadithViewModel
import com.example.ui.components.DailyNotificationSettingsSheet
import com.example.ui.components.HadithDetailSheet
import com.example.ui.components.ImamBioBottomSheet
import com.example.ui.components.NarratorBioBottomSheet
import com.example.ui.components.OnlineHadithLookupDialog
import com.example.ui.screens.BerandaTabContent
import com.example.ui.screens.FavoritDanCatatanTabContent
import com.example.ui.screens.KitabDanPerawiTabContent
import com.example.ui.screens.Sunnah24JamTabContent
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        DailyHadithNotificationHelper.createNotificationChannel(applicationContext)

        val database = HadithDatabase.getInstance(applicationContext)
        val remoteApi = HadithRemoteApi.create()
        val repository = HadithRepository(database.hadithDao(), remoteApi)
        val factory = HadithViewModel.Factory(repository)
        val initialHadithIdFromNotification = intent?.getStringExtra(DailyHadithNotificationHelper.EXTRA_HADITH_ID)

        setContent {
            val viewModel: HadithViewModel = viewModel(factory = factory)
            val isDarkOverride by viewModel.isDarkModeOverride.collectAsStateWithLifecycle()
            val systemDark = isSystemInDarkTheme()
            val useDarkTheme = isDarkOverride ?: systemDark

            MyApplicationTheme(darkTheme = useDarkTheme) {
                HadithEncyclopediaApp(
                    viewModel = viewModel,
                    isDarkTheme = useDarkTheme,
                    initialHadithIdFromNotification = initialHadithIdFromNotification
                )
            }
        }
    }
}

@Composable
fun HadithEncyclopediaApp(
    viewModel: HadithViewModel,
    isDarkTheme: Boolean,
    initialHadithIdFromNotification: String? = null
) {
    val context = LocalContext.current
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedGrade by viewModel.selectedGrade.collectAsStateWithLifecycle()
    val selectedTheme by viewModel.selectedTheme.collectAsStateWithLifecycle()
    val selectedImamId by viewModel.selectedImamId.collectAsStateWithLifecycle()
    val selectedNarratorId by viewModel.selectedNarratorId.collectAsStateWithLifecycle()
    val selectedBookmarkFolder by viewModel.selectedBookmarkFolder.collectAsStateWithLifecycle()

    val allHadiths by viewModel.allHadiths.collectAsStateWithLifecycle()
    val filteredHadiths by viewModel.filteredHadiths.collectAsStateWithLifecycle()
    val bookmarkedHadiths by viewModel.bookmarkedHadiths.collectAsStateWithLifecycle()
    val sunnahGuides by viewModel.sunnahGuides.collectAsStateWithLifecycle()

    val arabicFontSizeSp by viewModel.arabicFontSizeSp.collectAsStateWithLifecycle()
    val showLatin by viewModel.showLatin.collectAsStateWithLifecycle()

    val selectedHadithForDetail by viewModel.selectedHadithForDetail.collectAsStateWithLifecycle()
    val selectedImamBio by viewModel.selectedImamBio.collectAsStateWithLifecycle()
    val selectedNarratorBio by viewModel.selectedNarratorBio.collectAsStateWithLifecycle()
    val onlineFetchState by viewModel.onlineFetchState.collectAsStateWithLifecycle()

    var showOnlineLookupDialog by remember { mutableStateOf(false) }
    var showNotificationSettingsSheet by remember { mutableStateOf(false) }
    var notificationConfig by remember {
        mutableStateOf(DailyHadithNotificationHelper.getConfig(context))
    }

    val hadithOfTheDay = remember(allHadiths) {
        DailyHadithNotificationHelper.getHadithOfTheDay(allHadiths)
    }

    // Pending action after runtime notification permission is granted
    var pendingSendTestNotification by remember { mutableStateOf(false) }
    var pendingScheduleParams by remember { mutableStateOf<Triple<Boolean, Int, Int>?>(null) }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            pendingScheduleParams?.let { (enabled, hour, minute) ->
                notificationConfig = DailyHadithNotificationHelper.saveAndSchedule(
                    context,
                    enabled,
                    hour,
                    minute
                )
                Toast.makeText(
                    context,
                    "Pengingat 'Hadits Hari Ini' dijadwalkan pukul ${notificationConfig.formattedTime}",
                    Toast.LENGTH_SHORT
                ).show()
                pendingScheduleParams = null
            }
            if (pendingSendTestNotification) {
                val sent = DailyHadithNotificationHelper.sendHadithNotificationNow(context, hadithOfTheDay)
                if (sent) {
                    Toast.makeText(
                        context,
                        "Notifikasi 'Hadits Hari Ini' berhasil dikirim! Cek panel notifikasi HP Anda.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                pendingSendTestNotification = false
            }
        } else {
            pendingScheduleParams = null
            pendingSendTestNotification = false
            Toast.makeText(
                context,
                "Izin notifikasi diperlukan untuk mengirim motivasi 'Hadits Hari Ini'.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun triggerSendNotificationNow(targetHadith: HadithEntity = hadithOfTheDay) {
        if (DailyHadithNotificationHelper.hasNotificationPermission(context)) {
            val sent = DailyHadithNotificationHelper.sendHadithNotificationNow(context, targetHadith)
            if (sent) {
                Toast.makeText(
                    context,
                    "Notifikasi 'Hadits Hari Ini' berhasil dikirim!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pendingSendTestNotification = true
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    fun triggerSaveSchedule(enabled: Boolean, hour: Int, minute: Int) {
        if (!enabled) {
            notificationConfig = DailyHadithNotificationHelper.saveAndSchedule(context, false, hour, minute)
            Toast.makeText(context, "Pengingat harian dinonaktifkan", Toast.LENGTH_SHORT).show()
            return
        }

        if (DailyHadithNotificationHelper.hasNotificationPermission(context)) {
            notificationConfig = DailyHadithNotificationHelper.saveAndSchedule(context, true, hour, minute)
            Toast.makeText(
                context,
                "Pengingat 'Hadits Hari Ini' aktif setiap pukul ${notificationConfig.formattedTime}",
                Toast.LENGTH_SHORT
            ).show()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pendingScheduleParams = Triple(true, hour, minute)
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    // Open Hadith detail automatically if user tapped the local notification
    LaunchedEffect(initialHadithIdFromNotification, allHadiths) {
        if (!initialHadithIdFromNotification.isNullOrBlank() && allHadiths.isNotEmpty()) {
            viewModel.openHadithDetailById(initialHadithIdFromNotification)
        }
    }

    // BackHandler for non-home tabs
    if (currentTab != AppTab.BERANDA) {
        BackHandler {
            viewModel.selectTab(AppTab.BERANDA)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // Set contentWindowInsets to 0 so the KaabaHeaderBanner renders edge-to-edge under the status bar
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                NavigationBarItem(
                    selected = currentTab == AppTab.BERANDA,
                    onClick = { viewModel.selectTab(AppTab.BERANDA) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.BERANDA) Icons.Filled.Home else Icons.Outlined.Home,
                            contentDescription = "Beranda"
                        )
                    },
                    label = {
                        Text(
                            text = "Beranda",
                            fontWeight = if (currentTab == AppTab.BERANDA) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    modifier = Modifier.testTag("nav_tab_beranda")
                )

                NavigationBarItem(
                    selected = currentTab == AppTab.KITAB_PERAWI,
                    onClick = { viewModel.selectTab(AppTab.KITAB_PERAWI) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.KITAB_PERAWI) Icons.Filled.MenuBook else Icons.Outlined.MenuBook,
                            contentDescription = "9 Imam & Perawi"
                        )
                    },
                    label = {
                        Text(
                            text = "9 Imam & Perawi",
                            fontWeight = if (currentTab == AppTab.KITAB_PERAWI) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    modifier = Modifier.testTag("nav_tab_kitab")
                )

                NavigationBarItem(
                    selected = currentTab == AppTab.SUNNAH_HARIAN,
                    onClick = { viewModel.selectTab(AppTab.SUNNAH_HARIAN) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.SUNNAH_HARIAN) Icons.Filled.WbSunny else Icons.Outlined.WbSunny,
                            contentDescription = "Sunnah 24 Jam"
                        )
                    },
                    label = {
                        Text(
                            text = "Sunnah 24 Jam",
                            fontWeight = if (currentTab == AppTab.SUNNAH_HARIAN) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    modifier = Modifier.testTag("nav_tab_sunnah")
                )

                NavigationBarItem(
                    selected = currentTab == AppTab.FAVORIT,
                    onClick = { viewModel.selectTab(AppTab.FAVORIT) },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.FAVORIT) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Koleksi & Catatan"
                        )
                    },
                    label = {
                        Text(
                            text = "Koleksi",
                            fontWeight = if (currentTab == AppTab.FAVORIT) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    modifier = Modifier.testTag("nav_tab_favorit")
                )
            }
        }
    ) { _ ->
        Box(modifier = Modifier.fillMaxSize()) {
            when (currentTab) {
                AppTab.BERANDA -> {
                    BerandaTabContent(
                        hadiths = filteredHadiths,
                        hadithOfTheDay = hadithOfTheDay,
                        notificationConfig = notificationConfig,
                        totalAllHadiths = allHadiths.size,
                        searchQuery = searchQuery,
                        selectedGrade = selectedGrade,
                        selectedTheme = selectedTheme,
                        selectedImamId = selectedImamId,
                        selectedNarratorId = selectedNarratorId,
                        arabicFontSizeSp = arabicFontSizeSp,
                        showLatin = showLatin,
                        isDarkTheme = isDarkTheme,
                        onSearchQueryChange = {
                            viewModel.updateSearchQuery(it)
                        },
                        onSelectGrade = viewModel::selectGradeFilter,
                        onSelectTheme = viewModel::selectThemeFilter,
                        onClearFilters = viewModel::clearAllFilters,
                        onToggleDarkTheme = { viewModel.toggleDarkMode(isDarkTheme) },
                        onOpenOnlineLookup = { showOnlineLookupDialog = true },
                        onOpenNotificationSettings = { showNotificationSettingsSheet = true },
                        onSendTestNotification = { triggerSendNotificationNow(it) },
                        onHadithClick = viewModel::openHadithDetail,
                        onNarratorClick = viewModel::openNarratorBioById,
                        onToggleBookmark = { hadith ->
                            viewModel.toggleBookmark(hadith)
                            val msg = if (!hadith.isBookmarked) "Disimpan ke Koleksi (${hadith.bookmarkFolder})"
                            else "Dihapus dari Koleksi"
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        },
                        onCopyHadith = { copyHadithToClipboard(context, it) },
                        onShareHadith = { shareHadithIntent(context, it) }
                    )
                }

                AppTab.KITAB_PERAWI -> {
                    KitabDanPerawiTabContent(
                        searchQuery = searchQuery,
                        totalCachedHadiths = allHadiths.size,
                        isDarkTheme = isDarkTheme,
                        onSearchQueryChange = {
                            viewModel.updateSearchQuery(it)
                            if (it.isNotBlank()) {
                                viewModel.selectTab(AppTab.BERANDA)
                            }
                        },
                        onToggleDarkTheme = { viewModel.toggleDarkMode(isDarkTheme) },
                        onOpenOnlineLookup = { showOnlineLookupDialog = true },
                        onImamClick = viewModel::openImamBio,
                        onNarratorClick = viewModel::openNarratorBio
                    )
                }

                AppTab.SUNNAH_HARIAN -> {
                    Sunnah24JamTabContent(
                        guides = sunnahGuides,
                        searchQuery = searchQuery,
                        totalCachedHadiths = allHadiths.size,
                        arabicFontSizeSp = arabicFontSizeSp,
                        isDarkTheme = isDarkTheme,
                        onSearchQueryChange = {
                            viewModel.updateSearchQuery(it)
                            if (it.isNotBlank()) {
                                viewModel.selectTab(AppTab.BERANDA)
                            }
                        },
                        onToggleDarkTheme = { viewModel.toggleDarkMode(isDarkTheme) },
                        onOpenOnlineLookup = { showOnlineLookupDialog = true },
                        onTogglePractice = viewModel::toggleSunnahPractice,
                        onOpenRelatedHadith = viewModel::openHadithDetailById
                    )
                }

                AppTab.FAVORIT -> {
                    FavoritDanCatatanTabContent(
                        bookmarkedHadiths = bookmarkedHadiths,
                        selectedFolder = selectedBookmarkFolder,
                        searchQuery = searchQuery,
                        totalCachedHadiths = allHadiths.size,
                        arabicFontSizeSp = arabicFontSizeSp,
                        showLatin = showLatin,
                        isDarkTheme = isDarkTheme,
                        onSearchQueryChange = {
                            viewModel.updateSearchQuery(it)
                            if (it.isNotBlank()) {
                                viewModel.selectTab(AppTab.BERANDA)
                            }
                        },
                        onSelectFolder = viewModel::selectBookmarkFolder,
                        onToggleDarkTheme = { viewModel.toggleDarkMode(isDarkTheme) },
                        onOpenOnlineLookup = { showOnlineLookupDialog = true },
                        onHadithClick = viewModel::openHadithDetail,
                        onNarratorClick = viewModel::openNarratorBioById,
                        onToggleBookmark = { viewModel.toggleBookmark(it) },
                        onCopyHadith = { copyHadithToClipboard(context, it) },
                        onShareHadith = { shareHadithIntent(context, it) }
                    )
                }
            }
        }
    }

    // Modal Bottom Sheet: Daily Notification Schedule & Instant Motivation Trigger
    if (showNotificationSettingsSheet) {
        DailyNotificationSettingsSheet(
            config = notificationConfig,
            hadithOfTheDay = hadithOfTheDay,
            onSaveSchedule = { enabled, hour, minute ->
                triggerSaveSchedule(enabled, hour, minute)
            },
            onSendTestNotificationNow = {
                triggerSendNotificationNow(hadithOfTheDay)
            },
            onDismiss = { showNotificationSettingsSheet = false }
        )
    }

    // Modal Bottom Sheet: Hadith Full Detail, Syarah Fiqih, Reader Controls & Study Notes
    selectedHadithForDetail?.let { detailHadith ->
        HadithDetailSheet(
            hadith = detailHadith,
            arabicFontSizeSp = arabicFontSizeSp,
            showLatin = showLatin,
            onIncreaseFont = { viewModel.updateArabicFontSize(arabicFontSizeSp + 2f) },
            onDecreaseFont = { viewModel.updateArabicFontSize(arabicFontSizeSp - 2f) },
            onToggleLatin = viewModel::toggleShowLatin,
            onSaveToFolder = { folder ->
                viewModel.saveHadithToFolder(detailHadith, folder)
                Toast.makeText(context, "Disimpan ke folder \"$folder\"", Toast.LENGTH_SHORT).show()
            },
            onSaveNote = { note ->
                viewModel.savePersonalNote(detailHadith, note)
            },
            onCopyHadith = { copyHadithToClipboard(context, detailHadith) },
            onShareHadith = { shareHadithIntent(context, detailHadith) },
            onOpenNarratorBio = {
                val narratorId = detailHadith.narratorId
                viewModel.closeHadithDetail()
                viewModel.openNarratorBioById(narratorId)
            },
            onDismiss = viewModel::closeHadithDetail
        )
    }

    // Modal Bottom Sheet: Imam Biography & Kitab Methodology
    selectedImamBio?.let { imam ->
        ImamBioBottomSheet(
            imam = imam,
            onFilterThisBook = {
                viewModel.clearAllFilters()
                viewModel.selectImamFilter(imam.id)
                viewModel.selectTab(AppTab.BERANDA)
            },
            onFetchRangeFromServer = { startNum ->
                viewModel.setOnlineBookSlug(imam.apiSlug)
                viewModel.fetchHadithBatchFromServer(imam.apiSlug, startNum)
                showOnlineLookupDialog = true
            },
            onDismiss = viewModel::closeImamBio
        )
    }

    // Modal Bottom Sheet: Sahabat Narrator Biography
    selectedNarratorBio?.let { narrator ->
        NarratorBioBottomSheet(
            narrator = narrator,
            onFilterThisNarrator = {
                viewModel.clearAllFilters()
                viewModel.selectNarratorFilter(narrator.id)
                viewModel.selectTab(AppTab.BERANDA)
            },
            onDismiss = viewModel::closeNarratorBio
        )
    }

    // Modal Bottom Sheet: Online 9 Imam Server Hadith Number Lookup
    if (showOnlineLookupDialog) {
        OnlineHadithLookupDialog(
            onlineState = onlineFetchState,
            onSelectBookSlug = viewModel::setOnlineBookSlug,
            onFetchSingleNumber = { slug, numStr ->
                viewModel.fetchHadithByNumberFromServer(slug, numStr)
            },
            onFetchBatchRange = { slug, start ->
                viewModel.fetchHadithBatchFromServer(slug, start)
            },
            onDismiss = { showOnlineLookupDialog = false }
        )
    }
}

private fun formatHadithForSharing(hadith: HadithEntity): String {
    return buildString {
        appendLine("📖 *${hadith.title}*")
        appendLine("(${hadith.bookName} No. ${hadith.hadithNumber} • Derajat: ${hadith.gradeEnum.label})")
        appendLine()
        appendLine(hadith.arabicText)
        appendLine()
        if (hadith.latinTransliteration.isNotBlank()) {
            appendLine("_${hadith.latinTransliteration}_")
            appendLine()
        }
        appendLine("Artinya:")
        appendLine("\"${hadith.indonesianTranslation}\"")
        appendLine()
        appendLine("• Perawi: ${hadith.narratorName}")
        appendLine("• Status: ${hadith.gradeScholar}")
        appendLine()
        appendLine("📌 *Intisari & Fiqih Hadits:*")
        appendLine(hadith.syarahFiqih)
        appendLine()
        appendLine("— Dibagikan dari Aplikasi Ensiklopedia Hadits & Sunnah")
    }
}

private fun copyHadithToClipboard(context: Context, hadith: HadithEntity) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Hadits ${hadith.bookName}", formatHadithForSharing(hadith))
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "Hadits berhasil disalin ke papan klip!", Toast.LENGTH_SHORT).show()
}

private fun shareHadithIntent(context: Context, hadith: HadithEntity) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, formatHadithForSharing(hadith))
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Bagikan Hadits melalui")
    context.startActivity(shareIntent)
}
