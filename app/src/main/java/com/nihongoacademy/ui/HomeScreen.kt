package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.nihongoacademy.model.StudyProgress
import kotlinx.coroutines.launch

@Composable
fun ModuleDetailScreen(
    navController: NavController,
    languageCode: String = "ja",
    moduleId: String = "module_1"
) {
    val language = LanguageCatalogFactory.buildSupportedLanguages()
        .firstOrNull { it.code.equals(languageCode, ignoreCase = true) }
        ?: LanguageCatalogFactory.buildSupportedLanguages().first()
    val module = language.modules.firstOrNull { it.id == moduleId }
        ?: language.modules.first()

    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = language.code.uppercase())) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(languageCode) {
        progress = progressStore.getProgress().copy(languageCode = language.code.uppercase())
    }

    var currentStage by remember { mutableStateOf("lesson") }
    var score by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("Módulo ${module.number}", style = MaterialTheme.typography.headlineMedium)
        Text(module.title, style = MaterialTheme.typography.titleLarge)

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Lección principal", style = MaterialTheme.typography.labelLarge)
                module.lessons.forEach { lesson ->
                    Text("• ${lesson.title} · ${lesson.durationMinutes} min")
                }
                Text("Objetivo: reforzar vocabulario, lectura y aplicación práctica del bloque.")
            }
        }

        if (currentStage == "lesson") {
            Button(
                onClick = { currentStage = "mid" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar práctica")
            }
        }

        if (currentStage == "mid") {
            val question = module.midExam.questions.first()
            Text("${module.midExam.type.label}", style = MaterialTheme.typography.titleMedium)

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(question.prompt, style = MaterialTheme.typography.titleLarge)
                    question.options.forEach { option ->
                        OutlinedButton(
                            onClick = { selected = option },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(option)
                        }
                    }
                }
            }

            Button(
                onClick = {
                    val isCorrect = selected.equals(question.correctAnswer, ignoreCase = true)
                    if (isCorrect) score += 1
                    feedback = if (isCorrect) {
                        "Correcto. ${question.feedback}"
                    } else {
                        "No es correcto. La respuesta era: ${question.correctAnswer}."
                    }
                    currentStage = "final"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar examen sorpresa")
            }
        }

        if (currentStage == "final") {
            val question = module.finalExam.questions.first()
            Text("${module.finalExam.type.label}", style = MaterialTheme.typography.titleMedium)

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(question.prompt, style = MaterialTheme.typography.titleLarge)
                    Text(question.hint, style = MaterialTheme.typography.bodyMedium)
                }
            }

            feedback?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (it.contains("Correcto")) MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                            else MaterialTheme.colorScheme.error.copy(alpha = 0.08f),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(14.dp)
                ) {
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        progress = progressStore.markModuleCompleted(language.code.uppercase(), module.id)
                    }
                    navController.navigate("modules/${languageCode.lowercase()}")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver a módulos")
            }
        }
    }
}
