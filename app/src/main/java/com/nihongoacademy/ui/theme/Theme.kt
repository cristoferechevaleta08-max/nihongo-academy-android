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

object AcademyPalette {
    val Ink = Color(0xFF0A0A0A)
    val Paper = Color(0xFFF9F5F0)
    val Card = Color.White
    val Mist = Ink.copy(alpha = 0.04f)
    val Olive = Ink.copy(alpha = 0.12f)
    val Sage = Ink.copy(alpha = 0.55f)
    val Sand = Ink.copy(alpha = 0.08f)
    val Clay = Ink.copy(alpha = 0.4f)
    val Bronze = Ink
    val Accent = Ink
    val Muted = Ink.copy(alpha = 0.6f)
}

private val LightColors = lightColorScheme(
    primary = AcademyPalette.Ink,
    onPrimary = Color.White,
    background = AcademyPalette.Paper,
    onBackground = AcademyPalette.Ink,
    surface = AcademyPalette.Card,
    onSurface = AcademyPalette.Ink,
    surfaceVariant = AcademyPalette.Mist,
    onSurfaceVariant = AcademyPalette.Muted,
    secondary = AcademyPalette.Ink,
    onSecondary = Color.White,
    tertiary = AcademyPalette.Ink.copy(alpha = 0.12f),
    onTertiary = AcademyPalette.Ink,
    outline = AcademyPalette.Ink.copy(alpha = 0.16f),
    error = AcademyPalette.Ink
)

private val DarkColors = darkColorScheme(
    primary = AcademyPalette.Paper,
    onPrimary = AcademyPalette.Ink,
    background = AcademyPalette.Ink,
    onBackground = AcademyPalette.Paper,
    surface = AcademyPalette.Ink,
    onSurface = AcademyPalette.Paper,
    surfaceVariant = AcademyPalette.Ink,
    onSurfaceVariant = AcademyPalette.Paper.copy(alpha = 0.7f),
    secondary = AcademyPalette.Paper,
    onSecondary = AcademyPalette.Ink,
    tertiary = AcademyPalette.Paper.copy(alpha = 0.12f),
    onTertiary = AcademyPalette.Paper,
    outline = AcademyPalette.Paper.copy(alpha = 0.2f),
    error = AcademyPalette.Paper
)

private val AppTypography = Typography(
    displayLarge = TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 42.sp),
    headlineLarge = TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 38.sp),
    headlineMedium = TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 27.sp),
    titleLarge = TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 22.sp),
    titleMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
    bodyLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    labelSmall = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium, fontSize = 11.sp)
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
    MaterialTheme(
        colorScheme = if (useDarkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}
