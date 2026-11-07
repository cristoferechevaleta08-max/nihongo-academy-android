package com.nihongo.academy

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.input.pointer.pointerInput
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
    var practiceMode by remember(entry.character) { mutableStateOf(false) }
    var handwriting by remember(entry.character) { mutableStateOf<List<List<Offset>>>(emptyList()) }
    val progress = remember(entry.character) { Animatable(0f) }
    val completedColor = MaterialTheme.colorScheme.primary
    val guideColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.13f)

    LaunchedEffect(entry.character, replayKey) {
        activeStroke = 0
        if (paths.isNotEmpty()) {
            paths.indices.forEach { index ->
                activeStroke = index
                progress.snapTo(0f)
                progress.animateTo(1f, animationSpec = tween(durationMillis = 620))
                delay(180)
            }
            activeStroke = paths.size
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
        Text("Orden de trazos", style = MaterialTheme.typography.titleMedium)
        if (paths.isEmpty()) {
            Text(entry.character, style = MaterialTheme.typography.displayLarge)
            Text("La guía de trazos todavía no está disponible para este kanji.")
        } else {
            Text(
                if (activeStroke >= paths.size) "Secuencia completa · ${paths.size} trazos"
                else "Trazo ${activeStroke + 1} de ${paths.size}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Canvas(
                Modifier.fillMaxWidth().aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
            ) {
                val scale = minOf(size.width, size.height) * 0.92f / 109f
                val left = (size.width - 109f * scale) / 2f
                val top = (size.height - 109f * scale) / 2f
                withTransform({
                    translate(left, top)
                    scale(scale, scale, Offset.Zero)
                }) {
                    drawLine(guideColor, Offset(54.5f, 4f), Offset(54.5f, 105f), 0.7f)
                    drawLine(guideColor, Offset(4f, 54.5f), Offset(105f, 54.5f), 0.7f)
                    paths.forEachIndexed { index, path ->
                        drawPath(path, guideColor, style = Stroke(width = 3.2f, cap = StrokeCap.Round))
                        when {
                            index < activeStroke -> drawPath(path, completedColor, style = Stroke(width = 3.2f, cap = StrokeCap.Round))
                            index == activeStroke && activeStroke < paths.size -> {
                                val measure = PathMeasure().apply { setPath(path, false) }
                                val partial = Path()
                                measure.getSegment(0f, measure.length * progress.value, partial, true)
                                drawPath(partial, completedColor, style = Stroke(width = 3.2f, cap = StrokeCap.Round))
                            }
                        }
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = { replayKey++ }, modifier = Modifier.weight(1f)) { Text("Repetir animación") }
                Button(onClick = { practiceMode = !practiceMode }, modifier = Modifier.weight(1f)) {
                    Text(if (practiceMode) "Ocultar práctica" else "Practicar escritura")
                }
            }
            if (practiceMode) {
                Text("Dibuja el kanji con el dedo siguiendo el modelo.", style = MaterialTheme.typography.bodySmall)
                Canvas(
                    Modifier.fillMaxWidth().aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
                        .pointerInput(entry.character) {
                            detectDragGestures(
                                onDragStart = { point -> handwriting = handwriting + listOf(listOf(point)) },
                                onDrag = { change, _ ->
                                    change.consume()
                                    if (handwriting.isNotEmpty()) handwriting = handwriting.dropLast(1) + listOf(handwriting.last() + change.position)
                                }
                            )
                        }
                ) {
                    val scale = minOf(size.width, size.height) * 0.92f / 109f
                    val left = (size.width - 109f * scale) / 2f
                    val top = (size.height - 109f * scale) / 2f
                    withTransform({
                        translate(left, top)
                        scale(scale, scale, Offset.Zero)
                    }) {
                        paths.forEach { drawPath(it, guideColor, style = Stroke(width = 2.6f, cap = StrokeCap.Round)) }
                    }
                    handwriting.forEach { line ->
                        line.zipWithNext().forEach { (start, end) ->
                            drawLine(completedColor, start, end, strokeWidth = 8f, cap = StrokeCap.Round)
                        }
                    }
                }
                OutlinedButton(onClick = { handwriting = emptyList() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Borrar trazado")
                }
                Text(
                    "La práctica es libre; todavía no evalúa automáticamente la dirección ni el orden.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
