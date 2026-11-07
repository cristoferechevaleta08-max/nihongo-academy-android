package com.nihongo.academy

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/** Tokens centrales de la identidad visual Sakura de Nihongo Academy. */
internal object AcademyPalette {
    val Background = Color(0xFF101016)
    val Surface = Color(0xFF1B1922)
    val SurfaceElevated = Color(0xFF26222E)
    val SurfaceVariant = Color(0xFF302A38)
    val Sakura = Color(0xFFF29ABB)
    val SakuraLight = Color(0xFFFFC4D8)
    val SakuraDeep = Color(0xFFC65D87)
    val ScarfRed = Color(0xFFE65B69)
    val TextPrimary = Color(0xFFF8F2F6)
    val TextSecondary = Color(0xFFC5B9C3)
    val Outline = Color(0xFF514652)
    val Success = Color(0xFF9ED7B5)
    val Error = Color(0xFFFF9EAD)

    val LightBackground = Color(0xFFFFF8FB)
    val LightSurface = Color(0xFFFFFFFF)
    val LightSurfaceVariant = Color(0xFFF6EAF0)
    val LightText = Color(0xFF30232B)
    val LightMuted = Color(0xFF75636E)
}

internal fun academyColorScheme(dark: Boolean) = if (dark) {
    darkColorScheme(
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
        surfaceVariant = AcademyPalette.SurfaceVariant,
        onSurfaceVariant = AcademyPalette.TextSecondary,
        outline = AcademyPalette.Outline,
        error = AcademyPalette.Error,
        onError = Color(0xFF3B101B)
    )
} else {
    lightColorScheme(
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
}

internal val AcademyTypography = Typography(
    displayLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 36.sp, lineHeight = 43.sp),
    displayMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 39.sp),
    displaySmall = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 28.sp, lineHeight = 35.sp),
    headlineLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 28.sp, lineHeight = 35.sp),
    headlineMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 31.sp),
    headlineSmall = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 21.sp, lineHeight = 28.sp),
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

internal val AcademyShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

/**
 * Ilustración vectorial ligera: zorro con bufanda, Fuji y pagoda.
 * No carga imágenes ni depende de servicios externos.
 */
@Composable
internal fun AcademyMotif(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxWidth().height(112.dp)) {
        val w = size.width
        val h = size.height

        // Monte Fuji
        val fuji = Path().apply {
            moveTo(w * 0.28f, h * 0.88f)
            lineTo(w * 0.48f, h * 0.16f)
            lineTo(w * 0.68f, h * 0.88f)
            close()
        }
        drawPath(fuji, AcademyPalette.SurfaceVariant)
        val snow = Path().apply {
            moveTo(w * 0.415f, h * 0.40f)
            lineTo(w * 0.48f, h * 0.16f)
            lineTo(w * 0.545f, h * 0.40f)
            lineTo(w * 0.505f, h * 0.36f)
            lineTo(w * 0.48f, h * 0.43f)
            lineTo(w * 0.455f, h * 0.36f)
            close()
        }
        drawPath(snow, AcademyPalette.TextPrimary.copy(alpha = 0.92f))

        // Pagoda de tres niveles
        val px = w * 0.82f
        val roofColor = AcademyPalette.SakuraDeep
        for (i in 0..2) {
            val y = h * (0.40f + i * 0.19f)
            val half = w * (0.105f - i * 0.018f)
            val roof = Path().apply {
                moveTo(px - half, y + h * 0.035f)
                lineTo(px, y - h * 0.045f)
                lineTo(px + half, y + h * 0.035f)
                lineTo(px + half * 0.78f, y + h * 0.035f)
                lineTo(px, y + h * 0.005f)
                lineTo(px - half * 0.78f, y + h * 0.035f)
                close()
            }
            drawPath(roof, roofColor.copy(alpha = 0.9f - i * 0.12f))
            drawRoundRect(
                color = AcademyPalette.SurfaceElevated,
                topLeft = Offset(px - half * 0.48f, y + h * 0.035f),
                size = Size(half * 0.96f, h * 0.12f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(5f, 5f)
            )
        }
        drawLine(roofColor, Offset(px, h * 0.17f), Offset(px, h * 0.30f), strokeWidth = 2.dp.toPx())

        // Zorro estilizado a la izquierda
        val fx = w * 0.14f
        val fy = h * 0.60f
        val fox = Color(0xFFE9A66C)
        val ears = Path().apply {
            moveTo(fx - w * 0.075f, fy - h * 0.02f)
            lineTo(fx - w * 0.065f, fy - h * 0.34f)
            lineTo(fx - w * 0.005f, fy - h * 0.12f)
            lineTo(fx + w * 0.055f, fy - h * 0.34f)
            lineTo(fx + w * 0.078f, fy - h * 0.02f)
            close()
        }
        drawPath(ears, fox)
        drawCircle(fox, radius = h * 0.235f, center = Offset(fx, fy))
        drawCircle(Color(0xFFFFE7D6), radius = h * 0.105f, center = Offset(fx, fy + h * 0.075f))
        drawCircle(AcademyPalette.Background, radius = h * 0.018f, center = Offset(fx - w * 0.035f, fy - h * 0.015f))
        drawCircle(AcademyPalette.Background, radius = h * 0.018f, center = Offset(fx + w * 0.035f, fy - h * 0.015f))
        drawCircle(AcademyPalette.Background, radius = h * 0.018f, center = Offset(fx, fy + h * 0.075f))
        drawLine(
            AcademyPalette.ScarfRed,
            Offset(fx - w * 0.105f, fy + h * 0.16f),
            Offset(fx + w * 0.105f, fy + h * 0.16f),
            strokeWidth = h * 0.075f,
            cap = androidx.compose.ui.graphics.StrokeCap.Round
        )
        drawLine(
            AcademyPalette.ScarfRed,
            Offset(fx + w * 0.055f, fy + h * 0.16f),
            Offset(fx + w * 0.095f, fy + h * 0.31f),
            strokeWidth = h * 0.055f,
            cap = androidx.compose.ui.graphics.StrokeCap.Round
        )

        // Pequeños pétalos de sakura, sin competir con el contenido.
        listOf(Offset(w * 0.22f, h * 0.20f), Offset(w * 0.72f, h * 0.20f), Offset(w * 0.93f, h * 0.13f)).forEach {
            drawCircle(AcademyPalette.Sakura.copy(alpha = 0.7f), radius = h * 0.025f, center = it)
        }
    }
}

