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
import kotlinx.coroutines.launch

@Composable
fun ModuleExamScreen(
    navController: NavController,
    languageCode: String,
    moduleId: String,
    examType: String
) {
    val languages = remember { LanguageCatalogFactory.buildSupportedLanguages() }
    val language = languages.firstOrNull { it.code.equals(languageCode, true) } ?: languages.first()
    val module = language.modules.firstOrNull { it.id == moduleId } ?: language.modules.first()
    val exam = if (examType == "final") module.finalExam else module.midExam
    val question = exam.questions.first()
    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    val scope = rememberCoroutineScope()

    var answer by remember { mutableStateOf("") }
    var evaluated by remember { mutableStateOf(false) }
    var saving by remember { mutableStateOf(false) }
    var saveMessage by remember { mutableStateOf<String?>(null) }
    val correct = answer.trim().equals(question.correctAnswer.trim(), ignoreCase = true)

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(exam.type.label, style = MaterialTheme.typography.labelLarge)
        Text(exam.title, style = MaterialTheme.typography.headlineMedium)
        Text("Módulo ${module.number} · ${language.name}", style = MaterialTheme.typography.bodyMedium)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(question.difficulty.label, style = MaterialTheme.typography.labelLarge)
                Text(question.prompt, style = MaterialTheme.typography.titleLarge)
                question.options.forEach { option ->
                    OutlinedButton(
                        onClick = { if (!evaluated) answer = option },
                        enabled = !evaluated,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (answer == option) "✓  $option" else option)
                    }
                }
            }
        }

        if (evaluated) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        if (correct) "Evaluación superada" else "Evaluación completada",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Text("Respuesta correcta: ${question.correctAnswer}")
                    Text(question.feedback)
                    Text("Pista: ${question.hint}")
                    saveMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                }
            }
        }

        Button(
            onClick = {
                if (!evaluated) {
                    evaluated = true
                    saving = true
                    scope.launch {
                        runCatching {
                            progressStore.recordExamResult(
                                languageCode = language.code,
                                moduleId = module.id,
                                examId = exam.id,
                                correctAnswers = if (correct) 1 else 0,
                                totalQuestions = 1
                            )
                        }.onFailure {
                            saveMessage = "No se pudo guardar el resultado. Inténtalo de nuevo."
                        }
                        saving = false
                    }
                } else {
                    navController.navigateUp()
                }
            },
            enabled = if (evaluated) !saving else answer.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(when {
                saving -> "Guardando resultado..."
                evaluated -> "Volver al módulo"
                else -> "Enviar evaluación"
            })
        }
    }
}
