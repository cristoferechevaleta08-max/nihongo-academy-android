package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.LanguageModule
import com.nihongoacademy.model.StudyProgress

@Composable
fun ModuleOverviewScreen(navController: NavController, languageCode: String = "ja") {
    val language = LanguageCatalogFactory.buildSupportedLanguages()
        .firstOrNull { it.code.equals(languageCode, ignoreCase = true) }
        ?: LanguageCatalogFactory.buildSupportedLanguages().first()

    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = language.code.uppercase())) }

    LaunchedEffect(languageCode) {
        progress = progressStore.getProgress().copy(languageCode = language.code.uppercase())
    }

    val completedModuleCount = language.modules.count { module ->
        module.midExam.id in progress.completedExams && module.finalExam.id in progress.completedExams
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("${language.name} · Módulos", style = MaterialTheme.typography.headlineMedium)
        Text(
            "$completedModuleCount/${language.modules.size} módulos evaluados · ${progress.totalXp} XP",
            style = MaterialTheme.typography.bodyLarge
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(language.modules) { module ->
                ModuleCard(
                    module = module,
                    isCompleted = module.midExam.id in progress.completedExams && module.finalExam.id in progress.completedExams,
                    onClick = { navController.navigate("module/${languageCode.lowercase()}/${module.id}") }
                )
            }
        }
    }
}

@Composable
private fun ModuleCard(
    module: LanguageModule,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Módulo ${module.number}", style = MaterialTheme.typography.labelLarge)
                if (module.isIntegrative) {
                    Text("Integrador", style = MaterialTheme.typography.labelLarge)
                }
            }
            Text(module.title, style = MaterialTheme.typography.titleLarge)
            Text(module.targetLevel, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("• ${module.lessons.size} lecciones")
            Text("• Examen sorpresa a la mitad")
            Text("• Examen sorpresa final")
            if (isCompleted) {
                Text("Completado", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
