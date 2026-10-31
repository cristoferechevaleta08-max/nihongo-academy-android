package com.nihongoacademy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AcademyPalette {
    val Ink = Color(0xFF111111)
    val Paper = Color(0xFFF7F3EE)
    val Card = Color(0xFFFFFFFF)
    val Mist = Color(0xFFF2F0EC)
    val Olive = Color(0xFFBBC7A5)
    val Sage = Color(0xFF6C8A73)
    val Sand = Color(0xFFE9DCC7)
    val Clay = Color(0xFFBA8F6A)
    val Bronze = Color(0xFF7A5C46)
    val Accent = Color(0xFF202120)
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
    secondary = AcademyPalette.Sage,
    onSecondary = Color.White,
    tertiary = AcademyPalette.Sand,
    outline = Color(0xFFDED7CF)
)

private val DarkColors = darkColorScheme(
    primary = Color.White,
    onPrimary = AcademyPalette.Ink,
    background = Color(0xFF121212),
    onBackground = Color(0xFFEDEDED),
    surface = Color(0xFF1A1A1A),
    onSurface = Color(0xFFF1F1F1),
    surfaceVariant = Color(0xFF262626),
    secondary = AcademyPalette.Sage,
    onSecondary = Color.Black,
    tertiary = AcademyPalette.Clay,
    outline = Color(0xFF3A3A3A)
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
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}
