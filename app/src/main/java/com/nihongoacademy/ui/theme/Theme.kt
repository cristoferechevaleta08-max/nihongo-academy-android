package com.nihongoacademy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.nihongoacademy.data.PreferencesStore
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Monochrome visual system for Nihongo Academy.
 * Legacy semantic names are kept to avoid breaking screens that still reference them.
 */
object AcademyPalette {
    val Ink = Color(0xFF111111)
    val Paper = Color(0xFFF7F7F7)
    val Card = Color(0xFFFFFFFF)
    val Mist = Color(0xFFEDEDED)
    val Olive = Color(0xFFD6D6D6)
    val Sage = Color(0xFF777777)
    val Sand = Color(0xFFE5E5E5)
    val Clay = Color(0xFF999999)
    val Bronze = Color(0xFF555555)
    val Accent = Color(0xFF202020)
    val Muted = Color(0xFF717171)
}

private val LightColors = lightColorScheme(
    primary = AcademyPalette.Ink,
    onPrimary = Color.White,
    background = AcademyPalette.Paper,
    onBackground = AcademyPalette.Ink,
    surface = AcademyPalette.Card,
    onSurface = AcademyPalette.Ink,
    surfaceVariant = AcademyPalette.Mist,
    secondary = AcademyPalette.Bronze,
    onSecondary = Color.White,
    tertiary = AcademyPalette.Sand,
    onTertiary = AcademyPalette.Ink,
    outline = Color(0xFFD1D1D1)
)

private val DarkColors = darkColorScheme(
    primary = Color.White,
    onPrimary = AcademyPalette.Ink,
    background = Color(0xFF101010),
    onBackground = Color(0xFFF1F1F1),
    surface = Color(0xFF191919),
    onSurface = Color(0xFFF1F1F1),
    surfaceVariant = Color(0xFF262626),
    onSurfaceVariant = Color(0xFFD0D0D0),
    secondary = Color(0xFFBDBDBD),
    onSecondary = Color(0xFF111111),
    tertiary = Color(0xFF8A8A8A),
    onTertiary = Color(0xFF101010),
    outline = Color(0xFF444444)
)

private val AppTypography = Typography(
    displayLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 42.sp),
    headlineLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 30.sp),
    headlineMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 24.sp),
    titleLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 22.sp),
    titleMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
    bodyLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    labelLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium, fontSize = 14.sp)
)

@Composable
fun NihongoTheme(
    darkTheme: Boolean? = null,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val themeModeFlow = remember(context) { PreferencesStore(context).themeModeFlow() }
    val themeMode by themeModeFlow.collectAsState(initial = "system")
    val useDarkTheme = darkTheme ?: when (themeMode) {
        "dark" -> true
        "light" -> false
        else -> isSystemInDarkTheme()
    }
    val colors = if (useDarkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}
