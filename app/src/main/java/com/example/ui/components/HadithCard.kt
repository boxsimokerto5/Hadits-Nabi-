package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.data.HadithEntity
import com.example.data.HadithGrade
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.GradeDhaifBgLight
import com.example.ui.theme.GradeDhaifDot
import com.example.ui.theme.GradeDhaifTextLight
import com.example.ui.theme.GradeHasanBgLight
import com.example.ui.theme.GradeHasanDot
import com.example.ui.theme.GradeHasanTextLight
import com.example.ui.theme.GradeMaudhuBgLight
import com.example.ui.theme.GradeMaudhuDot
import com.example.ui.theme.GradeMaudhuTextLight
import com.example.ui.theme.GradeShahihBgLight
import com.example.ui.theme.GradeShahihDot
import com.example.ui.theme.GradeShahihTextLight

@Composable
fun HadithGradeBadge(
    grade: HadithGrade,
    modifier: Modifier = Modifier
) {
    val (bgColor, textColor, dotColor) = when (grade) {
        HadithGrade.SHAHIH -> Triple(GradeShahihBgLight, GradeShahihTextLight, GradeShahihDot)
        HadithGrade.HASAN -> Triple(GradeHasanBgLight, GradeHasanTextLight, GradeHasanDot)
        HadithGrade.DHAIF -> Triple(GradeDhaifBgLight, GradeDhaifTextLight, GradeDhaifDot)
        HadithGrade.MAUDHU -> Triple(GradeMaudhuBgLight, GradeMaudhuTextLight, GradeMaudhuDot)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(bgColor)
            .border(1.dp, dotColor.copy(alpha = 0.35f), RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(dotColor)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "${grade.label} • ${grade.arabicLabel}",
            style = MaterialTheme.typography.labelMedium.copy(
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HadithCard(
    hadith: HadithEntity,
    arabicFontSizeSp: Float,
    showLatin: Boolean,
    onCardClick: () -> Unit,
    onNarratorClick: () -> Unit,
    onToggleBookmark: () -> Unit,
    onCopyClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val grade = hadith.gradeEnum
    val isWarningGrade = grade == HadithGrade.DHAIF || grade == HadithGrade.MAUDHU

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isWarningGrade) {
                    if (grade == HadithGrade.MAUDHU) GradeMaudhuDot.copy(alpha = 0.45f)
                    else GradeDhaifDot.copy(alpha = 0.45f)
                } else {
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.65f)
                },
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(onClick = onCardClick)
            .testTag("hadith_card_${hadith.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Top Row: Book + Number & Authenticity Grade Badge (Rata Kiri Kanan)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${hadith.bookName} • No. ${hadith.hadithNumber}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                HadithGradeBadge(grade = grade)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Hadith Title
            Text(
                text = hadith.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            )

            // Warning banner if Dhaif or Maudhu' so the user never confuses weak/fabricated with authentic
            if (isWarningGrade) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (grade == HadithGrade.MAUDHU) GradeMaudhuBgLight.copy(alpha = 0.75f)
                            else GradeDhaifBgLight.copy(alpha = 0.75f)
                        )
                        .padding(horizontal = 10.dp, vertical = 7.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.WarningAmber,
                        contentDescription = "Peringatan Derajat Hadits",
                        tint = if (grade == HadithGrade.MAUDHU) GradeMaudhuTextLight else GradeDhaifTextLight,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = grade.shortDesc,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (grade == HadithGrade.MAUDHU) GradeMaudhuTextLight else GradeDhaifTextLight,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Arabic Matan Container with soft alabaster/sky tint
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Text(
                    text = hadith.arabicText,
                    fontFamily = AmiriFontFamily,
                    fontSize = arabicFontSizeSp.sp,
                    lineHeight = (arabicFontSizeSp * 1.75f).sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (showLatin && hadith.latinTransliteration.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = hadith.latinTransliteration,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontStyle = FontStyle.Italic,
                        fontSize = 13.sp
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Indonesian Translation (Justified / Rata Kiri Kanan feel)
            Text(
                text = hadith.indonesianTranslation,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 23.sp
                ),
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Metadata Tags: Theme & Interactive Narrator Pill
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f)
                ) {
                    Text(
                        text = "Tema: ${hadith.themeCategory}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    onClick = onNarratorClick,
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profil Perawi",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = hadith.narratorName,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
            Spacer(modifier = Modifier.height(4.dp))

            // Bottom Action Bar: Takhrij Scholar Note + Copy, Share, Bookmark
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = hadith.gradeScholar,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onCopyClick,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("copy_hadith_${hadith.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Salin Hadits",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    IconButton(
                        onClick = onShareClick,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("share_hadith_${hadith.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Bagikan Hadits",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    IconButton(
                        onClick = onToggleBookmark,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("bookmark_hadith_${hadith.id}")
                    ) {
                        Icon(
                            imageVector = if (hadith.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Simpan Koleksi",
                            tint = if (hadith.isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
