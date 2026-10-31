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
        Text("Módulo ${module.number}", style = MaterialTheme.typography.headlineMedium)
        Text(module.title, style = MaterialTheme.typography.titleLarge)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Lecciones del módulo")
                module.lessons.forEach { lesson ->
                    Button(
                        onClick = {
                            navController.navigate("lesson/${languageCode.lowercase()}/${module.id}/${lesson.id}")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("${lesson.title} · ${lesson.durationMinutes} min")
                    }
                }
            }
        }

        Button(
            onClick = {
                androidx.lifecycle.viewmodel.compose.viewModel
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar progreso del módulo")
        }

        Text(
            text = "Progreso: ${progress.totalXp} XP · Nivel ${progress.currentLevel}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
