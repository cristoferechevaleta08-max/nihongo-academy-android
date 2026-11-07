package com.nihongo.academy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.launch
import java.time.LocalDate

private val n3KanjiLearnedKey = stringSetPreferencesKey("n3_kanji_learned")
private val n3KanjiBestQuizKey = intPreferencesKey("n3_kanji_best_quiz")
private val n3KanjiReviewScheduleKey = stringSetPreferencesKey("n3_kanji_review_schedule")

@Composable
internal fun N3KanjiScreen(
    ds: DataStore<Preferences>,
    modifier: Modifier = Modifier
) {
    val preferences by ds.data.collectAsState(initial = androidx.datastore.preferences.core.emptyPreferences())
    val learnedSet = preferences[n3KanjiLearnedKey] ?: emptySet()
    val bestScore = preferences[n3KanjiBestQuizKey] ?: 0
    val reviewSchedule = preferences[n3KanjiReviewScheduleKey] ?: emptySet()
    val learnedCount = n3KanjiCatalog.count { it.character in learnedSet }
    val dueReviewCount = n3KanjiDueForReview(n3KanjiCatalog, learnedSet, reviewSchedule, LocalDate.now()).size
    val scope = rememberCoroutineScope()
    var query by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf<N3KanjiEntry?>(null) }
    var quiz by remember { mutableStateOf<List<N3KanjiQuizQuestion>>(emptyList()) }
    var quizIndex by remember { mutableIntStateOf(0) }
    var quizScore by remember { mutableIntStateOf(0) }
    var quizChoice by remember { mutableStateOf<String?>(null) }
    var quizDone by remember { mutableStateOf(false) }
    var reviewMode by remember { mutableStateOf(false) }
    var reviewEntries by remember { mutableStateOf<List<N3KanjiEntry>>(emptyList()) }

    if (reviewMode) {
        N3KanjiReviewScreen(ds, reviewEntries) {
            reviewMode = false
            reviewEntries = emptyList()
        }
        return
    }

    if (quiz.isNotEmpty()) {
        if (quizDone) {
            Column(
                modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Evaluación de kanji N3", style = MaterialTheme.typography.headlineSmall)
                Text("$quizScore / ${quiz.size}", style = MaterialTheme.typography.displayMedium)
                Text(if (quizScore >= 8) "¡Buen trabajo! Sigue repasando." else "Revisa las fichas y vuelve a intentarlo.")
                Text("Mejor resultado: $bestScore / 10")
                Button(onClick = { quiz = emptyList(); quizDone = false }) { Text("Volver a la biblioteca") }
            }
        } else {
            val question = quiz[quizIndex]
            Column(
                modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TextButton(onClick = { quiz = emptyList(); quizDone = false }) { Text("← Salir") }
                Text("Pregunta ${quizIndex + 1} de ${quiz.size} · N3")
                LinearProgressIndicator(
                    progress = { (quizIndex + 1).toFloat() / quiz.size },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(question.display, style = MaterialTheme.typography.displayLarge)
                Text(question.prompt)
                question.options.forEach { option ->
                    OutlinedButton(
                        onClick = { quizChoice = option },
                        enabled = quizChoice == null,
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(option) }
                }
                if (quizChoice != null) {
                    Text(
                        if (quizChoice == question.answer) "¡Correcto!" else "Respuesta correcta: ${question.answer}",
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(question.explanation, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Button(
                    enabled = quizChoice != null,
                    onClick = {
                        val score = quizScore + if (quizChoice == question.answer) 1 else 0
                        quizScore = score
                        quizChoice = null
                        if (quizIndex == quiz.lastIndex) {
                            quizDone = true
                            scope.launch {
                                ds.edit { prefs -> prefs[n3KanjiBestQuizKey] = maxOf(prefs[n3KanjiBestQuizKey] ?: 0, score) }
                            }
                        } else quizIndex++
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text(if (quizIndex == quiz.lastIndex) "Finalizar" else "Siguiente") }
            }
        }
        return
    }

    if (selected != null) {
        val entry = selected!!
        val isLearned = entry.character in learnedSet
        Column(
            modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextButton(onClick = { selected = null }) { Text("← Biblioteca N3") }
            Text("N3 · Kanji", color = MaterialTheme.colorScheme.primary)
            Text(entry.character, style = MaterialTheme.typography.displayLarge)
            Text(entry.meaningEs, style = MaterialTheme.typography.headlineSmall)
            Text("Trazos: ${entry.strokeCount}")
            N3KanjiStrokeOrder(entry.character, entry.strokeCount)
            HorizontalDivider()
            Text("Lectura On'yomi", style = MaterialTheme.typography.titleMedium)
            Text(entry.onyomi.joinToString(" · ").ifBlank { "No incluida en esta ficha" })
            Text("Lectura Kun'yomi", style = MaterialTheme.typography.titleMedium)
            Text(entry.kunyomi.joinToString(" · ") { displayN3KunYomi(it) }.ifBlank { "No incluida en esta ficha" })
            Text(
                "Las marcas técnicas de separación se omiten para facilitar la lectura.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Button(
                onClick = {
                    scope.launch {
                        ds.edit { prefs ->
                            val old = prefs[n3KanjiLearnedKey] ?: emptySet()
                            prefs[n3KanjiLearnedKey] = if (entry.character in old) old - entry.character else old + entry.character
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (isLearned) "Marcar como pendiente" else "Marcar como aprendido") }
        }
        return
    }

    val visibleEntries = remember(query) {
        val search = query.trim()
        if (search.isBlank()) n3KanjiCatalog else n3KanjiCatalog.filter {
            it.character.contains(search) || it.meaningEs.contains(search, ignoreCase = true)
        }
    }

    Column(modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)) {
            Text("Kanji N3", style = MaterialTheme.typography.headlineSmall)
            Text(
                "Selección de estudio · 370 caracteres",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(10.dp))
            Text("Progreso: $learnedCount / ${n3KanjiCatalog.size}", style = MaterialTheme.typography.labelLarge)
            LinearProgressIndicator(
                progress = { learnedCount.toFloat() / n3KanjiCatalog.size.toFloat() },
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                AssistChip(
                    onClick = {
                        reviewEntries = n3KanjiDueForReview(n3KanjiCatalog, learnedSet, reviewSchedule, LocalDate.now()).take(20)
                        reviewMode = true
                    },
                    label = { Text("Repasar hoy · $dueReviewCount") },
                    enabled = dueReviewCount > 0
                )
                AssistChip(
                    onClick = {
                        quiz = buildN3KanjiQuiz()
                        quizIndex = 0
                        quizScore = 0
                        quizChoice = null
                        quizDone = false
                    },
                    label = { Text("Examen · 10 preguntas") }
                )
            }
            Text("Mejor resultado: $bestScore / 10", style = MaterialTheme.typography.labelMedium)
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Buscar kanji o significado") }
            )
            if (query.isNotBlank()) {
                Text("Resultados en los 370 kanjis", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (visibleEntries.isEmpty()) item {
                Column(Modifier.fillMaxWidth().padding(28.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.Outlined.SearchOff, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Text("No se encontraron kanji", style = MaterialTheme.typography.titleMedium)
                    Text("Prueba con otro término.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    OutlinedButton(onClick = { query = "" }) { Text("Mostrar todos los kanji") }
                }
            }
            items(visibleEntries, key = { it.character }) { entry ->
                val isLearned = entry.character in learnedSet
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { selected = entry },
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier.width(64.dp).heightIn(min = 64.dp), contentAlignment = Alignment.Center) { Text(entry.character, style = MaterialTheme.typography.headlineLarge, maxLines = 1) }
                        Column(Modifier.weight(1f)) {
                            Text(entry.meaningEs, style = MaterialTheme.typography.titleSmall)
                            Text("On: ${entry.onyomi.joinToString("、").ifBlank { "—" }}", style = MaterialTheme.typography.bodySmall)
                            Text("Kun: ${entry.kunyomi.joinToString("、") { displayN3KunYomi(it) }.ifBlank { "—" }}", style = MaterialTheme.typography.bodySmall)
                            Text("${entry.strokeCount} trazos", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        if (isLearned) Text("✓", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}
