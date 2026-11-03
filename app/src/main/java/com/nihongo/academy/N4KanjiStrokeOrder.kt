package com.nihongo.academy

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
internal fun N4KanjiStrokeOrder(character: String, modifier: Modifier = Modifier) {
    val paths = remember(character) {
        n4KanjiStrokePaths[character].orEmpty().map { data ->
            PathParser().parsePathString(data).toPath()
        }
    }
    var replayKey by remember(character) { mutableIntStateOf(0) }
    var visibleStrokes by remember(character) { mutableIntStateOf(0) }
    var practiceMode by remember(character) { mutableStateOf(false) }
    var handwriting by remember(character) { mutableStateOf<List<List<Offset>>>(emptyList()) }

    LaunchedEffect(character, replayKey, paths.size) {
        visibleStrokes = 0
        for (index in paths.indices) {
            delay(420)
            visibleStrokes = index + 1
        }
    }

    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Orden de trazos", style = MaterialTheme.typography.titleMedium)
        Text(
            if (paths.isEmpty()) "No hay datos de trazos disponibles para este carácter."
            else "Trazo ${visibleStrokes.coerceAtMost(paths.size)} de ${paths.size}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        val strokeColor = MaterialTheme.colorScheme.primary
        val guideColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.10f)
        Canvas(
            Modifier.fillMaxWidth()
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(18.dp))
        ) {
            val scaleX = size.width / 109f
            val scaleY = size.height / 109f
            withTransform({ scale(scaleX, scaleY, pivot = Offset.Zero) }) {
                drawLine(guideColor, Offset(54.5f, 4f), Offset(54.5f, 105f), strokeWidth = 0.7f)
                drawLine(guideColor, Offset(4f, 54.5f), Offset(105f, 54.5f), strokeWidth = 0.7f)
                paths.take(visibleStrokes).forEach { path ->
                    drawPath(path, strokeColor, style = Stroke(width = 3.2f, cap = StrokeCap.Round))
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { replayKey++ }, modifier = Modifier.weight(1f)) {
                Text("Repetir animación")
            }
            Button(onClick = { practiceMode = !practiceMode }, modifier = Modifier.weight(1f)) {
                Text(if (practiceMode) "Ocultar práctica" else "Practicar escritura")
            }
        }
        if (practiceMode) {
            Text("Dibujá el kanji con el dedo siguiendo el modelo.", style = MaterialTheme.typography.bodySmall)
            Canvas(
                Modifier.fillMaxWidth()
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(18.dp))
                    .pointerInput(character) {
                        detectDragGestures(
                            onDragStart = { point -> handwriting = handwriting + listOf(listOf(point)) },
                            onDrag = { change, _ ->
                                change.consume()
                                if (handwriting.isNotEmpty()) {
                                    handwriting = handwriting.dropLast(1) + listOf(handwriting.last() + change.position)
                                }
                            }
                        )
                    }
            ) {
                drawContext.transform.scale(size.width / 109f, size.height / 109f, Offset.Zero)
                val unitScale = size.width / 109f
                drawContext.transform.scale(1f, size.height / size.width, Offset.Zero)
                paths.forEach { drawPath(it, guideColor, style = Stroke(width = 2.4f)) }
                handwriting.forEach { line ->
                    line.zipWithNext().forEach { (start, end) ->
                        drawLine(
                            strokeColor,
                            Offset(start.x / unitScale, start.y / unitScale),
                            Offset(end.x / unitScale, end.y / unitScale),
                            strokeWidth = 3.5f,
                            cap = StrokeCap.Round
                        )
                    }
                }
            }
            OutlinedButton(onClick = { handwriting = emptyList() }, modifier = Modifier.fillMaxWidth()) {
                Text("Borrar trazado")
            }
            Text(
                "La práctica es libre: todavía no califica automáticamente la dirección ni el orden de tus trazos.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
