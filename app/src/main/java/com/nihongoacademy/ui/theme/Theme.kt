package com.nihongoacademy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.nihongoacademy.data.PreferencesStore

private val DarkColors = darkColorScheme(
    primary = AcademyColors.ButtonPrimary,
    onPrimary = AcademyColors.ButtonPrimaryText,
    secondary = AcademyColors.Sakura,
    onSecondary = AcademyColors.Background,
    tertiary = AcademyColors.JapaneseRed,
    onTertiary = Color.White,
    background = AcademyColors.Background,
    onBackground = AcademyColors.TextPrimary,
    surface = AcademyColors.Surface,
    onSurface = AcademyColors.TextPrimary,
    surfaceVariant = AcademyColors.SurfaceElevated,
    onSurfaceVariant = AcademyColors.TextSecondary,
    outline = AcademyColors.Border,
    error = Color(0xFFFF7B83),
    onError = Color(0xFF24080B)
)

private val LightColors = lightColorScheme(
    primary = Color(0xFFB85F78),
    onPrimary = Color.White,
    secondary = Color(0xFFB85F78),
    onSecondary = Color.White,
    tertiary = AcademyColors.JapaneseRed,
    background = Color(0xFFF7F4F2),
    onBackground = Color(0xFF171719),
    surface = Color.White,
    onSurface = Color(0xFF171719),
    surfaceVariant = Color(0xFFECE8E7),
    onSurfaceVariant = Color(0xFF66636A),
    outline = Color(0xFFD6D0D1)
)

@Composable
fun NihongoTheme(
    darkTheme: Boolean? = null,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val themeModeFlow = remember(context) { PreferencesStore(context).themeModeFlow() }
    val themeMode by themeModeFlow.collectAsState(initial = "dark")
    val useDarkTheme = darkTheme ?: when (themeMode) {
        "light" -> false
        "dark" -> true
        // Nihongo Academy now opens in the reference dark appearance by default.
        else -> if (themeMode == "system") true else isSystemInDarkTheme()
    }

    MaterialTheme(
        colorScheme = if (useDarkTheme) DarkColors else LightColors,
        typography = AcademyTypography,
        content = content
    )
}