package com.nihongo.academy

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

internal fun academyColorScheme(dark: Boolean) = if (dark) {
    darkColorScheme(
        primary = SakuraNightTokens.Primary,
        onPrimary = SakuraNightTokens.OnPrimary,
        secondary = SakuraNightTokens.Primary,
        onSecondary = SakuraNightTokens.OnPrimary,
        secondaryContainer = SakuraNightTokens.SecondaryContainer,
        onSecondaryContainer = SakuraNightTokens.OnSurface,
        background = SakuraNightTokens.Background,
        onBackground = SakuraNightTokens.OnSurface,
        surface = SakuraNightTokens.Surface,
        onSurface = SakuraNightTokens.OnSurface,
        surfaceVariant = SakuraNightTokens.SurfaceVariant,
        onSurfaceVariant = SakuraNightTokens.OnSurfaceVariant,
        outline = SakuraNightTokens.Outline,
        error = SakuraNightTokens.Error
    )
} else {
    lightColorScheme(
        primary = SakuraNightTokens.Primary,
        onPrimary = SakuraNightTokens.OnPrimary,
        secondary = SakuraNightTokens.SecondaryContainer,
        onSecondary = SakuraNightTokens.OnSurface,
        background = SakuraNightTokens.LightBackground,
        onBackground = SakuraNightTokens.LightOnSurface,
        surface = SakuraNightTokens.LightSurface,
        onSurface = SakuraNightTokens.LightOnSurface,
        surfaceVariant = SakuraNightTokens.LightSurfaceVariant,
        onSurfaceVariant = SakuraNightTokens.LightOnSurfaceVariant,
        outline = SakuraNightTokens.LightOutline,
        error = SakuraNightTokens.Error
    )
}

@Composable
internal fun NihongoAcademyTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = academyColorScheme(darkTheme),
        typography = AcademyTypography,
        shapes = AcademyShapes,
        content = content
    )
}
