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
import com.nihongoacademy.model.StudyProgress
import kotlinx.coroutines.launch

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

    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = language.code.uppercase())) }
    val coroutineScope = rememberCoroutineScope()

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
        Text("Lección: ${lesson.title}", style = MaterialTheme.typography.headlineMedium)
        Text("Módulo ${module.number} · ${language.name}", style = MaterialTheme.typography.titleMedium)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Tema")
                Text(lesson.prompt, style = MaterialTheme.typography.titleLarge)
            }
        }

        lesson.options.forEach { option ->
            Button(
                onClick = {
                    coroutineScope.launch {
                        val current = progressStore.getProgress()
                        val next = current.copy(languageCode = language.code.uppercase())
                        progress = next
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(option)
            }
        }

        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }

        Text(
            text = "Progreso actual: ${progress.totalXp} XP · Nivel ${progress.currentLevel}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
