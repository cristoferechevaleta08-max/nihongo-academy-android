package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.content.Unit1ContentRepository
import com.google.gson.JsonObject

@Composable
fun Unit1ContentScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember { Unit1ContentRepository(context) }
    var lessonNumber by remember { mutableIntStateOf(1) }
    var quizIndex by remember(lessonNumber) { mutableIntStateOf(0) }
    var selected by remember(lessonNumber, quizIndex) { mutableStateOf("") }
    var checked by remember(lessonNumber, quizIndex) { mutableStateOf(false) }
    var loadError by remember { mutableStateOf<String?>(null) }
    val lesson = remember(lessonNumber) { runCatching { repository.loadLesson(lessonNumber) }.getOrElse { loadError = it.message; JsonObject() } }
    val title = lesson.get("title")?.asString.orEmpty()
    val quiz = lesson.getAsJsonArray("closing_quiz")?.let { if (quizIndex in 0 until it.size()) it.get(quizIndex).asJsonObject else null }
    val options = quiz?.getAsJsonArray("options")?.map { it.asJsonObject } ?: emptyList()
    val correct = options.firstOrNull { it.get("correct")?.asBoolean == true }?.get("text")?.asString.orEmpty()

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("Japonés · Unidad 1", style = MaterialTheme.typography.labelLarge)
        Text(title.ifBlank { "Contenido no disponible" }, style = MaterialTheme.typography.headlineMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            (1..5).forEach { n ->
                if (n == lessonNumber) Button(onClick = {}, enabled = false) { Text("$n") }
                else OutlinedButton(onClick = { lessonNumber = n }) { Text("$n") }
            }
        }
        if (loadError != null) {
            Text("No se pudo cargar esta lección: $loadError", color = MaterialTheme.colorScheme.error)
        } else {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Objetivo", style = MaterialTheme.typography.titleMedium)
                    Text(lesson.get("objective")?.asString.orEmpty())
                    Text("Explicación", style = MaterialTheme.typography.titleMedium)
                    Text(lesson.get("explanation")?.asString.orEmpty())
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Práctica guiada", style = MaterialTheme.typography.titleMedium)
                    lesson.getAsJsonArray("guided_practice")?.forEach { entry ->
                        val item = entry.asJsonObject
                        Text("${item.get("kana").asString}   ·   ${item.get("reading").asString}", style = MaterialTheme.typography.headlineSmall)
                    }
                    Text("Práctica libre", style = MaterialTheme.typography.titleMedium)
                    Text(lesson.get("free_practice")?.asString.orEmpty())
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    Text("Quiz de cierre · ${quizIndex + 1}/3", style = MaterialTheme.typography.titleMedium)
                    Text(quiz?.get("prompt")?.asString.orEmpty(), style = MaterialTheme.typography.titleLarge)
                    options.forEach { option ->
                        val value = option.get("text").asString
                        OutlinedButton(onClick = { if (!checked) selected = value }, enabled = !checked, modifier = Modifier.fillMaxWidth()) {
                            Text(if (selected == value) "✓  $value" else value)
                        }
                    }
                    if (checked) {
                        Text(if (selected == correct) "Correcto" else "Respuesta: $correct", color = if (selected == correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
                        if (selected == correct) {
    Text(quiz?.get("explanation_correct")?.asString.orEmpty())
} else {
    val selectedId = options.firstOrNull { it.get("text").asString == selected }?.get("id")?.asString
    val explanation = quiz?.getAsJsonArray("distractors")?.firstOrNull {
        it.asJsonObject.get("option_id")?.asString == selectedId
    }?.asJsonObject?.get("explanation")?.asString
    Text(explanation ?: "Revisa la explicación y vuelve a intentarlo.")
}
                        Text("Material didáctico en revisión; este quiz no cuenta como examen oficial.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                    }
                    Button(onClick = {
                        if (!checked && selected.isNotBlank()) checked = true
                        else if (checked) {
                            if (quizIndex < 2) quizIndex++ else navController.navigateUp()
                            selected = ""; checked = false
                        }
                    }, enabled = if (checked) true else selected.isNotBlank(), modifier = Modifier.fillMaxWidth()) {
                        Text(if (checked) if (quizIndex < 2) "Siguiente pregunta" else "Terminar lección" else "Comprobar")
                    }
                }
            }
        }
        OutlinedButton(onClick = { navController.navigate("unit1_reviewer") }, modifier = Modifier.fillMaxWidth()) {
            Text("Abrir modo revisor")
        }
        OutlinedButton(onClick = { navController.navigateUp() }, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
    }
}

@Composable
fun Unit1ReviewerScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember { Unit1ContentRepository(context) }
    val reviewer = remember { runCatching { repository.loadReviewerMode() }.getOrNull() }
    val items = reviewer?.getAsJsonArray("items")
    val warnings = reviewer?.getAsJsonObject("warnings_summary")
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Revisión humana · Unidad 1", style = MaterialTheme.typography.headlineMedium)
        Text("Ningún borrador se publica en el examen oficial.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.error)
        Text("Audios nativos pendientes: ${warnings?.getAsJsonArray("audio_missing")?.size() ?: 0}")
        Text("Fuentes pendientes: ${warnings?.getAsJsonArray("source_note_pending")?.size() ?: 0}")
        Text("Kana fuera de alcance detectados: ${warnings?.getAsJsonArray("out_of_scope_kana")?.size() ?: 0}")
        items?.forEach { element ->
            val item = element.asJsonObject
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(item.get("id").asString, style = MaterialTheme.typography.titleSmall)
                    Text("Estado: ${item.get("status").asString}")
                    item.getAsJsonArray("warnings")?.forEach { Text("• ${it.asString}", color = MaterialTheme.colorScheme.error) }
                }
            }
        }
        OutlinedButton(onClick = { navController.navigateUp() }, modifier = Modifier.fillMaxWidth()) { Text("Volver al estudio") }
    }
}
