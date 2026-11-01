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
    val questions = exam.questions
    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    val scope = rememberCoroutineScope()

    var currentIndex by remember(exam.id) { mutableStateOf(0) }
    var answers by remember(exam.id) { mutableStateOf<Map<String, String>>(emptyMap()) }
    var evaluated by remember(exam.id) { mutableStateOf(false) }
    var saving by remember { mutableStateOf(false) }
    var saveMessage by remember { mutableStateOf<String?>(null) }

    val question = questions[currentIndex.coerceIn(questions.indices)]
    val selectedAnswer = answers[question.id].orEmpty()
    val correctAnswers = questions.count { item ->
        answers[item.id].orEmpty().trim().equals(item.correctAnswer.trim(), ignoreCase = true)
    }
    val allAnswered = questions.all { answers[it.id].orEmpty().isNotBlank() }
    val passed = correctAnswers.toFloat() / questions.size.coerceAtLeast(1) >= 0.6f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(exam.type.label, style = MaterialTheme.typography.labelLarge)
        Text(exam.title, style = MaterialTheme.typography.headlineMedium)
        Text("Módulo ${module.number} · ${language.name}", style = MaterialTheme.typography.bodyMedium)

        if (!evaluated) {
            Text("Pregunta ${currentIndex + 1} de ${questions.size}", style = MaterialTheme.typography.titleMedium)
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(question.difficulty.label, style = MaterialTheme.typography.labelLarge)
                    Text(question.prompt, style = MaterialTheme.typography.titleLarge)
                    question.options.forEach { option ->
                        OutlinedButton(
                            onClick = { answers = answers + (question.id to option) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (selectedAnswer == option) "✓  $option" else option)
                        }
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                if (currentIndex > 0) {
                    OutlinedButton(
                        onClick = { currentIndex -= 1 },
                        modifier = Modifier.weight(1f)
                    ) { Text("Anterior") }
                }
                Button(
                    onClick = {
                        if (currentIndex < questions.lastIndex) {
                            currentIndex += 1
                        } else if (allAnswered) {
                            evaluated = true
                            saving = true
                            saveMessage = null
                            scope.launch {
                                runCatching {
                                    progressStore.recordExamResult(
                                        languageCode = language.code,
                                        moduleId = module.id,
                                        examId = exam.id,
                                        correctAnswers = correctAnswers,
                                        totalQuestions = questions.size
                                    )
                                }.onFailure {
                                    evaluated = false
                                    currentIndex = questions.lastIndex
                                    saveMessage = "No se pudo guardar el resultado. Vuelve a enviarlo para reintentar."
                                }
                                saving = false
                            }
                        }
                    },
                    enabled = selectedAnswer.isNotBlank() && !saving,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (currentIndex == questions.lastIndex) "Enviar evaluación" else "Siguiente")
                }
            }
        } else {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        if (passed) "Evaluación superada" else "Necesitas reforzar este módulo",
                        style = MaterialTheme.typography.titleLarge,
                        color = if (passed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Text("$correctAnswers de ${questions.size} respuestas correctas")
                    Text("${(correctAnswers * 100 / questions.size.coerceAtLeast(1))}% de aciertos")
                    Text(if (passed) "Has alcanzado el 60% necesario para aprobar." else "Vuelve a estudiar las lecciones y repite la evaluación.")
                }
            }
            questions.forEachIndexed { index, item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("Pregunta ${index + 1}: ${item.prompt}", style = MaterialTheme.typography.titleMedium)
                        Text("Tu respuesta: ${answers[item.id] ?: "Sin responder"}")
                        Text("Respuesta correcta: ${item.correctAnswer}")
                        Text(item.feedback, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            Button(
                onClick = { navController.navigateUp() },
                enabled = !saving,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (saving) "Guardando resultado..." else "Volver al módulo")
            }
        }
        saveMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }
    }
}
