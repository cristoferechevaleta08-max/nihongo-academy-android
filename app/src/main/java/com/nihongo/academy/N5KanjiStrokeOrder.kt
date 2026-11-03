package com.nihongo.academy

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
internal fun N5KanjiStrokeOrder(entry: N5KanjiEntry) {
    val paths: List<Path> = remember(entry.character) {
        n5KanjiStrokePaths[entry.character].orEmpty().map { data ->
            PathParser().parsePathString(data).toPath()
        }
    }
    var replayKey by remember(entry.character) { mutableIntStateOf(0) }
    var activeStroke by remember(entry.character) { mutableIntStateOf(0) }
    val progress = remember(entry.character) { Animatable(0f) }
    val completedColor = MaterialTheme.colorScheme.primary
    val guideColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.13f)

    LaunchedEffect(entry.character, replayKey) {
        activeStroke = 0
        progress.snapTo(0f)
        paths.indices.forEach { index ->
            activeStroke = index
            progress.snapTo(0f)
            progress.animateTo(1f, animationSpec = tween(durationMillis = 620))
            delay(180)
        }
        activeStroke = paths.size
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Orden de escritura", style = MaterialTheme.typography.titleMedium)
            OutlinedButton(
                onClick = { replayKey++ },
                enabled = paths.isNotEmpty()
            ) { Text("Reproducir de nuevo") }
        }
        if (paths.isEmpty()) {
            Text("La guía de trazos todavía no está disponible para este kanji.")
        } else {
            Canvas(Modifier.fillMaxWidth().height(220.dp)) {
                val scale = minOf(size.width, size.height) * 0.82f / 109f
                val left = (size.width - 109f * scale) / 2f
                val top = (size.height - 109f * scale) / 2f
                withTransform({
                    translate(left, top)
                    scale(scale, scale, Offset.Zero)
                }) {
                    paths.forEachIndexed { index, path ->
                        drawPath(
                            path = path,
                            color = guideColor,
                            style = Stroke(width = 3.2f, cap = StrokeCap.Round)
                        )
                        when {
                            index < activeStroke -> drawPath(
                                path = path,
                                color = completedColor,
                                style = Stroke(width = 3.2f, cap = StrokeCap.Round)
                            )
                            index == activeStroke && activeStroke < paths.size -> {
                                val measure = PathMeasure().apply { setPath(path, false) }
                                val partial = Path()
                                measure.getSegment(
                                    0f,
                                    measure.length * progress.value,
                                    partial,
                                    true
                                )
                                drawPath(
                                    path = partial,
                                    color = completedColor,
                                    style = Stroke(width = 3.2f, cap = StrokeCap.Round)
                                )
                            }
                        }
                    }
                }
            }
            Text(
                if (activeStroke >= paths.size) "Secuencia completa · ${paths.size} trazos"
                else "Trazando ${activeStroke + 1} de ${paths.size}",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "Observá cómo se dibuja cada trazo en su orden correcto.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
