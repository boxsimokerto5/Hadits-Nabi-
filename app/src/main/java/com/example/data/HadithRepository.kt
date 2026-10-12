package com.example.data

import kotlinx.coroutines.flow.Flow

class HadithRepository(
    private val hadithDao: HadithDao,
    private val remoteApi: HadithRemoteApi
) {
    val allHadiths: Flow<List<HadithEntity>> = hadithDao.getAllHadiths()
    val bookmarkedHadiths: Flow<List<HadithEntity>> = hadithDao.getBookmarkedHadiths()

    suspend fun ensureSeeded() {
        val count = hadithDao.getHadithCount()
        if (count == 0) {
            hadithDao.insertAll(HadithSeedData.getInitialHadiths())
        }
    }

    suspend fun toggleBookmark(hadith: HadithEntity, folder: String = hadith.bookmarkFolder) {
        hadithDao.updateBookmark(
            id = hadith.id,
            bookmarked = !hadith.isBookmarked,
            folder = folder
        )
    }

    suspend fun saveToFolder(hadithId: String, folder: String) {
        hadithDao.updateBookmark(
            id = hadithId,
            bookmarked = true,
            folder = folder
        )
    }

    suspend fun savePersonalNote(hadithId: String, note: String) {
        hadithDao.updatePersonalNote(hadithId, note)
    }

    /**
     * Fetches a specific Hadith by Imam book slug and number from the online Kutubut Tis'ah API
     * (62,000+ hadiths across Bukhari, Muslim, Abu Daud, Tirmidzi, Nasa'i, Ibnu Majah, Ahmad, Malik, Darimi)
     * and caches it into Room database so it never gets lost and remains searchable offline.
     */
    suspend fun fetchOnlineHadithByNumber(bookSlug: String, number: Int): Result<HadithEntity> {
        val localId = "${bookSlug}_$number"
        val existing = hadithDao.getHadithById(localId)
        if (existing != null) {
            return Result.success(existing)
        }

        return try {
            val response = remoteApi.getHadithByNumber(bookSlug, number)
            val content = response.data?.contents
            if (content != null && !content.arab.isNullOrBlank()) {
                val bookInfo = HadithCatalogData.nineImams.find { it.apiSlug == bookSlug }
                val bookDisplayName = bookInfo?.name ?: response.data.name ?: bookSlug.replaceFirstChar { it.uppercase() }
                val defaultGrade = if (bookSlug == "bukhari" || bookSlug == "muslim") "SHAHIH" else "SHAHIH"
                val defaultScholar = if (bookSlug == "bukhari" || bookSlug == "muslim") {
                    "HR. $bookDisplayName No. $number (Shahih)"
                } else {
                    "HR. $bookDisplayName No. $number (Kutubut Tis'ah)"
                }

                val entity = HadithEntity(
                    id = localId,
                    bookId = bookSlug,
                    bookName = bookDisplayName,
                    hadithNumber = content.number ?: number,
                    title = "$bookDisplayName Nomor ${content.number ?: number}",
                    arabicText = content.arab,
                    latinTransliteration = "Riwayat Kitab $bookDisplayName Nomor ${content.number ?: number}",
                    indonesianTranslation = content.translationId ?: "",
                    grade = defaultGrade,
                    gradeScholar = defaultScholar,
                    themeCategory = inferThemeFromTranslation(content.translationId ?: ""),
                    narratorId = inferNarratorIdFromTranslation(content.translationId ?: ""),
                    narratorName = inferNarratorNameFromTranslation(content.translationId ?: ""),
                    sunnahTimeSlot = null,
                    syarahFiqih = "• Hadits ini diambil langsung dari ensiklopedia lengkap $bookDisplayName Nomor ${content.number ?: number} dan telah tersimpan otomatis ke dalam memori perangkat Anda (Offline Cache).\n• Anda dapat menambahkan catatan kajian pribadi atau menyimpannya ke dalam folder koleksi favorit."
                )
                hadithDao.insertHadith(entity)
                Result.success(entity)
            } else {
                Result.failure(Exception("Hadits nomor $number pada kitab $bookSlug tidak ditemukan."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Fetches a range of Hadiths (e.g., "1-10") from the online Kutubut Tis'ah API and caches them locally.
     */
    suspend fun fetchOnlineHadithRange(bookSlug: String, startNumber: Int, endNumber: Int): Result<List<HadithEntity>> {
        val safeStart = startNumber.coerceAtLeast(1)
        val safeEnd = endNumber.coerceIn(safeStart, safeStart + 14) // Max 15 per batch for fast response
        return try {
            val response = remoteApi.getHadithRange(bookSlug, "$safeStart-$safeEnd")
            val items = response.data?.hadiths.orEmpty()
            if (items.isNotEmpty()) {
                val bookInfo = HadithCatalogData.nineImams.find { it.apiSlug == bookSlug }
                val bookDisplayName = bookInfo?.name ?: response.data?.name ?: bookSlug
                val entities = items.mapNotNull { item ->
                    val num = item.number ?: return@mapNotNull null
                    val arab = item.arab ?: return@mapNotNull null
                    val trans = item.translationId ?: ""
                    val localId = "${bookSlug}_$num"
                    val existing = hadithDao.getHadithById(localId)
                    existing ?: HadithEntity(
                        id = localId,
                        bookId = bookSlug,
                        bookName = bookDisplayName,
                        hadithNumber = num,
                        title = "$bookDisplayName No. $num — ${extractShortTitle(trans)}",
                        arabicText = arab,
                        latinTransliteration = "Matan $bookDisplayName No. $num",
                        indonesianTranslation = trans,
                        grade = "SHAHIH",
                        gradeScholar = "HR. $bookDisplayName No. $num",
                        themeCategory = inferThemeFromTranslation(trans),
                        narratorId = inferNarratorIdFromTranslation(trans),
                        narratorName = inferNarratorNameFromTranslation(trans),
                        sunnahTimeSlot = null,
                        syarahFiqih = "• Riwayat dari kitab $bookDisplayName Nomor $num. Tersimpan di database lokal aplikasi agar dapat diakses kembali kapan saja tanpa koneksi internet."
                    )
                }
                hadithDao.insertAll(entities)
                Result.success(entities)
            } else {
                Result.failure(Exception("Rentang hadits $safeStart-$safeEnd tidak ditemukan."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun extractShortTitle(translation: String): String {
        val cleaned = translation
            .replace(Regex("^Telah menceritakan kepada kami.*?berkata[,:]?\\s*", RegexOption.IGNORE_CASE), "")
            .replace(Regex("^Dari.*?berkata[,:]?\\s*", RegexOption.IGNORE_CASE), "")
            .trim()
        val words = cleaned.split(" ").take(6).joinToString(" ")
        return if (words.length > 42) words.take(42) + "…" else words.ifBlank { "Riwayat Hadits" }
    }

    private fun inferThemeFromTranslation(text: String): String {
        val lower = text.lowercase()
        return when {
            lower.contains("wudhu") || lower.contains("mandi") || lower.contains("tayammum") || lower.contains("siwak") || lower.contains("suci") -> "Thaharah (Bersuci)"
            lower.contains("shalat") || lower.contains("sholat") || lower.contains("sujud") || lower.contains("ruku") || lower.contains("masjid") || lower.contains("adzan") -> "Sholat"
            lower.contains("puasa") || lower.contains("ramadhan") || lower.contains("sahur") || lower.contains("berbuka") -> "Puasa"
            lower.contains("zakat") || lower.contains("sedekah") || lower.contains("infak") -> "Zakat & Sedekah"
            lower.contains("jual") || lower.contains("beli") || lower.contains("hutang") || lower.contains("dagang") || lower.contains("riba") -> "Muamalah & Harta"
            lower.contains("nikah") || lower.contains("istri") || lower.contains("suami") || lower.contains("keluarga") || lower.contains("anak") -> "Keluarga & Pernikahan"
            lower.contains("doa") || lower.contains("dzikir") || lower.contains("zikir") || lower.contains("tasbih") || lower.contains("istighfar") -> "Zikir & Doa"
            lower.contains("ilmu") || lower.contains("ulama") || lower.contains("belajar") -> "Ilmu & Sunnah"
            lower.contains("akhlak") || lower.contains("senyum") || lower.contains("sabar") || lower.contains("jujur") || lower.contains("marah") -> "Akhlak & Adab"
            else -> "Iman & Aqidah"
        }
    }

    private fun inferNarratorIdFromTranslation(text: String): String {
        val lower = text.lowercase()
        return when {
            lower.contains("abu hurairah") -> "abu_hurairah"
            lower.contains("aisyah") || lower.contains("'aisyah") -> "aisyah"
            lower.contains("ibnu umar") || lower.contains("abdullah bin umar") -> "ibnu_umar"
            lower.contains("anas") -> "anas_bin_malik"
            lower.contains("ibnu abbas") -> "ibnu_abbas"
            lower.contains("jabir") -> "jabir_bin_abdillah"
            lower.contains("abu sa'id") || lower.contains("abu said") -> "abu_said_alkhudri"
            lower.contains("umar bin khattab") || lower.contains("umar bin al khaththab") -> "umar_bin_khattab"
            else -> "abu_hurairah"
        }
    }

    private fun inferNarratorNameFromTranslation(text: String): String {
        val id = inferNarratorIdFromTranslation(text)
        return HadithCatalogData.sahabatNarrators.find { it.id == id }?.name ?: "Sahabat Rasulullah ﷺ"
    }
}
