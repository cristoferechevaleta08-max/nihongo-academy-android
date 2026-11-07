package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.LanguageModule
import com.nihongoacademy.model.StudyProgress

@Composable
fun ModuleOverviewScreen(navController: NavController, languageCode: String = "ja") {
    val language = LanguageCatalogFactory.buildSupportedLanguages().firstOrNull { it.code.equals(languageCode, ignoreCase = true) }
        ?: LanguageCatalogFactory.buildSupportedLanguages().first()
    val context = androidx.compose.ui.platform.LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = language.code.uppercase())) }
    LaunchedEffect(languageCode) { progress = progressStore.getProgress(languageCode).copy(languageCode = language.code.uppercase()) }
    val completedModuleCount = language.modules.count { it.midExam.id in progress.completedExams && it.finalExam.id in progress.completedExams }
    val completion = if (language.modules.isEmpty()) 0 else completedModuleCount * 100 / language.modules.size

    Column(
        Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 22.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            "PLAN DE ESTUDIO",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.8.sp)
        )
        Text(
            "Módulos · ${language.code.uppercase()}",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(Modifier.height(1.dp).fillMaxWidth().background(MaterialTheme.colorScheme.outline.copy(alpha = 0.45f)))
        Text(
            "${language.modules.size.toString().padStart(2, '0')} MÓDULOS   /   ${completion.toString().padStart(2, '0')}% COMPLETADO",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.1.sp)
        )
        LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(language.modules, key = { it.id }) { module ->
                val completed = module.midExam.id in progress.completedExams && module.finalExam.id in progress.completedExams
                ModuleEditorialCard(module, completed) { navController.navigate("module/${languageCode.lowercase()}/${module.id}") }
            }
        }
    }
}

@Composable
private fun ModuleEditorialCard(module: LanguageModule, isCompleted: Boolean, onClick: () -> Unit) {
    val shape = RoundedCornerShape(22.dp)
    Row(
        Modifier.fillMaxWidth()
            .heightIn(min = 112.dp)
            .background(MaterialTheme.colorScheme.surface, shape)
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.55f), shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 17.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                "MÓDULO ${module.number.toString().padStart(2, '0')}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp)
            )
            Text(
                module.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                "${module.lessons.size} lecciones  ·  ${module.targetLevel}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
            if (isCompleted) {
                Text(
                    "COMPLETADO",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.1.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
        Text("›", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.headlineMedium)
    }
}
