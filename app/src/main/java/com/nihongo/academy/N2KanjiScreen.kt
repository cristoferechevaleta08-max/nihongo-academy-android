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
import kotlinx.coroutines.launch

private val n2KanjiLearnedKey = stringSetPreferencesKey("n2_kanji_learned")

@Composable
internal fun N2KanjiScreen(
    ds: DataStore<Preferences>,
    modifier: Modifier = Modifier
) {
    val preferences by ds.data.collectAsState(initial = androidx.datastore.preferences.core.emptyPreferences())
    val learned = preferences[n2KanjiLearnedKey] ?: emptySet()
    val scope = rememberCoroutineScope()
    var query by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf<N2KanjiEntry?>(null) }

    if (selected != null) {
        val entry = selected!!
        val isLearned = entry.character in learned
        Column(
            modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextButton(onClick = { selected = null }) { Text("← Biblioteca N2") }
            Text("N2 · Kanji", color = MaterialTheme.colorScheme.primary)
            Text(entry.character, style = MaterialTheme.typography.displayLarge)
            Text(entry.meaningEs, style = MaterialTheme.typography.headlineSmall)
            Text("Trazos: ${entry.strokeCount}")
            HorizontalDivider()
            Text("Lectura On'yomi", style = MaterialTheme.typography.titleMedium)
            Text(entry.onyomi.joinToString(" · ").ifBlank { "No incluida en esta ficha" })
            Text("Lectura Kun'yomi", style = MaterialTheme.typography.titleMedium)
            Text(entry.kunyomi.joinToString(" · ") { displayN2KunYomi(it) }.ifBlank { "No incluida en esta ficha" })
            Button(
                onClick = {
                    scope.launch {
                        ds.edit { prefs ->
                            val old = prefs[n2KanjiLearnedKey] ?: emptySet()
                            prefs[n2KanjiLearnedKey] =
                                if (entry.character in old) old - entry.character else old + entry.character
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (isLearned) "Marcar como pendiente" else "Marcar como aprendido") }
        }
        return
    }

    val visible = remember(query) {
        if (query.isBlank()) n2KanjiBlock1
        else n2KanjiBlock1.filter {
            it.character.contains(query.trim()) ||
                it.meaningEs.contains(query.trim(), ignoreCase = true) ||
                it.onyomi.any { reading -> reading.contains(query.trim(), ignoreCase = true) } ||
                it.kunyomi.any { reading -> displayN2KunYomi(reading).contains(query.trim(), ignoreCase = true) }
        }
    }

    Column(modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)) {
            Text("Kanji N2", style = MaterialTheme.typography.headlineSmall)
            Text(
                "Selección pedagógica · ${n2KanjiBlock1.size} caracteres en el bloque inicial",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))
            Text("Progreso: ${learned.size} / ${n2KanjiBlock1.size}", style = MaterialTheme.typography.labelLarge)
            LinearProgressIndicator(
                progress = learned.intersect(n2KanjiCharacters.toSet()).size.toFloat() / n2KanjiBlock1.size.toFloat(),
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
            )
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Buscar kanji, lectura o significado") }
            )
            Spacer(Modifier.height(8.dp))
            AssistChip(onClick = { query = "" }, label = { Text("Bloque 1 · ${n2KanjiBlock1.size}") })
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(visible, key = { it.character }) { entry ->
                val isLearned = entry.character in learned
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { selected = entry },
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(entry.character, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.width(64.dp))
                        Column(Modifier.weight(1f)) {
                            Text(entry.meaningEs, style = MaterialTheme.typography.titleSmall)
                            Text("On: ${entry.onyomi.joinToString("、").ifBlank { "—" }}", style = MaterialTheme.typography.bodySmall)
                            Text(
                                "Kun: ${entry.kunyomi.joinToString("、") { displayN2KunYomi(it) }.ifBlank { "—" }}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        if (isLearned) Text("✓", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}
