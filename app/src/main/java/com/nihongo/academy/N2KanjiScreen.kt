package com.nihongo.academy

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val n2KanjiLearnedKey = stringSetPreferencesKey("n2_kanji_learned")
private val n2KanjiBestExamKey = intPreferencesKey("n2_kanji_best_exam_score")
private fun n2KanjiSrsKey(character: String) = stringPreferencesKey("n2_kanji_srs_$character")

@Composable
internal fun N2KanjiScreen(
    ds: DataStore<Preferences>,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val learned by ds.data.map { it[n2KanjiLearnedKey] ?: emptySet() }
        .collectAsState(initial = emptySet())
    val bestExamScore by ds.data.map { it[n2KanjiBestExamKey] ?: 0 }
        .collectAsState(initial = 0)
    val reviewStates: Map<String, KanjiReviewState> by ds.data.map { prefs ->
        n2KanjiCatalog.mapNotNull { entry ->
            prefs[n2KanjiSrsKey(entry.character)]?.let { stored ->
                KanjiReviewState.decode(stored)?.let { entry.character to it }
            }
        }.toMap()
    }.collectAsState(initial = emptyMap<String, KanjiReviewState>())

    var selected by remember { mutableStateOf<N2KanjiEntry?>(null) }
    var writingEntry by remember { mutableStateOf<N2KanjiEntry?>(null) }
    var query by remember { mutableStateOf("") }
    var onlyPending by remember { mutableStateOf(false) }
    var reviewQueue by remember { mutableStateOf<List<N2KanjiEntry>>(emptyList()) }
    var reviewIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var answerRevealed by remember { mutableStateOf(false) }
    var reviewFinished by remember { mutableStateOf(false) }
    var examQuestions by remember { mutableStateOf<List<N2KanjiEntry>>(emptyList()) }
    var examIndex by remember { mutableIntStateOf(0) }
    var examScore by remember { mutableIntStateOf(0) }
    var examChoice by remember { mutableStateOf<String?>(null) }
    var examFinished by remember { mutableStateOf(false) }

    val now = System.currentTimeMillis()
    val dueEntries = n2KanjiCatalog.filter { entry ->
        reviewStates[entry.character]?.nextReviewAt?.let { it <= now } == true
    }

    fun startReview(entries: List<N2KanjiEntry>) {
        reviewQueue = entries
        reviewIndex = 0
        selectedAnswer = null
        answerRevealed = false
        reviewFinished = entries.isEmpty()
        selected = null
    }

    if (examQuestions.isNotEmpty()) {
        if (examFinished) {
            Column(
                modifier.fillMaxSize().padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(32.dp))
                Text("Examen N5 completado", style = MaterialTheme.typography.headlineMedium)
                Text("$examScore / ${examQuestions.size}", style = MaterialTheme.typography.displayMedium)
                Text(
                    if (examScore >= 8) "¡Muy buen trabajo! Seguí repasando para consolidar los kanji."
                    else "Buen intento. Revisá tus errores y volvé a practicar."
                )
                Text("Mejor resultado: $bestExamScore / 10", color = MaterialTheme.colorScheme.primary)
                Button(onClick = {
                    examQuestions = emptyList()
                    examFinished = false
                }) { Text("Volver a la biblioteca") }
            }
            return
        }

        val entry = examQuestions[examIndex]
        val options = remember(entry.character, examQuestions.size) {
            (n2KanjiCatalog.filter { it.character != entry.character && it.meaningEs != entry.meaningEs }
                .shuffled().take(3).map { it.meaningEs } + entry.meaningEs).shuffled()
        }
        Column(
            modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextButton(onClick = {
                examQuestions = emptyList()
                examFinished = false
            }) { Text("← Salir del examen") }
            Text("Evaluación de kanji N5", color = MaterialTheme.colorScheme.primary)
            Text("Pregunta ${examIndex + 1} de ${examQuestions.size}")
            LinearProgressIndicator(
                progress = { (examIndex + 1).toFloat() / examQuestions.size },
                modifier = Modifier.fillMaxWidth()
            )
            Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp)) {
                Column(
                    Modifier.fillMaxWidth().padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(entry.character, style = MaterialTheme.typography.displayLarge)
                    Text("Seleccioná el significado correcto.", style = MaterialTheme.typography.titleMedium)
                }
            }
            options.forEach { option ->
                OutlinedButton(
                    onClick = { examChoice = option },
                    enabled = examChoice == null,
                    modifier = Modifier.fillMaxWidth()
                ) { Text(option) }
            }
            Button(
                onClick = {
                    val updatedScore = examScore + if (examChoice == entry.meaningEs) 1 else 0
                    examScore = updatedScore
                    examChoice = null
                    if (examIndex + 1 < examQuestions.size) {
                        examIndex++
                    } else {
                        examFinished = true
                        scope.launch {
                            ds.edit { prefs ->
                                prefs[n2KanjiBestExamKey] = maxOf(
                                    prefs[n2KanjiBestExamKey] ?: 0,
                                    updatedScore
                                )
                            }
                        }
                    }
                },
                enabled = examChoice != null,
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (examIndex + 1 == examQuestions.size) "Finalizar examen" else "Siguiente pregunta") }
        }
        return
    }

    if (writingEntry != null) {
        val entry = writingEntry!!
        var strokes by remember(entry.character) {
            mutableStateOf<List<List<Offset>>>(emptyList())
        }
        Column(
            modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            TextButton(onClick = { writingEntry = null }) { Text("← Volver a la ficha") }
            Text("Escritura de kanji N5", color = MaterialTheme.colorScheme.primary)
            Text(entry.character, style = MaterialTheme.typography.headlineMedium)
            Text("Trazá el carácter siguiendo el modelo.")
            val writingGuideColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.13f)
            val writingStrokeColor = MaterialTheme.colorScheme.primary
            Box(
                Modifier.fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
                    .pointerInput(entry.character) {
                        detectDragGestures(
                            onDragStart = { point -> strokes = strokes + listOf(listOf(point)) },
                            onDrag = { change, _ ->
                                change.consume()
                                if (strokes.isNotEmpty()) {
                                    strokes = strokes.dropLast(1) + listOf(strokes.last() + change.position)
                                }
                            }
                        )
                    }
            ) {
                Text(
                    entry.character,
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.displayLarge,
                    color = writingGuideColor
                )
                Canvas(Modifier.fillMaxSize()) {
                    strokes.forEach { line ->
                        line.zipWithNext().forEach { (start, end) ->
                            drawLine(
                                color = writingStrokeColor,
                                start = start,
                                end = end,
                                strokeWidth = 12f,
                                cap = StrokeCap.Round
                            )
                        }
                    }
                }
            }
            Text(
                "Práctica de trazado libre. La evaluación automática del orden exacto de los trazos se incorporará después.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(
                    onClick = { strokes = emptyList() },
                    modifier = Modifier.weight(1f)
                ) { Text("Borrar") }
                Button(
                    onClick = { writingEntry = null },
                    modifier = Modifier.weight(1f)
                ) { Text("Terminar") }
            }
        }
        return
    }

    if (reviewQueue.isNotEmpty() || reviewFinished) {
        if (reviewFinished) {
            Column(
                modifier.fillMaxSize().padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(36.dp))
                Text("¡Sesión completada!", style = MaterialTheme.typography.headlineMedium)
                Text("Terminaste esta tanda de kanji. Tu progreso quedó guardado en este dispositivo.")
                Button(onClick = {
                    reviewQueue = emptyList()
                    reviewFinished = false
                }) { Text("Volver a la biblioteca") }
            }
            return
        }

        val entry = reviewQueue[reviewIndex.coerceAtMost(reviewQueue.lastIndex)]
        val options = remember(entry.character) {
            (n2KanjiCatalog.filter { it.character != entry.character && it.meaningEs != entry.meaningEs }
                .shuffled().take(3).map { it.meaningEs } + entry.meaningEs).shuffled()
        }
        Column(
            modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextButton(onClick = {
                reviewQueue = emptyList()
                reviewFinished = false
            }) { Text("← Salir del repaso") }
            Text("Repaso de kanji N5 · ${reviewIndex + 1} de ${reviewQueue.size}",
                color = MaterialTheme.colorScheme.primary)
            LinearProgressIndicator(
                progress = { (reviewIndex + 1).toFloat() / reviewQueue.size.coerceAtLeast(1) },
                modifier = Modifier.fillMaxWidth()
            )
            Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp)) {
                Column(
                    Modifier.fillMaxWidth().padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(entry.character, style = MaterialTheme.typography.displayLarge)
                    Text("¿Qué significa este kanji?", style = MaterialTheme.typography.titleMedium)
                }
            }
            options.forEach { option ->
                OutlinedButton(
                    onClick = { if (!answerRevealed) selectedAnswer = option },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !answerRevealed,
                    colors = if (answerRevealed && option == entry.meaningEs) {
                        ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                    } else ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(option)
                }
            }
            if (answerRevealed) {
                val correct = selectedAnswer == entry.meaningEs
                Text(
                    if (correct) "¡Correcto!" else "La respuesta correcta es: ${entry.meaningEs}",
                    color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.titleMedium
                )
                Text("¿Qué tan bien lo recordaste?")
                listOf(
                    KanjiReviewGrade.AGAIN to "Otra vez",
                    KanjiReviewGrade.HARD to "Difícil",
                    KanjiReviewGrade.GOOD to "Bien",
                    KanjiReviewGrade.EASY to "Fácil"
                ).forEach { (grade, label) ->
                    OutlinedButton(
                        onClick = {
                            scope.launch {
                                val previous = reviewStates[entry.character] ?: KanjiReviewState.initial
                                val next = scheduleKanjiReview(previous, grade, System.currentTimeMillis())
                                ds.edit { prefs ->
                                    prefs[n2KanjiSrsKey(entry.character)] = next.encode()
                                }
                            }
                            if (reviewIndex + 1 < reviewQueue.size) {
                                reviewIndex++
                                selectedAnswer = null
                                answerRevealed = false
                            } else {
                                reviewQueue = emptyList()
                                reviewFinished = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(label) }
                }
            } else {
                Button(
                    onClick = { answerRevealed = true },
                    enabled = selectedAnswer != null,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Comprobar respuesta") }
            }
        }
        return
    }

    if (selected != null) {
        val entry = selected!!
        Column(
            modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            TextButton(onClick = { selected = null }) { Text("← Biblioteca N5") }
            Text("N5 · Kanji", color = MaterialTheme.colorScheme.primary)
            Text(entry.character, style = MaterialTheme.typography.displayLarge)
            Text(entry.meaningEs, style = MaterialTheme.typography.headlineSmall)
            Text("Trazos: ${entry.strokeCount}")
            N2KanjiStrokeOrder(entry.character, entry.strokeCount)
            val srs = reviewStates[entry.character]
            if (srs != null) {
                Text(
                    if (srs.nextReviewAt <= now) "Repaso pendiente" else "Próximo repaso: " +
                        java.text.DateFormat.getDateInstance().format(java.util.Date(srs.nextReviewAt)),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            HorizontalDivider()
            Text("Lectura On'yomi", style = MaterialTheme.typography.titleMedium)
            Text(entry.onyomi.joinToString(" · ").ifBlank { "Sin lectura On'yomi registrada" })
            Text("Lectura Kun'yomi", style = MaterialTheme.typography.titleMedium)
            Text(entry.kunyomi.joinToString(" · ").ifBlank { "Sin lectura Kun'yomi registrada" })
            HorizontalDivider()
            Text("Vocabulario", style = MaterialTheme.typography.titleMedium)
            entry.vocabulary.forEach { word ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text(word.word, style = MaterialTheme.typography.titleLarge)
                        Text(word.reading, color = MaterialTheme.colorScheme.primary)
                        Text(word.meaningEs)
                    }
                }
            }
            HorizontalDivider()
            Text("Ejemplo", style = MaterialTheme.typography.titleMedium)
            Text(entry.exampleJa, style = MaterialTheme.typography.titleLarge)
            Text(entry.exampleReading, color = MaterialTheme.colorScheme.primary)
            Text(entry.exampleEs)
            Button(
                onClick = {
                    scope.launch {
                        ds.edit { prefs ->
                            val current = prefs[n2KanjiLearnedKey] ?: emptySet()
                            prefs[n2KanjiLearnedKey] =
                                if (entry.character in current) current - entry.character
                                else current + entry.character
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (entry.character in learned) "Marcar como pendiente" else "Marcar como aprendido")
            }
            OutlinedButton(
                onClick = { startReview(listOf(entry)) },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Practicar este kanji") }
            OutlinedButton(
                onClick = { writingEntry = entry },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Practicar escritura") }
        }
        return
    }

    val learnedCount = learned.intersect(n2KanjiCatalog.map { it.character }.toSet()).size
    val filtered = n2KanjiCatalog.filter { entry ->
        (!onlyPending || entry.character !in learned) &&
            (query.isBlank() || entry.character.contains(query) ||
                entry.meaningEs.contains(query, ignoreCase = true) ||
                entry.vocabulary.any { it.word.contains(query) || it.meaningEs.contains(query, ignoreCase = true) })
    }
    Column(modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text("Biblioteca de kanji", style = MaterialTheme.typography.headlineMedium)
        Text("N5 · Primer bloque de contenido", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { if (n2KanjiCatalog.isEmpty()) 0f else learnedCount.toFloat() / n2KanjiCatalog.size },
            modifier = Modifier.fillMaxWidth()
        )
        Text("$learnedCount de ${n2KanjiCatalog.size} kanji marcados como aprendidos")
        Text("Mejor examen: $bestExamScore / 10", color = MaterialTheme.colorScheme.onSurfaceVariant)
        OutlinedButton(
            onClick = {
                examQuestions = n2KanjiCatalog.shuffled().take(10)
                examIndex = 0
                examScore = 0
                examChoice = null
                examFinished = false
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Realizar examen N5 · 10 preguntas") }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { startReview(dueEntries) },
                enabled = dueEntries.isNotEmpty(),
                modifier = Modifier.weight(1f)
            ) { Text("Repasar · ${dueEntries.size}") }
            OutlinedButton(
                onClick = { startReview(n2KanjiCatalog) },
                modifier = Modifier.weight(1f)
            ) { Text("Practicar todos") }
        }
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Buscar kanji, significado o palabra") },
            singleLine = true
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = onlyPending, onCheckedChange = { onlyPending = it })
            Text("Mostrar solo pendientes")
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            items(filtered, key = { it.character }) { entry ->
                Card(
                    Modifier.fillMaxWidth().clickable { selected = entry },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        Modifier.padding(14.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(entry.character, style = MaterialTheme.typography.displaySmall)
                        Column(Modifier.weight(1f)) {
                            Text(entry.meaningEs, style = MaterialTheme.typography.titleMedium)
                            Text("On: ${entry.onyomi.joinToString(" · ").ifBlank { "—" }}")
                            Text("Kun: ${entry.kunyomi.joinToString(" · ").ifBlank { "—" }}")
                            Text("${entry.strokeCount} trazos · ${entry.vocabulary.size} palabras", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        if (entry.character in learned) Text("✓", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}
