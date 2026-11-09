package com.nihongo.academy

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

/** Paisaje japonés monocromático: luna, montañas y pagoda, sin personajes. */
@Composable
internal fun AcademyMotif(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme
    Canvas(modifier = modifier.fillMaxWidth().height(144.dp)) {
        val w = size.width
        val h = size.height
        val ink = colors.onSurface
        val muted = colors.onSurfaceVariant
        val surface = colors.surfaceVariant

        // Luna
        drawCircle(
            brush = Brush.radialGradient(listOf(ink.copy(alpha = 0.16f), ink.copy(alpha = 0.04f))),
            radius = h * 0.29f,
            center = Offset(w * 0.72f, h * 0.34f)
        )
        drawCircle(color = surface, radius = h * 0.235f, center = Offset(w * 0.72f, h * 0.34f))

        // Montañas en dos planos suaves
        val distant = Path().apply {
            moveTo(0f, h * 0.91f)
            lineTo(w * 0.24f, h * 0.43f)
            lineTo(w * 0.43f, h * 0.91f)
            close()
        }
        drawPath(distant, muted.copy(alpha = 0.22f))
        val near = Path().apply {
            moveTo(w * 0.18f, h)
            lineTo(w * 0.47f, h * 0.53f)
            lineTo(w * 0.76f, h)
            close()
        }
        drawPath(near, muted.copy(alpha = 0.13f))

        // Pagoda de tres tejados
        val px = w * 0.86f
        for (level in 0..2) {
            val y = h * (0.49f + level * 0.16f)
            val half = w * (0.105f - level * 0.017f)
            val roof = Path().apply {
                moveTo(px - half, y + h * 0.035f)
                lineTo(px, y - h * 0.035f)
                lineTo(px + half, y + h * 0.035f)
                lineTo(px + half * 0.78f, y + h * 0.035f)
                lineTo(px, y + h * 0.006f)
                lineTo(px - half * 0.78f, y + h * 0.035f)
                close()
            }
            drawPath(roof, ink.copy(alpha = 0.88f))
            drawRoundRect(
                color = surface,
                topLeft = Offset(px - half * 0.42f, y + h * 0.04f),
                size = Size(half * 0.84f, h * 0.105f),
                cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
            )
        }
        drawLine(ink, Offset(px, h * 0.20f), Offset(px, h * 0.43f), strokeWidth = 2.dp.toPx())

        // Nubes y líneas de viento
        for (i in 0..2) {
            val x = w * (0.08f + i * 0.055f)
            val y = h * (0.23f + i * 0.09f)
            drawLine(muted.copy(alpha = 0.6f), Offset(x, y), Offset(x + w * 0.13f, y), 1.4.dp.toPx())
            drawLine(muted.copy(alpha = 0.45f), Offset(x + w * 0.035f, y + h * 0.045f), Offset(x + w * 0.16f, y + h * 0.045f), 1.2.dp.toPx())
        }
    }
}

/** Sobre visual de la colección; conserva su presentación sin colores de acento. */
@Composable
internal fun AcademyEnvelope(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme
    Canvas(modifier = modifier.fillMaxWidth().height(126.dp)) {
        val left = size.width * 0.27f
        val top = size.height * 0.10f
        val width = size.width * 0.46f
        val height = size.height * 0.78f
        val right = left + width
        val bottom = top + height
        val ink = colors.onSurface
        val surface = colors.surfaceVariant

        drawRoundRect(
            color = surface,
            topLeft = Offset(left, top),
            size = Size(width, height),
            cornerRadius = CornerRadius(18.dp.toPx(), 18.dp.toPx())
        )
        val flap = Path().apply {
            moveTo(left, top + height * 0.08f)
            lineTo(left + width * 0.5f, top + height * 0.62f)
            lineTo(right, top + height * 0.08f)
            close()
        }
        drawPath(flap, ink.copy(alpha = 0.12f))
        drawLine(ink.copy(alpha = 0.5f), Offset(left + width * 0.04f, bottom - height * 0.34f), Offset(left + width * 0.5f, bottom - height * 0.02f), 1.5.dp.toPx())
        drawLine(ink.copy(alpha = 0.5f), Offset(right - width * 0.04f, bottom - height * 0.34f), Offset(left + width * 0.5f, bottom - height * 0.02f), 1.5.dp.toPx())
        drawCircle(ink.copy(alpha = 0.7f), height * 0.09f, Offset(left + width * 0.5f, top + height * 0.61f))
    }
}
