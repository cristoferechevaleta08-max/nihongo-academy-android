package com.nihongo.academy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
internal fun N3KanjiScreen(
    ds: DataStore<Preferences>,
    modifier: Modifier = Modifier
) {
    val learnedKey = remember { stringSetPreferencesKey("n3_kanji_learned") }
    val learned by ds.data.collectAsState(initial = androidx.datastore.preferences.core.emptyPreferences())
    val learnedSet = learned[learnedKey] ?: emptySet()
    val scope = rememberCoroutineScope()
    var blockIndex by remember { mutableIntStateOf(0) }
    var query by remember { mutableStateOf("") }

    val visibleEntries = remember(blockIndex, query) {
        val source = if (query.isBlank()) n3KanjiBlocks[blockIndex]
            else n3KanjiCharacters
        source.withIndex().filter { (_, character) ->
            character.contains(query.trim())
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
            Text(
                "Progreso: ${learnedSet.size} / ${n3KanjiCharacters.size}",
                style = MaterialTheme.typography.labelLarge
            )
            LinearProgressIndicator(
                progress = learnedSet.size.toFloat() / n3KanjiCharacters.size.toFloat(),
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Buscar kanji") }
            )
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                n3KanjiBlocks.forEachIndexed { index, block ->
                    FilterChip(
                        selected = blockIndex == index && query.isBlank(),
                        onClick = { blockIndex = index; query = "" },
                        label = { Text("Bloque ${index + 1} · ${block.size}") }
                    )
                }
            }
            if (query.isNotBlank()) {
                Text(
                    "Resultados en los 3 bloques",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(visibleEntries, key = { _, entry -> entry.value }) { index, entry ->
                val character = entry.value
                val isLearned = character in learnedSet
                Surface(
                    modifier = Modifier.fillMaxWidth().clickable {
                        scope.launch {
                            ds.edit { prefs ->
                                val current = prefs[learnedKey] ?: emptySet()
                                prefs[learnedKey] = if (character in current) current - character
                                    else current + character
                            }
                        }
                    },
                    shape = RoundedCornerShape(14.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Row(
                        Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            character,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.width(64.dp)
                        )
                        Column(Modifier.weight(1f)) {
                            Text("Kanji N3", style = MaterialTheme.typography.titleSmall)
                            Text(
                                if (isLearned) "Marcado como aprendido" else "Tocá para marcar como aprendido",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Checkbox(
                            checked = isLearned,
                            onCheckedChange = {
                                scope.launch {
                                    ds.edit { prefs ->
                                        val current = prefs[learnedKey] ?: emptySet()
                                        prefs[learnedKey] = if (it) current + character else current - character
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
