package com.nihongoacademy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nihongoacademy.data.PreferencesStore

/**
 * Tokens visuales compartidos de Nihongo Academy.
 * No contiene estado ni lógica de progreso: solo define la identidad visual.
 */
object AcademyPalette {
    // Base oscura
    val Background = Color(0xFF111016)
    val Surface = Color(0xFF1B1922)
    val SurfaceElevated = Color(0xFF25222D)
    val SurfaceSoft = Color(0xFF302B38)

    // Sakura
    val Sakura = Color(0xFFF18DB5)
    val SakuraLight = Color(0xFFFFC2D8)
    val SakuraDeep = Color(0xFFC85D89)

    // Texto y estados
    val TextPrimary = Color(0xFFF7F1F5)
    val TextSecondary = Color(0xFFC2B7C1)
    val Outline = Color(0xFF514653)
    val Success = Color(0xFF9DD9B8)
    val Error = Color(0xFFFF9EAD)

    // Superficies del tema claro
    val LightBackground = Color(0xFFFFF8FB)
    val LightSurface = Color(0xFFFFFFFF)
    val LightSurfaceVariant = Color(0xFFF6EAF0)
    val LightText = Color(0xFF30232B)
    val LightMuted = Color(0xFF75636E)

    // Compatibilidad con componentes existentes que usan los nombres anteriores.
    val Ink = TextPrimary
    val Paper = LightBackground
    val Card = Surface
    val Mist = SurfaceElevated
    val Olive = SurfaceSoft
    val Sage = TextSecondary
    val Sand = SurfaceElevated
    val Clay = TextSecondary
    val Bronze = SakuraDeep
    val Accent = Sakura
    val Muted = TextSecondary
}

private val LightColors = lightColorScheme(
    primary = AcademyPalette.SakuraDeep,
    onPrimary = Color.White,
    secondary = AcademyPalette.Sakura,
    onSecondary = AcademyPalette.LightText,
    tertiary = AcademyPalette.SakuraLight,
    onTertiary = AcademyPalette.LightText,
    background = AcademyPalette.LightBackground,
    onBackground = AcademyPalette.LightText,
    surface = AcademyPalette.LightSurface,
    onSurface = AcademyPalette.LightText,
    surfaceVariant = AcademyPalette.LightSurfaceVariant,
    onSurfaceVariant = AcademyPalette.LightMuted,
    outline = Color(0xFFD9C4CF),
    error = Color(0xFFB3264D),
    onError = Color.White
)

private val DarkColors = darkColorScheme(
    primary = AcademyPalette.Sakura,
    onPrimary = Color(0xFF351B29),
    secondary = AcademyPalette.SakuraLight,
    onSecondary = Color(0xFF351B29),
    tertiary = AcademyPalette.SakuraDeep,
    onTertiary = Color.White,
    background = AcademyPalette.Background,
    onBackground = AcademyPalette.TextPrimary,
    surface = AcademyPalette.Surface,
    onSurface = AcademyPalette.TextPrimary,
    surfaceVariant = AcademyPalette.SurfaceElevated,
    onSurfaceVariant = AcademyPalette.TextSecondary,
    outline = AcademyPalette.Outline,
    error = AcademyPalette.Error,
    onError = Color(0xFF3B101B)
)

private val AppTypography = Typography(
    displayLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 36.sp, lineHeight = 43.sp),
    displayMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 39.sp),
    headlineLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 29.sp, lineHeight = 36.sp),
    headlineMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 25.sp, lineHeight = 32.sp),
    headlineSmall = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 22.sp, lineHeight = 29.sp),
    titleLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, lineHeight = 27.sp),
    titleMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 23.sp),
    titleSmall = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp),
    bodyLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 25.sp),
    bodyMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 22.sp),
    bodySmall = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 18.sp),
    labelLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 20.sp),
    labelMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 17.sp),
    labelSmall = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium, fontSize = 11.sp, lineHeight = 16.sp)
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
