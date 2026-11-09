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
        secondary = AcademyColors.Gray300,
        onSecondary = AcademyColors.Black,
        secondaryContainer = AcademyColors.Gray900,
        onSecondaryContainer = AcademyColors.White,
        background = AcademyColors.Black,
        onBackground = AcademyColors.White,
        surface = AcademyColors.Gray900,
        onSurface = AcademyColors.White,
        surfaceVariant = AcademyColors.Gray900,
        onSurfaceVariant = AcademyColors.Gray300,
        outline = AcademyColors.Gray700,
        outlineVariant = AcademyColors.Gray900,
        error = AcademyColors.Gray300,
        onError = AcademyColors.Black
    )
} else {
    lightColorScheme(
        primary = AcademyColors.Black,
        onPrimary = AcademyColors.White,
        secondary = AcademyColors.Gray700,
        onSecondary = AcademyColors.White,
        secondaryContainer = AcademyColors.Gray100,
        onSecondaryContainer = AcademyColors.Black,
        background = AcademyColors.White,
        onBackground = AcademyColors.Black,
        surface = AcademyColors.White,
        onSurface = AcademyColors.Black,
        surfaceVariant = AcademyColors.Gray100,
        onSurfaceVariant = AcademyColors.Gray700,
        outline = AcademyColors.Gray300,
        outlineVariant = AcademyColors.Gray100,
        error = AcademyColors.Gray900,
        onError = AcademyColors.White
    )
}

/** Por defecto respeta el tema del sistema; una preferencia guardada puede sobrescribirlo. */
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