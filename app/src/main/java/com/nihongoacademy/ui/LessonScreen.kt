package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.data.PreferencesStore
import com.nihongoacademy.data.StudySettings
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.StudyProgress
import kotlinx.coroutines.launch

@Composable
fun LessonScreen(
    navController: NavController,
    languageCode: String = "ja",
    moduleId: String = "module_1",
    lessonId: String = "m1_l1"
) {
    val languages = remember { LanguageCatalogFactory.buildSupportedLanguages() }
    val language = languages.firstOrNull { it.code.equals(languageCode, ignoreCase = true) } ?: languages.first()
    val module = language.modules.firstOrNull { it.id == moduleId } ?: language.modules.first()
    val lesson = module.lessons.firstOrNull { it.id == lessonId } ?: module.lessons.first()
    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    val preferencesStore = remember { PreferencesStore(context.applicationContext) }
    val settingsFlow = remember(preferencesStore) { preferencesStore.studySettingsFlow() }
    val settings by settingsFlow.collectAsState(initial = StudySettings())
    val scope = rememberCoroutineScope()

    var selectedAnswer by remember(lesson.id) { mutableStateOf("") }
    var checked by remember(lesson.id) { mutableStateOf(false) }
    var saving by remember { mutableStateOf(false) }
    var saveError by remember { mutableStateOf<String?>(null) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = language.code)) }

    LaunchedEffect(language.code) {
        progress = progressStore.getProgress(languageCode).copy(languageCode = language.code)
    }

    val isCorrect = selectedAnswer == lesson.correctAnswer

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Lección · ${language.name}", style = MaterialTheme.typography.labelLarge)
        Text(lesson.title, style = MaterialTheme.typography.headlineMedium)
        Text(lesson.description, style = MaterialTheme.typography.bodyLarge)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Comprueba lo aprendido", style = MaterialTheme.typography.labelLarge)
                Text(lesson.prompt, style = MaterialTheme.typography.titleLarge)
                if (settings.showPronunciation && lesson.speechText.isNotBlank()) PronunciationButton(lesson.speechText, language.code)
                lesson.options.forEach { option ->
                    OutlinedButton(
                        onClick = { if (!checked) selectedAnswer = option },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !checked
                    ) {
                        Text(if (selectedAnswer == option) "✓  $option" else option)
                    }
                }
            }
        }

        if (checked) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        if (isCorrect) "¡Respuesta correcta!" else "Vamos a reforzarlo",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isCorrect) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Text("Respuesta: ${lesson.correctAnswer}")
                    Text(lesson.explanation)
                    Text(if (isCorrect) "+10 XP" else "Puedes volver a intentarlo en otra sesión.")
                }
            }
        }

        saveError?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        Button(
            onClick = {
                if (!checked && selectedAnswer.isNotBlank()) {
                    checked = true
                    saving = true
                    saveError = null
                    scope.launch {
                        runCatching {
                            progress = progressStore.recordLessonResult(
                                languageCode = language.code,
                                moduleId = module.id,
                                lessonId = lesson.id,
                                correctAnswers = if (isCorrect) 1 else 0,
                                totalQuestions = 1
                            )
                        }.onFailure {
                            checked = false
                            saveError = "No se pudo guardar el progreso. Tu respuesta sigue seleccionada; vuelve a comprobarla para reintentar."
                        }
                        saving = false
                    }
                } else if (checked) {
                    val nextLesson = module.lessons.firstOrNull { it.id != lesson.id }
                    if (nextLesson != null) {
                        navController.navigate("lesson/${languageCode.lowercase()}/${module.id}/${nextLesson.id}")
                    } else {
                        navController.navigateUp()
                    }
                }
            },
            enabled = if (checked) !saving else selectedAnswer.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(when {
                saving -> "Guardando..."
                checked -> "Continuar"
                else -> "Comprobar respuesta"
            })
        }

        Text(
            "Progreso guardado · ${progress.totalXp} XP · Nivel ${progress.currentLevel}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        OutlinedButton(onClick = { navController.navigateUp() }, modifier = Modifier.fillMaxWidth()) {
            Text("Volver al módulo")
        }
    }
}
