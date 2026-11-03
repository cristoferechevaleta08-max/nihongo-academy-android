package com.nihongo.academy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
internal fun N5KanjiStrokeOrder(entry: N5KanjiEntry) {
    val pathData = n5KanjiStrokePaths[entry.character].orEmpty()
    val paths: List<Path> = remember(entry.character) {
        pathData.map { PathParser().parsePathString(it).toPath() }
    }
    var shownStrokes by remember(entry.character) { mutableIntStateOf(0) }
    var replayKey by remember(entry.character) { mutableIntStateOf(0) }
    val completedColor = MaterialTheme.colorScheme.onSurface
    val activeColor = MaterialTheme.colorScheme.primary
    val guideColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)

    LaunchedEffect(entry.character, replayKey) {
        shownStrokes = 0
        paths.indices.forEach { index ->
            delay(560)
            shownStrokes = index + 1
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        Text("Orden de escritura", style = MaterialTheme.typography.titleMedium)
        if (paths.isEmpty()) {
            Text("La guía de trazos todavía no está disponible para este kanji.")
        } else {
            Canvas(Modifier.fillMaxWidth().height(220.dp)) {
                withTransform({
                    scale(size.width / 109f, size.height / 109f, Offset.Zero)
                }) {
                    paths.forEachIndexed { index, path ->
                        val color = when {
                            index == shownStrokes - 1 -> activeColor
                            index < shownStrokes -> completedColor
                            else -> guideColor
                        }
                        drawPath(path, color)
                    }
                }
            }
            Text(
                if (shownStrokes == paths.size) "Secuencia completa · ${paths.size} trazos"
                else "Trazo ${shownStrokes.coerceAtLeast(1)} de ${paths.size}",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            OutlinedButton(onClick = { replayKey++ }, modifier = Modifier.fillMaxWidth()) {
                Text("Reproducir orden de trazos")
            }
        }
    }
}
