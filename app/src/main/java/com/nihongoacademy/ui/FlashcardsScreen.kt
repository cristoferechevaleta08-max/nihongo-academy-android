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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.data.FlashcardReviewStore
import com.nihongoacademy.data.PreferencesStore
import com.nihongoacademy.data.StudySettings
import com.nihongoacademy.data.FlashcardSchedule
import com.nihongoacademy.data.ReviewRating
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.ModuleLesson
import kotlinx.coroutines.launch

@Composable
fun FlashcardsScreen(navController: NavController, languageCode: String) {
    val languages = remember { LanguageCatalogFactory.buildSupportedLanguages() }
    val language = languages.firstOrNull { it.code.equals(languageCode, ignoreCase = true) } ?: languages.first()
    val cards = remember(language.code) {
        language.modules.flatMap { module ->
            module.lessons.map { lesson -> Triple(module.id, lesson.id, lesson) }
        }
    }
    val context = LocalContext.current
    val store = remember { FlashcardReviewStore(context.applicationContext) }
    val preferencesStore = remember { PreferencesStore(context.applicationContext) }
    val settingsFlow = remember(preferencesStore) { preferencesStore.studySettingsFlow() }
    val settings by settingsFlow.collectAsState(initial = StudySettings())
    val scope = rememberCoroutineScope()
    var schedules by remember(language.code) { mutableStateOf<Map<String, FlashcardSchedule>>(emptyMap()) }
    var currentCard by remember(language.code) { mutableStateOf<Triple<String, String, ModuleLesson>?>(null) }
    var answerVisible by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(true) }
    var saving by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun cardKey(card: Triple<String, String, ModuleLesson>) = "${card.first}:${card.second}"

    fun selectNextCard() {
        val now = System.currentTimeMillis()
        val next = cards.firstOrNull { card -> (schedules[cardKey(card)]?.dueAt ?: 0L) <= now }
        currentCard = next
        answerVisible = false
    }

    LaunchedEffect(language.code) {
        schedules = store.getSchedules(language.code)
        selectNextCard()
        loading = false
    }

    fun review(rating: ReviewRating) {
        val card = currentCard ?: return
        saving = true
        errorMessage = null
        scope.launch {
            runCatching { store.recordReview(language.code, cardKey(card), rating) }
                .onSuccess { saved ->
                    schedules = schedules + (cardKey(card) to saved)
                    selectNextCard()
                }
                .onFailure { errorMessage = "No se pudo guardar el repaso. Comprueba el almacenamiento e inténtalo otra vez." }
            saving = false
        }
    }

    val dueCount = cards.count { (schedules[cardKey(it)]?.dueAt ?: 0L) <= System.currentTimeMillis() }
    val selected = currentCard

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Entrenamiento de memoria", style = MaterialTheme.typography.labelLarge)
        Text("Tarjetas · ${language.name}", style = MaterialTheme.typography.headlineMedium)
        Text("$dueCount tarjetas pendientes · ${cards.size} tarjetas en el mazo", style = MaterialTheme.typography.bodyMedium)

        if (loading) {
            Text("Preparando tus tarjetas...")
        } else if (selected == null) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("¡Todo repasado por ahora!", style = MaterialTheme.typography.titleLarge)
                    Text("Las tarjetas volverán cuando llegue su próximo horario de repaso. Puedes regresar más tarde.")
                }
            }
        } else {
            val lesson = selected.third
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text("Pregunta", style = MaterialTheme.typography.labelLarge)
                    Text(lesson.prompt, style = MaterialTheme.typography.headlineSmall)
                    if (settings.showPronunciation && lesson.speechText.isNotBlank()) PronunciationButton(lesson.speechText, language.code)
                    if (answerVisible) {
                        Text("Respuesta", style = MaterialTheme.typography.labelLarge)
                        Text(lesson.correctAnswer, style = MaterialTheme.typography.titleLarge)
                        Text(lesson.explanation, style = MaterialTheme.typography.bodyMedium)
                    } else {
                        Text("Piensa en la respuesta antes de revelarla.", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            if (!answerVisible) {
                Button(onClick = { answerVisible = true }, modifier = Modifier.fillMaxWidth()) {
                    Text("Mostrar respuesta")
                }
            } else {
                Text("¿Qué tan fácil fue recordarla?", style = MaterialTheme.typography.titleMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { review(ReviewRating.AGAIN) },
                        enabled = !saving,
                        modifier = Modifier.weight(1f)
                    ) { Text("Otra vez") }
                    OutlinedButton(
                        onClick = { review(ReviewRating.HARD) },
                        enabled = !saving,
                        modifier = Modifier.weight(1f)
                    ) { Text("Difícil") }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { review(ReviewRating.GOOD) },
                        enabled = !saving,
                        modifier = Modifier.weight(1f)
                    ) { Text("Bien") }
                    Button(
                        onClick = { review(ReviewRating.EASY) },
                        enabled = !saving,
                        modifier = Modifier.weight(1f)
                    ) { Text("Fácil") }
                }
            }
        }

        errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        if (saving) Text("Guardando repaso...")
        OutlinedButton(onClick = { navController.navigateUp() }, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }
    }

}
