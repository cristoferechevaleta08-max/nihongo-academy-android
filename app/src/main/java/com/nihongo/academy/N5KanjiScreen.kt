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
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val n5KanjiLearnedKey = stringSetPreferencesKey("n5_kanji_learned")

@Composable
internal fun N5KanjiScreen(
    dataStore: DataStore<Preferences>,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val learned by dataStore.data.map { it[n5KanjiLearnedKey] ?: emptySet() }
        .collectAsState(initial = emptySet())
    var selected by remember { mutableStateOf<N5KanjiEntry?>(null) }
    var query by remember { mutableStateOf("") }
    var onlyPending by remember { mutableStateOf(false) }

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
                        dataStore.edit { prefs ->
                            val current = prefs[n5KanjiLearnedKey] ?: emptySet()
                            prefs[n5KanjiLearnedKey] =
                                if (entry.character in current) current - entry.character
                                else current + entry.character
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (entry.character in learned) "Marcar como pendiente" else "Marcar como aprendido")
            }
        }
        return
    }

    val filtered = n5KanjiCatalog.filter { entry ->
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
            progress = { if (n5KanjiCatalog.isEmpty()) 0f else learned.intersect(n5KanjiCatalog.map { it.character }.toSet()).size.toFloat() / n5KanjiCatalog.size },
            modifier = Modifier.fillMaxWidth()
        )
        Text("${learned.intersect(n5KanjiCatalog.map { it.character }.toSet()).size} de ${n5KanjiCatalog.size} kanji de este bloque aprendidos")
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