/** Sobre ilustrado de la colección; es solo presentación y no altera la recompensa diaria. */
@Composable
internal fun AcademyEnvelope(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxWidth().height(126.dp)) {
        val left = size.width * 0.27f
        val top = size.height * 0.10f
        val width = size.width * 0.46f
        val height = size.height * 0.78f
        val right = left + width
        val bottom = top + height

        drawRoundRect(
            brush = Brush.linearGradient(
                listOf(AcademyPalette.SakuraDeep, AcademyPalette.Sakura, AcademyPalette.SakuraLight),
                start = Offset(left, top),
                end = Offset(right, bottom)
            ),
            topLeft = Offset(left, top),
            size = Size(width, height),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(18.dp.toPx(), 18.dp.toPx())
        )
        val flap = Path().apply {
            moveTo(left, top + height * 0.08f)
            lineTo(left + width * 0.5f, top + height * 0.62f)
            lineTo(right, top + height * 0.08f)
            close()
        }
        drawPath(flap, AcademyPalette.SakuraLight.copy(alpha = 0.9f))
        drawLine(
            Color.White.copy(alpha = 0.55f),
            Offset(left + width * 0.04f, bottom - height * 0.34f),
            Offset(left + width * 0.5f, bottom - height * 0.02f),
            strokeWidth = 1.5.dp.toPx()
        )
        drawLine(
            Color.White.copy(alpha = 0.55f),
            Offset(right - width * 0.04f, bottom - height * 0.34f),
            Offset(left + width * 0.5f, bottom - height * 0.02f),
            strokeWidth = 1.5.dp.toPx()
        )
        drawCircle(
            color = AcademyPalette.ScarfRed,
            radius = height * 0.105f,
            center = Offset(left + width * 0.5f, top + height * 0.61f)
        )
        drawCircle(
            color = Color.White.copy(alpha = 0.85f),
            radius = height * 0.035f,
            center = Offset(left + width * 0.5f, top + height * 0.61f)
        )
        drawPath(
            Path().apply {
                moveTo(left + width * 0.15f, bottom - height * 0.13f)
                lineTo(left + width * 0.27f, bottom - height * 0.25f)
                lineTo(left + width * 0.35f, bottom - height * 0.13f)
                lineTo(left + width * 0.27f, bottom - height * 0.02f)
                close()
            },
            Color.White.copy(alpha = 0.28f)
        )
    }
}
