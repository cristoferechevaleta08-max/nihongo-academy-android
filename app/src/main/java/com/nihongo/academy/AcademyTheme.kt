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
        drawPath(fuji, SakuraNightTokens.SurfaceVariant)
        val snow = Path().apply {
            moveTo(w * 0.415f, h * 0.40f)
            lineTo(w * 0.48f, h * 0.16f)
            lineTo(w * 0.545f, h * 0.40f)
            lineTo(w * 0.505f, h * 0.36f)
            lineTo(w * 0.48f, h * 0.43f)
            lineTo(w * 0.455f, h * 0.36f)
            close()
        }
        drawPath(snow, SakuraNightTokens.OnSurface.copy(alpha = 0.92f))

        // Pagoda de tres niveles
        val px = w * 0.82f
        val roofColor = SakuraNightTokens.PrimaryDeep
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
                color = SakuraNightTokens.SurfaceElevated,
                topLeft = Offset(px - half * 0.48f, y + h * 0.035f),
                size = Size(half * 0.96f, h * 0.12f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(5f, 5f)
            )
        }
        drawLine(roofColor, Offset(px, h * 0.17f), Offset(px, h * 0.30f), strokeWidth = 2.dp.toPx())

        // Zorro estilizado a la izquierda
        val fx = w * 0.14f
        val fy = h * 0.60f
        val fox = SakuraNightTokens.FoxFur
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
        drawCircle(SakuraNightTokens.FoxMuzzle, radius = h * 0.105f, center = Offset(fx, fy + h * 0.075f))
        drawCircle(SakuraNightTokens.Background, radius = h * 0.018f, center = Offset(fx - w * 0.035f, fy - h * 0.015f))
        drawCircle(SakuraNightTokens.Background, radius = h * 0.018f, center = Offset(fx + w * 0.035f, fy - h * 0.015f))
        drawCircle(SakuraNightTokens.Background, radius = h * 0.018f, center = Offset(fx, fy + h * 0.075f))
        drawLine(
            SakuraNightTokens.ScarfRed,
            Offset(fx - w * 0.105f, fy + h * 0.16f),
            Offset(fx + w * 0.105f, fy + h * 0.16f),
            strokeWidth = h * 0.075f,
            cap = androidx.compose.ui.graphics.StrokeCap.Round
        )
        drawLine(
            SakuraNightTokens.ScarfRed,
            Offset(fx + w * 0.055f, fy + h * 0.16f),
            Offset(fx + w * 0.095f, fy + h * 0.31f),
            strokeWidth = h * 0.055f,
            cap = androidx.compose.ui.graphics.StrokeCap.Round
        )

        // Pequeños pétalos de sakura, sin competir con el contenido.
        listOf(Offset(w * 0.22f, h * 0.20f), Offset(w * 0.72f, h * 0.20f), Offset(w * 0.93f, h * 0.13f)).forEach {
            drawCircle(SakuraNightTokens.Primary.copy(alpha = 0.7f), radius = h * 0.025f, center = it)
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
                listOf(SakuraNightTokens.PrimaryDeep, SakuraNightTokens.Primary, SakuraNightTokens.PrimaryLight),
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
        drawPath(flap, SakuraNightTokens.PrimaryLight.copy(alpha = 0.9f))
        drawLine(
            SakuraNightTokens.White.copy(alpha = 0.55f),
            Offset(left + width * 0.04f, bottom - height * 0.34f),
            Offset(left + width * 0.5f, bottom - height * 0.02f),
            strokeWidth = 1.5.dp.toPx()
        )
        drawLine(
            SakuraNightTokens.White.copy(alpha = 0.55f),
            Offset(right - width * 0.04f, bottom - height * 0.34f),
            Offset(left + width * 0.5f, bottom - height * 0.02f),
            strokeWidth = 1.5.dp.toPx()
        )
        drawCircle(
            color = SakuraNightTokens.ScarfRed,
            radius = height * 0.105f,
            center = Offset(left + width * 0.5f, top + height * 0.61f)
        )
        drawCircle(
            color = SakuraNightTokens.White.copy(alpha = 0.85f),
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
            SakuraNightTokens.White.copy(alpha = 0.28f)
        )
    }
}
