package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.HadithCatalogData
import com.example.data.HadithEntity
import com.example.data.HadithGrade
import com.example.data.HadithRepository
import com.example.data.HadithSeedData
import com.example.data.ImamBookInfo
import com.example.data.SahabatNarratorInfo
import com.example.data.SunnahDailyGuide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class AppTab {
    BERANDA,
    KITAB_PERAWI,
    SUNNAH_HARIAN,
    FAVORIT
}

data class OnlineFetchUiState(
    val isLoading: Boolean = false,
    val selectedBookSlug: String = "bukhari",
    val statusMessage: String? = null,
    val isError: Boolean = false
)

class HadithViewModel(
    private val repository: HadithRepository
) : ViewModel() {

    private val _currentTab = MutableStateFlow(AppTab.BERANDA)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedGrade = MutableStateFlow<HadithGrade?>(null) // null = Semua Derajat
    val selectedGrade: StateFlow<HadithGrade?> = _selectedGrade.asStateFlow()

    private val _selectedTheme = MutableStateFlow("Semua Tema")
    val selectedTheme: StateFlow<String> = _selectedTheme.asStateFlow()

    private val _selectedImamId = MutableStateFlow<String?>(null) // null = Semua Kitab
    val selectedImamId: StateFlow<String?> = _selectedImamId.asStateFlow()

    private val _selectedNarratorId = MutableStateFlow<String?>(null) // null = Semua Perawi
    val selectedNarratorId: StateFlow<String?> = _selectedNarratorId.asStateFlow()

    private val _selectedBookmarkFolder = MutableStateFlow("Semua Folder")
    val selectedBookmarkFolder: StateFlow<String> = _selectedBookmarkFolder.asStateFlow()

    // Reader preferences
    private val _arabicFontSizeSp = MutableStateFlow(26f)
    val arabicFontSizeSp: StateFlow<Float> = _arabicFontSizeSp.asStateFlow()

    private val _showLatin = MutableStateFlow(true)
    val showLatin: StateFlow<Boolean> = _showLatin.asStateFlow()

    private val _isDarkModeOverride = MutableStateFlow<Boolean?>(null)
    val isDarkModeOverride: StateFlow<Boolean?> = _isDarkModeOverride.asStateFlow()

    // Active detail / modal states
    private val _selectedHadithForDetail = MutableStateFlow<HadithEntity?>(null)
    val selectedHadithForDetail: StateFlow<HadithEntity?> = _selectedHadithForDetail.asStateFlow()

    private val _selectedImamBio = MutableStateFlow<ImamBookInfo?>(null)
    val selectedImamBio: StateFlow<ImamBookInfo?> = _selectedImamBio.asStateFlow()

    private val _selectedNarratorBio = MutableStateFlow<SahabatNarratorInfo?>(null)
    val selectedNarratorBio: StateFlow<SahabatNarratorInfo?> = _selectedNarratorBio.asStateFlow()

    // Sunnah 24-hour checklist state
    private val _sunnahGuides = MutableStateFlow(HadithSeedData.sunnah24HourGuides)
    val sunnahGuides: StateFlow<List<SunnahDailyGuide>> = _sunnahGuides.asStateFlow()

    // Online Kutubut Tis'ah API state
    private val _onlineFetchState = MutableStateFlow(OnlineFetchUiState())
    val onlineFetchState: StateFlow<OnlineFetchUiState> = _onlineFetchState.asStateFlow()

    val allHadiths: StateFlow<List<HadithEntity>> = repository.allHadiths
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val filteredHadiths: StateFlow<List<HadithEntity>> = combine(
        allHadiths,
        _searchQuery,
        _selectedGrade,
        _selectedTheme,
        combine(_selectedImamId, _selectedNarratorId) { imam, narrator -> imam to narrator }
    ) { hadiths, query, grade, theme, (imamId, narratorId) ->
        val q = query.trim().lowercase()
        hadiths.filter { item ->
            val matchesGrade = grade == null || item.gradeEnum == grade
            val matchesTheme = theme == "Semua Tema" || item.themeCategory.equals(theme, ignoreCase = true)
            val matchesImam = imamId == null || item.bookId.equals(imamId, ignoreCase = true)
            val matchesNarrator = narratorId == null || item.narratorId.equals(narratorId, ignoreCase = true)

            val matchesQuery = if (q.isEmpty()) {
                true
            } else {
                item.title.lowercase().contains(q) ||
                    item.indonesianTranslation.lowercase().contains(q) ||
                    item.latinTransliteration.lowercase().contains(q) ||
                    item.bookName.lowercase().contains(q) ||
                    item.narratorName.lowercase().contains(q) ||
                    item.themeCategory.lowercase().contains(q) ||
                    item.syarahFiqih.lowercase().contains(q) ||
                    item.hadithNumber.toString() == q ||
                    "${item.bookName.lowercase()} ${item.hadithNumber}".contains(q) ||
                    "${item.bookId.lowercase()} ${item.hadithNumber}".contains(q)
            }

            matchesGrade && matchesTheme && matchesImam && matchesNarrator && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedHadiths: StateFlow<List<HadithEntity>> = combine(
        repository.bookmarkedHadiths,
        _selectedBookmarkFolder
    ) { bookmarks, folder ->
        if (folder == "Semua Folder") bookmarks
        else bookmarks.filter { it.bookmarkFolder.equals(folder, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.ensureSeeded()
        }
    }

    fun selectTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectGradeFilter(grade: HadithGrade?) {
        _selectedGrade.value = if (_selectedGrade.value == grade) null else grade
    }

    fun selectThemeFilter(theme: String) {
        _selectedTheme.value = theme
    }

    fun selectImamFilter(imamId: String?) {
        _selectedImamId.value = if (_selectedImamId.value == imamId) null else imamId
    }

    fun selectNarratorFilter(narratorId: String?) {
        _selectedNarratorId.value = if (_selectedNarratorId.value == narratorId) null else narratorId
    }

    fun clearAllFilters() {
        _searchQuery.value = ""
        _selectedGrade.value = null
        _selectedTheme.value = "Semua Tema"
        _selectedImamId.value = null
        _selectedNarratorId.value = null
    }

    fun selectBookmarkFolder(folder: String) {
        _selectedBookmarkFolder.value = folder
    }

    fun updateArabicFontSize(sizeSp: Float) {
        _arabicFontSizeSp.value = sizeSp.coerceIn(20f, 38f)
    }

    fun toggleShowLatin() {
        _showLatin.update { !it }
    }

    fun toggleDarkMode(currentDark: Boolean) {
        _isDarkModeOverride.value = !currentDark
    }

    fun openHadithDetail(hadith: HadithEntity) {
        _selectedHadithForDetail.value = hadith
    }

    fun openHadithDetailById(hadithId: String) {
        val found = allHadiths.value.find { it.id == hadithId }
        if (found != null) {
            _selectedHadithForDetail.value = found
        }
    }

    fun closeHadithDetail() {
        _selectedHadithForDetail.value = null
    }

    fun openImamBio(imam: ImamBookInfo) {
        _selectedImamBio.value = imam
    }

    fun closeImamBio() {
        _selectedImamBio.value = null
    }

    fun openNarratorBio(narrator: SahabatNarratorInfo) {
        _selectedNarratorBio.value = narrator
    }

    fun closeNarratorBio() {
        _selectedNarratorBio.value = null
    }

    fun openNarratorBioById(narratorId: String) {
        val found = HadithCatalogData.sahabatNarrators.find { it.id == narratorId }
        if (found != null) {
            _selectedNarratorBio.value = found
        }
    }

    fun toggleBookmark(hadith: HadithEntity, folder: String = hadith.bookmarkFolder) {
        viewModelScope.launch {
            repository.toggleBookmark(hadith, folder)
            // Refresh detail state if open
            val updated = hadith.copy(isBookmarked = !hadith.isBookmarked, bookmarkFolder = folder)
            if (_selectedHadithForDetail.value?.id == hadith.id) {
                _selectedHadithForDetail.value = updated
            }
        }
    }

    fun saveHadithToFolder(hadith: HadithEntity, folder: String) {
        viewModelScope.launch {
            repository.saveToFolder(hadith.id, folder)
            if (_selectedHadithForDetail.value?.id == hadith.id) {
                _selectedHadithForDetail.value = hadith.copy(isBookmarked = true, bookmarkFolder = folder)
            }
        }
    }

    fun savePersonalNote(hadith: HadithEntity, note: String) {
        viewModelScope.launch {
            repository.savePersonalNote(hadith.id, note)
            if (_selectedHadithForDetail.value?.id == hadith.id) {
                _selectedHadithForDetail.value = hadith.copy(
                    personalNote = note,
                    isBookmarked = if (note.isNotBlank()) true else hadith.isBookmarked
                )
            }
        }
    }

    fun toggleSunnahPractice(guideId: String) {
        _sunnahGuides.update { list ->
            list.map { item ->
                if (item.id == guideId) item.copy(isPracticedToday = !item.isPracticedToday)
                else item
            }
        }
    }

    fun setOnlineBookSlug(slug: String) {
        _onlineFetchState.update { it.copy(selectedBookSlug = slug, statusMessage = null, isError = false) }
    }

    fun fetchHadithByNumberFromServer(bookSlug: String, numberInput: String) {
        val num = numberInput.trim().toIntOrNull()
        if (num == null || num < 1) {
            _onlineFetchState.update {
                it.copy(
                    statusMessage = "Masukkan nomor hadits yang valid (contoh: 1, 15, 250).",
                    isError = true
                )
            }
            return
        }

        viewModelScope.launch {
            _onlineFetchState.update {
                it.copy(isLoading = true, statusMessage = "Mengambil Hadits No. $num dari server Kutubut Tis'ah…", isError = false)
            }
            val result = repository.fetchOnlineHadithByNumber(bookSlug, num)
            result.onSuccess { hadith ->
                _onlineFetchState.update {
                    it.copy(
                        isLoading = false,
                        statusMessage = "Berhasil memuat & menyimpan ${hadith.bookName} No. ${hadith.hadithNumber}!",
                        isError = false
                    )
                }
                _selectedHadithForDetail.value = hadith
            }.onFailure { err ->
                _onlineFetchState.update {
                    it.copy(
                        isLoading = false,
                        statusMessage = "Gagal memuat dari server (${err.localizedMessage ?: "Periksa koneksi internet"}). Anda tetap dapat membaca seluruh koleksi offline.",
                        isError = true
                    )
                }
            }
        }
    }

    fun fetchHadithBatchFromServer(bookSlug: String, startNumber: Int) {
        val endNumber = startNumber + 9
        viewModelScope.launch {
            _onlineFetchState.update {
                it.copy(
                    isLoading = true,
                    statusMessage = "Mengunduh rentang Hadits No. $startNumber–$endNumber dari kitab $bookSlug…",
                    isError = false
                )
            }
            val result = repository.fetchOnlineHadithRange(bookSlug, startNumber, endNumber)
            result.onSuccess { list ->
                _onlineFetchState.update {
                    it.copy(
                        isLoading = false,
                        statusMessage = "Berhasil menambahkan ${list.size} hadits ($startNumber–$endNumber) ke penyimpanan offline!",
                        isError = false
                    )
                }
            }.onFailure { err ->
                _onlineFetchState.update {
                    it.copy(
                        isLoading = false,
                        statusMessage = "Koneksi server sedang sibuk (${err.localizedMessage ?: "Offline"}). Koleksi lokal tetap siap digunakan.",
                        isError = true
                    )
                }
            }
        }
    }

    class Factory(private val repository: HadithRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HadithViewModel(repository) as T
        }
    }
}
