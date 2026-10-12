package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF38BDF8),
    onPrimary = Color(0xFF082F49),
    primaryContainer = Color(0xFF075985),
    onPrimaryContainer = Color(0xFFE0F2FE),
    secondary = KaabaGold,
    onSecondary = Color(0xFF1E1B18),
    secondaryContainer = Color(0xFF423311),
    onSecondaryContainer = KaabaGoldSoft,
    tertiary = Color(0xFF34D399),
    background = NightSanctuaryBg,
    onBackground = NightTextPrimary,
    surface = NightCardSurface,
    onSurface = NightTextPrimary,
    surfaceVariant = NightElevatedSurface,
    onSurfaceVariant = NightTextSecondary,
    outline = Color(0xFF334155),
    outlineVariant = Color(0xFF1E293B)
)

private val LightColorScheme = lightColorScheme(
    primary = SkyBlueMid,
    onPrimary = Color.White,
    primaryContainer = SkyBlueLight,
    onPrimaryContainer = SkyBlueDeep,
    secondary = KaabaGold,
    onSecondary = Color.White,
    secondaryContainer = KaabaGoldSoft,
    onSecondaryContainer = Color(0xFF78350F),
    tertiary = Color(0xFF059669),
    background = AlabasterBg,
    onBackground = SlateTextPrimary,
    surface = PureWhiteSurface,
    onSurface = SlateTextPrimary,
    surfaceVariant = SoftSlateCard,
    onSurfaceVariant = SlateTextSecondary,
    outline = BorderSubtle,
    outlineVariant = Color(0xFFF1F5F9)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
