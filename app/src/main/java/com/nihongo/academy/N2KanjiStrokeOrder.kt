package com.nihongo.academy

import android.content.Context
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.vector.PathParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.delay
import org.json.JSONObject
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

private suspend fun loadN2KanjiStrokePaths(context: Context, character: String): List<String> =
    withContext(Dispatchers.IO) {
        val code = character.codePointAt(0).toString(16).padStart(5, '0')
        val directory = File(context.filesDir, "kanji-strokes")
        val cached = File(directory, "$code.json")
        fun decode(json: String): List<String> {
            val array = JSONObject(json).getJSONArray("strokes")
            return List(array.length()) { index -> array.getString(index) }
        }
        try {
            if (cached.exists()) {
                val cachedPaths = runCatching { decode(cached.readText()) }.getOrNull()
                if (!cachedPaths.isNullOrEmpty()) return@withContext cachedPaths
                // A damaged or incomplete cache must not permanently block a fresh download.
                cached.delete()
            }
            val connection = (URL("https://raw.githubusercontent.com/boomtakalaka/kanjivg-json/master/kanji_json/$code.json").openConnection() as HttpURLConnection).apply {
                connectTimeout = 10000
                readTimeout = 10000
                requestMethod = "GET"
            }
            try {
                val body = connection.inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
                val paths = decode(body)
                if (paths.isNotEmpty()) {
                    directory.mkdirs()
                    cached.writeText(body)
                }
                paths
            } finally {
                connection.disconnect()
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

@Composable
internal fun N2KanjiStrokeOrder(character: String, expectedStrokeCount: Int) {
    val context = LocalContext.current
    var rawPaths by remember(character) { mutableStateOf<List<String>>(emptyList()) }
    var loading by remember(character) { mutableStateOf(true) }
    var replayKey by remember(character) { mutableIntStateOf(0) }
    var loadKey by remember(character) { mutableIntStateOf(0) }
    var activeStroke by remember(character) { mutableIntStateOf(0) }
    var practiceMode by remember(character) { mutableStateOf(false) }
    var handwriting by remember(character) { mutableStateOf<List<List<Offset>>>(emptyList()) }
    val progress = remember(character) { Animatable(0f) }
    // No mostrar una secuencia parcial: si un path está dañado, se descarta la guía completa.
    val paths = remember(character, rawPaths) {
        runCatching {
            rawPaths.map { data ->
                require(data.isNotBlank()) { "Empty KanjiVG path" }
                PathParser().parsePathString(data).toPath()
            }
        }.getOrElse { emptyList() }
    }
    val strokeColor = MaterialTheme.colorScheme.primary
    val guideColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.13f)

    LaunchedEffect(character, loadKey) {
        loading = true
        rawPaths = loadN2KanjiStrokePaths(context, character)
        loading = false
    }
    LaunchedEffect(character, replayKey, paths.size) {
        if (paths.isEmpty()) return@LaunchedEffect
        activeStroke = 0
        paths.indices.forEach { index ->
            activeStroke = index
            progress.snapTo(0f)
            progress.animateTo(1f, animationSpec = tween(620))
            delay(180)
        }
        activeStroke = paths.size
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
        Text("Orden de trazos", style = MaterialTheme.typography.titleMedium)
        Text(
            when {
                loading -> "Cargando guía de escritura…"
                paths.isEmpty() -> "No se pudo cargar la guía. Comprobá tu conexión e intentá de nuevo."
                else -> "Trazo ${activeStroke.coerceAtMost(paths.size)} de ${paths.size}"
            },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (paths.isNotEmpty()) {
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
                            index < activeStroke -> drawPath(path, strokeColor, style = Stroke(width = 3.2f, cap = StrokeCap.Round))
                            index == activeStroke && activeStroke < paths.size -> {
                                val measure = PathMeasure().apply { setPath(path, false) }
                                val partial = Path()
                                measure.getSegment(0f, measure.length * progress.value, partial, true)
                                drawPath(partial, strokeColor, style = Stroke(width = 3.2f, cap = StrokeCap.Round))
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
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = { replayKey++ }, modifier = Modifier.weight(1f)) { Text("Repetir animación") }
                Button(onClick = { practiceMode = !practiceMode }, modifier = Modifier.weight(1f)) {
                    Text(if (practiceMode) "Ocultar práctica" else "Practicar escritura")
                }
            }
            if (practiceMode) {
                Text("Dibujá el kanji con el dedo siguiendo el modelo.", style = MaterialTheme.typography.bodySmall)
                Canvas(
                    Modifier.fillMaxWidth().aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
                        .pointerInput(character) {
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
                            drawLine(strokeColor, start, end, strokeWidth = 8f, cap = StrokeCap.Round)
                        }
                    }
                }
                OutlinedButton(onClick = { handwriting = emptyList() }, modifier = Modifier.fillMaxWidth()) { Text("Borrar trazado") }
                Text("La práctica es libre; todavía no evalúa automáticamente la dirección ni el orden.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else if (!loading) {
            OutlinedButton(onClick = { loadKey++ }, modifier = Modifier.fillMaxWidth()) {
                Text("Reintentar carga")
            }
        }
        if (paths.isNotEmpty() && paths.size != expectedStrokeCount) {
            Text("La guía contiene ${paths.size} trazos; la ficha indica ${expectedStrokeCount}.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }
    }
}
