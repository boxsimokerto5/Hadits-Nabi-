package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class HadithGrade(
    val label: String,
    val arabicLabel: String,
    val shortDesc: String
) {
    SHAHIH(
        label = "Shahih",
        arabicLabel = "صحيح",
        shortDesc = "Sanad bersambung, perawi adil & dhabith (kuat hafalan), tidak syadz & tidak ber-'illat. Dapat dijadikan hujjah utama."
    ),
    HASAN(
        label = "Hasan",
        arabicLabel = "حسن",
        shortDesc = "Sanad bersambung dan perawi terpercaya, tingkat kedhabithan sedikit di bawah Shahih namun tetap kuat sebagai dalil amalan."
    ),
    DHAIF(
        label = "Dhaif (Lemah)",
        arabicLabel = "ضعيف",
        shortDesc = "Terdapat kelemahan pada sanad atau perawi. Perlu kehati-hatian dan tidak dijadikan landasan hukum aqidah/syariat utama."
    ),
    MAUDHU(
        label = "Maudhu' (Palsu)",
        arabicLabel = "موضوع",
        shortDesc = "Bukan sabda Nabi ﷺ (riwayat buatan/palsu). Ditampilkan semata-mata untuk edukasi agar umat Islam berhati-hati dan tidak mengamalkannya."
    )
}

@Entity(tableName = "hadiths")
data class HadithEntity(
    @PrimaryKey val id: String,
    val bookId: String,
    val bookName: String,
    val hadithNumber: Int,
    val title: String,
    val arabicText: String,
    val latinTransliteration: String,
    val indonesianTranslation: String,
    val grade: String, // SHAHIH, HASAN, DHAIF, MAUDHU
    val gradeScholar: String, // e.g., "Muttafaqun 'Alaih", "Syaikh Al-Albani", "Imam At-Tirmidzi"
    val themeCategory: String, // Iman & Aqidah, Thaharah, Sholat, Puasa, Zakat & Sedekah, Akhlak & Adab, Muamalah, Keluarga, Zikir & Doa, Ilmu & Sunnah
    val narratorId: String, // e.g., "abu_hurairah", "aisyah", "umar_bin_khattab"
    val narratorName: String,
    val sunnahTimeSlot: String?, // e.g., "Pagi & Bangun Tidur", "Makan & Minum", "Masjid & Sholat", "Sosial & Adab", "Malam & Sebelum Tidur", null
    val syarahFiqih: String, // Detailed explanation & Fawaid (pelajaran)
    val isBookmarked: Boolean = false,
    val bookmarkFolder: String = "Amalan Harian",
    val personalNote: String = "",
    val lastReadTimestamp: Long = 0L
) {
    val gradeEnum: HadithGrade
        get() = try {
            HadithGrade.valueOf(grade)
        } catch (_: Exception) {
            HadithGrade.SHAHIH
        }
}

data class ImamBookInfo(
    val id: String,
    val apiSlug: String,
    val name: String,
    val arabicName: String,
    val authorFullName: String,
    val birthDeathHijri: String,
    val totalHadithsInBook: Int,
    val description: String,
    val methodology: String
)

data class SahabatNarratorInfo(
    val id: String,
    val name: String,
    val arabicName: String,
    val title: String,
    val totalNarratedCount: Int,
    val wafatYear: String,
    val bioSummary: String,
    val virtues: String
)

data class SunnahDailyGuide(
    val id: String,
    val timePeriod: String, // "Dini Hari & Bangun Tidur", "Pagi & Beraktivitas", "Makan, Minum & Berpakaian", "Masjid & Ibadah", "Sore, Keluarga & Pergaulan", "Malam & Sebelum Tidur"
    val timeRangeLabel: String,
    val title: String,
    val arabicPrayerOrMatan: String,
    val latinReading: String,
    val translation: String,
    val practiceSteps: List<String>,
    val dalilReference: String,
    val relatedHadithId: String,
    val isPracticedToday: Boolean = false
)
