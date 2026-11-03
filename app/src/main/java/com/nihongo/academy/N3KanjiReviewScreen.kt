package com.nihongo.academy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import java.time.LocalDate

private val n3KanjiReviewScheduleKey = stringSetPreferencesKey("n3_kanji_review_schedule")

@Composable
internal fun N3KanjiReviewScreen(
    ds: DataStore<Preferences>,
    entries: List<N3KanjiEntry>,
    onExit: () -> Unit
) {
    var index by remember { mutableIntStateOf(0) }
    var revealed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val entry = entries.getOrNull(index)

    if (entry == null) {
        Column(
            Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Repaso N3 completado", style = MaterialTheme.typography.headlineSmall)
            Text(entries.size.toString() + " kanji repasados", style = MaterialTheme.typography.titleLarge)
            Text("Las próximas revisiones quedaron programadas según tu valoración.")
            Button(onClick = onExit) { Text("Volver a la biblioteca") }
        }
        return
    }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = onExit, modifier = Modifier.align(Alignment.Start)) { Text("← Salir del repaso") }
        Text("Repaso N3 · " + (index + 1) + " de " + entries.size, style = MaterialTheme.typography.titleMedium)
        LinearProgressIndicator(
            progress = (index + 1).toFloat() / entries.size.coerceAtLeast(1),
            modifier = Modifier.fillMaxWidth()
        )
        Text(entry.character, style = MaterialTheme.typography.displayLarge)
        if (revealed) {
            Text(entry.meaningEs, style = MaterialTheme.typography.headlineSmall)
            HorizontalDivider()
            Text("On'yomi: " + entry.onyomi.joinToString(" · ").ifBlank { "—" })
            Text("Kun'yomi: " + entry.kunyomi.joinToString(" · ") { it.replace(".", "") }.ifBlank { "—" })
            Text("¿Cómo te resultó esta ficha?", style = MaterialTheme.typography.titleMedium)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    N3ReviewRating.HARD to "Difícil",
                    N3ReviewRating.GOOD to "Bien",
                    N3ReviewRating.EASY to "Fácil"
                ).forEach { (rating, label) ->
                    OutlinedButton(
                        onClick = {
                            scope.launch {
                                ds.edit { prefs ->
                                    val old = prefs[n3KanjiReviewScheduleKey] ?: emptySet()
                                    prefs[n3KanjiReviewScheduleKey] = scheduleN3KanjiReview(
                                        old, entry.character, rating, LocalDate.now()
                                    )
                                }
                            }
                            revealed = false
                            index++
                        },
                        modifier = Modifier.weight(1f)
                    ) { Text(label) }
                }
            }
        } else {
            Text("Intentá recordar el significado y las lecturas antes de revelar la respuesta.")
            Button(onClick = { revealed = true }, modifier = Modifier.fillMaxWidth()) {
                Text("Mostrar respuesta")
            }
        }
    }
}
