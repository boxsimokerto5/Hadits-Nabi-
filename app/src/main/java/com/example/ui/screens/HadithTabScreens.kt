package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.FolderSpecial
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.HadithCatalogData
import com.example.data.HadithEntity
import com.example.data.HadithGrade
import com.example.data.ImamBookInfo
import com.example.data.SahabatNarratorInfo
import com.example.data.SunnahDailyGuide
import com.example.notification.DailyNotificationConfig
import com.example.ui.components.DailyHadithSpotlightCard
import com.example.ui.components.HadithCard
import com.example.ui.components.KaabaHeaderBanner
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.GradeDhaifDot
import com.example.ui.theme.GradeHasanDot
import com.example.ui.theme.GradeMaudhuDot
import com.example.ui.theme.GradeShahihDot
import com.example.ui.theme.KaabaGold

@Composable
fun BerandaTabContent(
    hadiths: List<HadithEntity>,
    hadithOfTheDay: HadithEntity?,
    notificationConfig: DailyNotificationConfig,
    totalAllHadiths: Int,
    searchQuery: String,
    selectedGrade: HadithGrade?,
    selectedTheme: String,
    selectedImamId: String?,
    selectedNarratorId: String?,
    arabicFontSizeSp: Float,
    showLatin: Boolean,
    isDarkTheme: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onSelectGrade: (HadithGrade?) -> Unit,
    onSelectTheme: (String) -> Unit,
    onClearFilters: () -> Unit,
    onToggleDarkTheme: () -> Unit,
    onOpenOnlineLookup: () -> Unit,
    onOpenNotificationSettings: () -> Unit,
    onSendTestNotification: (HadithEntity) -> Unit,
    onHadithClick: (HadithEntity) -> Unit,
    onNarratorClick: (String) -> Unit,
    onToggleBookmark: (HadithEntity) -> Unit,
    onCopyHadith: (HadithEntity) -> Unit,
    onShareHadith: (HadithEntity) -> Unit
) {
    val hasActiveFilter = searchQuery.isNotBlank() ||
        selectedGrade != null ||
        selectedTheme != "Semua Tema" ||
        selectedImamId != null ||
        selectedNarratorId != null

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("beranda_hadith_list"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // 1. Kaaba & Blue Sky Full-Width Edge-to-Edge Soft Gradient Header
        item {
            KaabaHeaderBanner(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                totalCachedHadiths = totalAllHadiths,
                isDarkTheme = isDarkTheme,
                onToggleDarkTheme = onToggleDarkTheme,
                onOpenQuickOnlineLookup = onOpenOnlineLookup,
                onOpenNotificationSettings = onOpenNotificationSettings
            )
        }

        // 1b. "Hadits Hari Ini" Daily Motivation Spotlight Card & Notification Trigger
        if (hadithOfTheDay != null && !hasActiveFilter) {
            item {
                DailyHadithSpotlightCard(
                    hadithOfTheDay = hadithOfTheDay,
                    notificationConfig = notificationConfig,
                    onOpenDetail = { onHadithClick(hadithOfTheDay) },
                    onOpenReminderSettings = onOpenNotificationSettings,
                    onSendTestNotification = { onSendTestNotification(hadithOfTheDay) },
                    onShareHadith = { onShareHadith(hadithOfTheDay) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

        // 2. Authenticity Grade Filter Pills (Semua, Shahih, Hasan, Dhaif, Maudhu')
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Filter Derajat Hadits",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Text(
                        text = "62.308 Riwayat 9 Imam",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedGrade == null,
                        onClick = { onSelectGrade(null) },
                        label = { Text("Semua Derajat") },
                        modifier = Modifier.testTag("filter_grade_all")
                    )

                    HadithGrade.entries.forEach { grade ->
                        val dotColor = when (grade) {
                            HadithGrade.SHAHIH -> GradeShahihDot
                            HadithGrade.HASAN -> GradeHasanDot
                            HadithGrade.DHAIF -> GradeDhaifDot
                            HadithGrade.MAUDHU -> GradeMaudhuDot
                        }
                        FilterChip(
                            selected = selectedGrade == grade,
                            onClick = { onSelectGrade(grade) },
                            leadingIcon = {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(dotColor)
                                )
                            },
                            label = { Text(grade.label) },
                            modifier = Modifier.testTag("filter_grade_${grade.name.lowercase()}")
                        )
                    }
                }
            }
        }

        // 3. Life Themes Directory Horizontal Filter
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Direktori Tema Kehidupan & Fiqih",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    if (hasActiveFilter) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .clickable(onClick = onClearFilters)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                .testTag("reset_filters_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ClearAll,
                                contentDescription = "Reset Filter",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Reset Filter",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HadithCatalogData.allThemes.forEach { theme ->
                        val isSelected = selectedTheme == theme
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectTheme(theme) },
                            label = { Text(theme) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }
            }
        }

        // Active Imam or Narrator filter banner if selected from Tab 2
        if (selectedImamId != null || selectedNarratorId != null) {
            item {
                val activeImamName = HadithCatalogData.nineImams.find { it.id == selectedImamId }?.name
                val activeNarratorName = HadithCatalogData.sahabatNarrators.find { it.id == selectedNarratorId }?.name
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = buildString {
                                    append("Filter Aktif: ")
                                    if (activeImamName != null) append("Kitab $activeImamName ")
                                    if (activeNarratorName != null) append("• Perawi $activeNarratorName")
                                },
                                style = MaterialTheme.typography.labelLarge.copy(
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        OutlinedButton(
                            onClick = onClearFilters,
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            modifier = Modifier.height(32.dp)
                        ) {
                            Text("Tampilkan Semua", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }

        // Result Counter & Quick Online Server Prompt
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Menampilkan ${hadiths.size} Hadits Pilihan",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Text(
                    text = "Ketuk kartu untuk Syarah & Fawaid",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }

        // Empty state with direct online server lookup button
        if (hadiths.isEmpty()) {
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(44.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Hadits belum ditemukan di filter lokal saat ini",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Anda dapat mereset filter di atas atau mengambil langsung nomor hadits spesifik dari server Kutubut Tis'ah (62.000+ hadits).",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedButton(onClick = onClearFilters) {
                                Text("Reset Filter")
                            }
                            Button(onClick = onOpenOnlineLookup) {
                                Icon(Icons.Default.CloudDownload, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Cari di Server 9 Imam")
                            }
                        }
                    }
                }
            }
        } else {
            items(hadiths, key = { it.id }) { hadith ->
                HadithCard(
                    hadith = hadith,
                    arabicFontSizeSp = arabicFontSizeSp,
                    showLatin = showLatin,
                    onCardClick = { onHadithClick(hadith) },
                    onNarratorClick = { onNarratorClick(hadith.narratorId) },
                    onToggleBookmark = { onToggleBookmark(hadith) },
                    onCopyClick = { onCopyHadith(hadith) },
                    onShareClick = { onShareHadith(hadith) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 7.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KitabDanPerawiTabContent(
    searchQuery: String,
    totalCachedHadiths: Int,
    isDarkTheme: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onToggleDarkTheme: () -> Unit,
    onOpenOnlineLookup: () -> Unit,
    onImamClick: (ImamBookInfo) -> Unit,
    onNarratorClick: (SahabatNarratorInfo) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("kitab_perawi_list"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        item {
            KaabaHeaderBanner(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                totalCachedHadiths = totalCachedHadiths,
                isDarkTheme = isDarkTheme,
                onToggleDarkTheme = onToggleDarkTheme,
                onOpenQuickOnlineLookup = onOpenOnlineLookup
            )
        }

        // Section 1: Kutubut Tis'ah (9 Kitab Imam Hadits)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Ensiklopedia Kutubut Tis'ah (9 Kitab Induk)",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Ketuk kitab untuk membaca biografi Imam, metodologi sanad, atau mengunduh hadits",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }

        items(HadithCatalogData.nineImams, key = { it.id }) { imam ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { onImamClick(imam) }
                    .testTag("imam_card_${imam.id}")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = imam.name,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            )
                            Text(
                                text = imam.authorFullName,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                        Text(
                            text = imam.arabicName,
                            fontFamily = AmiriFontFamily,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = KaabaGold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = imam.description,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                        ) {
                            Text(
                                text = "±${imam.totalHadithsInBook} Hadits",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        Text(
                            text = imam.birthDeathHijri,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }

        // Section 2: Biografi Sahabat Perawi Terbanyak (Al-Muktsirun fil Hadits)
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Biografi Perawi Sahabat (Al-Muktsirun)",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Mengenal profil para Sahabat mulia yang paling banyak meriwayatkan Sunnah Nabi ﷺ",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }

        items(HadithCatalogData.sahabatNarrators, key = { it.id }) { narrator ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { onNarratorClick(narrator) }
                    .testTag("narrator_card_${narrator.id}")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = narrator.name,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = narrator.title,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = "${narrator.totalNarratedCount} Hadits",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = narrator.bioSummary,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun Sunnah24JamTabContent(
    guides: List<SunnahDailyGuide>,
    searchQuery: String,
    totalCachedHadiths: Int,
    arabicFontSizeSp: Float,
    isDarkTheme: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onToggleDarkTheme: () -> Unit,
    onOpenOnlineLookup: () -> Unit,
    onTogglePractice: (String) -> Unit,
    onOpenRelatedHadith: (String) -> Unit
) {
    val practicedCount = guides.count { it.isPracticedToday }
    val progress = if (guides.isNotEmpty()) practicedCount.toFloat() / guides.size else 0f

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("sunnah_24jam_list"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        item {
            KaabaHeaderBanner(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                totalCachedHadiths = totalCachedHadiths,
                isDarkTheme = isDarkTheme,
                onToggleDarkTheme = onToggleDarkTheme,
                onOpenQuickOnlineLookup = onOpenOnlineLookup
            )
        }

        // Daily Sunnah Progress Summary Banner
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.WbSunny,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "24 Jam Bersama Sunnah Rasulullah ﷺ",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = "Panduan praktis amalan harian dari bangun tidur hingga tidur kembali",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }
                        }

                        Text(
                            text = "$practicedCount/${guides.size}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(50)),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }

        items(guides, key = { it.id }) { guide ->
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 7.dp)
                    .border(
                        1.dp,
                        if (guide.isPracticedToday) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                        else MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
                        RoundedCornerShape(18.dp)
                    )
                    .testTag("sunnah_card_${guide.id}")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "${guide.timePeriod} • ${guide.timeRangeLabel}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .clickable { onTogglePractice(guide.id) }
                                .padding(horizontal = 6.dp, vertical = 4.dp)
                                .testTag("sunnah_check_${guide.id}")
                        ) {
                            Icon(
                                imageVector = if (guide.isPracticedToday) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                contentDescription = "Tandai Sudah Diamalkan",
                                tint = if (guide.isPracticedToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (guide.isPracticedToday) "Diamalkan" else "Tandai",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = if (guide.isPracticedToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = guide.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Arabic Prayer / Matan Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f))
                            .padding(14.dp)
                    ) {
                        Text(
                            text = guide.arabicPrayerOrMatan,
                            fontFamily = AmiriFontFamily,
                            fontSize = arabicFontSizeSp.sp,
                            lineHeight = (arabicFontSizeSp * 1.7f).sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = guide.latinReading,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontStyle = FontStyle.Italic
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "\"${guide.translation}\"",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Tata Cara & Adab Sunnah:",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    guide.practiceSteps.forEachIndexed { idx, step ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "${idx + 1}. ",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = guide.dalilReference,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        OutlinedButton(
                            onClick = { onOpenRelatedHadith(guide.relatedHadithId) },
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                            modifier = Modifier.height(34.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Buka Dalil Hadits", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavoritDanCatatanTabContent(
    bookmarkedHadiths: List<HadithEntity>,
    selectedFolder: String,
    searchQuery: String,
    totalCachedHadiths: Int,
    arabicFontSizeSp: Float,
    showLatin: Boolean,
    isDarkTheme: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onSelectFolder: (String) -> Unit,
    onToggleDarkTheme: () -> Unit,
    onOpenOnlineLookup: () -> Unit,
    onHadithClick: (HadithEntity) -> Unit,
    onNarratorClick: (String) -> Unit,
    onToggleBookmark: (HadithEntity) -> Unit,
    onCopyHadith: (HadithEntity) -> Unit,
    onShareHadith: (HadithEntity) -> Unit
) {
    val allFolders = listOf("Semua Folder") + HadithCatalogData.bookmarkFolders

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("favorit_hadith_list"),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        item {
            KaabaHeaderBanner(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                totalCachedHadiths = totalCachedHadiths,
                isDarkTheme = isDarkTheme,
                onToggleDarkTheme = onToggleDarkTheme,
                onOpenQuickOnlineLookup = onOpenOnlineLookup
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.FolderSpecial,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Folder Koleksi Favorit & Catatan Kajian",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    allFolders.forEach { folder ->
                        FilterChip(
                            selected = selectedFolder == folder,
                            onClick = { onSelectFolder(folder) },
                            label = { Text(folder) }
                        )
                    }
                }
            }
        }

        if (bookmarkedHadiths.isEmpty()) {
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                            modifier = Modifier.size(44.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Belum Ada Hadits di Folder \"$selectedFolder\"",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Ketuk ikon Simpan (Bookmark) pada kartu hadits atau tambahkan Catatan Pribadi di halaman detail untuk mengelompokkan hadits ke dalam folder ceramah, hafalan, atau amalan harian.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } else {
            items(bookmarkedHadiths, key = { it.id }) { hadith ->
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 7.dp)) {
                    // Folder & Note Badge above card
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = "📁 Folder: ${hadith.bookmarkFolder}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        if (hadith.personalNote.isNotBlank()) {
                            Text(
                                text = "📝 Memiliki Catatan Pribadi",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }

                    HadithCard(
                        hadith = hadith,
                        arabicFontSizeSp = arabicFontSizeSp,
                        showLatin = showLatin,
                        onCardClick = { onHadithClick(hadith) },
                        onNarratorClick = { onNarratorClick(hadith.narratorId) },
                        onToggleBookmark = { onToggleBookmark(hadith) },
                        onCopyClick = { onCopyHadith(hadith) },
                        onShareClick = { onShareHadith(hadith) }
                    )

                    if (hadith.personalNote.isNotBlank()) {
                        Surface(
                            shape = RoundedCornerShape(bottomStart = 14.dp, bottomEnd = 14.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Catatan Kajian Anda:",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = hadith.personalNote,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
