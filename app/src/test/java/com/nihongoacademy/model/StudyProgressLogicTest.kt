package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.ModuleLesson
import com.nihongoacademy.model.StudyProgress
import kotlinx.coroutines.launch

private data class LessonQuestion(
    val prompt: String,
    val choices: List<String>,
    val correctAnswer: String
)

@Composable
fun LessonScreen(
    navController: NavController,
    languageCode: String = "ja",
    moduleId: String = "module_1",
    lessonId: String = "m1_l1"
) {
    val language = LanguageCatalogFactory.buildSupportedLanguages()
        .firstOrNull { it.code.equals(languageCode, ignoreCase = true) }
        ?: LanguageCatalogFactory.buildSupportedLanguages().first()
    val module = language.modules.firstOrNull { it.id == moduleId }
        ?: language.modules.first()
    val lesson = module.lessons.firstOrNull { it.id == lessonId }
        ?: module.lessons.first()
    val question = lessonQuestionFor(lesson)

    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = language.code.uppercase())) }
    val coroutineScope = rememberCoroutineScope()
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var wasAnswered by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    LaunchedEffect(languageCode) {
        progress = progressStore.getProgress().copy(languageCode = language.code.uppercase())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Lección", style = MaterialTheme.typography.labelLarge)
        Text(lesson.title, style = MaterialTheme.typography.headlineMedium)
        Text(lesson.description, style = MaterialTheme.typography.bodyMedium)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(question.prompt, style = MaterialTheme.typography.titleLarge)
                question.choices.forEach { option ->
                    OutlinedButton(
                        onClick = { if (!wasAnswered) selectedAnswer = option },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(option)
                    }
                }
            }
        }

        Button(
            onClick = {
                if (selectedAnswer == null || wasAnswered) return@Button
                val correct = selectedAnswer == question.correctAnswer
                isCorrect = correct
                wasAnswered = true

                coroutineScope.launch {
                    progress = progressStore.recordLessonResult(
                        languageCode = language.code.uppercase(),
                        moduleId = module.id,
                        lessonId = lesson.id,
                        correctAnswers = if (correct) 1 else 0,
                        totalQuestions = 1
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar respuesta")
        }

        if (wasAnswered) {
            Text(
                text = if (isCorrect) "Correcto. Has ganado XP por esta lección." else "No es correcto. La respuesta era: ${question.correctAnswer}",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al módulo")
        }

        Text(
            text = "XP total: ${progress.totalXp} · Racha: ${progress.streakDays} días",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

private fun lessonQuestionFor(lesson: ModuleLesson): LessonQuestion = when (lesson.id) {
    "m1_l1" -> LessonQuestion("¿Qué significa こんにちは?", listOf("Hola", "Gracias", "Agua", "Casa"), "Hola")
    "m1_l2" -> LessonQuestion("¿Qué significa ありがとう?", listOf("Casa", "Gracias", "Luz", "Libro"), "Gracias")
    "m2_l1" -> LessonQuestion("¿Qué significa こんばんは?", listOf("Buenas noches", "Escuela", "Pan", "Amigo"), "Buenas noches")
    "m2_l2" -> LessonQuestion("¿Qué significa さようなら?", listOf("Hasta luego", "Perdón", "Mañana", "Trabajo"), "Hasta luego")
    else -> LessonQuestion(
        prompt = "¿Qué significa '${lesson.title}' en contexto?",
        choices = listOf("Opción A", "Opción B", "Opción C", "Opción D"),
        correctAnswer = "Opción A"
    )
}
