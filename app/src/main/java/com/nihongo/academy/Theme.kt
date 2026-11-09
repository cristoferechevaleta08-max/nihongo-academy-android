package com.nihongo.academy

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

internal fun academyColorScheme(dark: Boolean) = if (dark) {
    darkColorScheme(
        primary = AcademyColors.White,
        onPrimary = AcademyColors.Black,
        secondary = AcademyColors.Lavender,
        onSecondary = AcademyColors.Black,
        secondaryContainer = AcademyColors.Gray900,
        onSecondaryContainer = AcademyColors.White,
        background = AcademyColors.Black,
        onBackground = AcademyColors.White,
        surface = AcademyColors.Gray900,
        onSurface = AcademyColors.White,
        surfaceVariant = AcademyColors.Gray900,
        onSurfaceVariant = AcademyColors.Lavender,
        outline = AcademyColors.Gray700,
        outlineVariant = AcademyColors.Gray900,
        error = AcademyColors.Gray300,
        onError = AcademyColors.Black
    )
} else {
    lightColorScheme(
        primary = AcademyColors.Ink,
        onPrimary = AcademyColors.White,
        secondary = AcademyColors.Lavender,
        onSecondary = AcademyColors.Ink,
        secondaryContainer = AcademyColors.Gray100,
        onSecondaryContainer = AcademyColors.Ink,
        background = AcademyColors.White,
        onBackground = AcademyColors.Ink,
        surface = AcademyColors.White,
        onSurface = AcademyColors.Ink,
        surfaceVariant = AcademyColors.Lavender,
        onSurfaceVariant = AcademyColors.InkMuted,
        outline = AcademyColors.Outline,
        outlineVariant = AcademyColors.Lavender
    )
}

@Composable
internal fun NihongoAcademyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = academyColorScheme(darkTheme),
        typography = AcademyTypography,
        shapes = AcademyShapes,
        content = content
    )
}
