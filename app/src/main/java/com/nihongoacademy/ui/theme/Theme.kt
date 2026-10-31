package com.nihongoacademy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

private val darkColorPalette = darkColorScheme(
    primary = BlackPalette.Black,
    onPrimary = BlackPalette.White,
    background = BlackPalette.Black,
    onBackground = BlackPalette.White,
    surface = BlackPalette.Black,
    onSurface = BlackPalette.White,
    surfaceVariant = BlackPalette.Slate,
    secondary = BlackPalette.Charcoal,
    onSecondary = BlackPalette.White
)

private val lightColorPalette = lightColorScheme(
    primary = BlackPalette.Black,
    onPrimary = BlackPalette.White,
    background = BlackPalette.White,
    onBackground = BlackPalette.Black,
    surface = BlackPalette.White,
    onSurface = BlackPalette.Black,
    surfaceVariant = BlackPalette.Faint,
    secondary = BlackPalette.GrayLight,
    onSecondary = BlackPalette.Black
)

object BlackPalette {
    const val Black = 0xFF000000
    const val Jet = 0xFF0A0A0A
    const val Carbon = 0xFF141414
    const val Charcoal = 0xFF1F1F1F
    const val Slate = 0xFF2E2E2E
    const val GrayMid = 0xFF6B6B6B
    const val GraySoft = 0xFFA3A3A3
    const val GrayLight = 0xFFD9D9D9
    const val Faint = 0xFFF5F5F5
    const val White = 0xFFFFFFFF
}

val AppFontSans = FontFamily.SansSerif

@Composable
fun NihongoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) darkColorPalette else lightColorPalette
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
