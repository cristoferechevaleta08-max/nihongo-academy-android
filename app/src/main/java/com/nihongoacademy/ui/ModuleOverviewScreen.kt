package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.LanguageModule
import com.nihongoacademy.model.StudyProgress
import com.nihongoacademy.ui.theme.AcademyColors

private val ModulePaper = AcademyColors.Background
private val ModuleInk = AcademyColors.TextPrimary
private val ModuleWhite = AcademyColors.Surface

@Composable
fun ModuleOverviewScreen(navController: NavController, languageCode: String = "ja") {
    val language = LanguageCatalogFactory.buildSupportedLanguages().firstOrNull { it.code.equals(languageCode, ignoreCase = true) }
        ?: LanguageCatalogFactory.buildSupportedLanguages().first()
    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = language.code.uppercase())) }
    LaunchedEffect(languageCode) { progress = progressStore.getProgress(languageCode).copy(languageCode = language.code.uppercase()) }
    val completedModuleCount = language.modules.count { it.midExam.id in progress.completedExams && it.finalExam.id in progress.completedExams }
    val completion = if (language.modules.isEmpty()) 0 else completedModuleCount * 100 / language.modules.size

    Column(Modifier.fillMaxSize().background(ModulePaper).padding(horizontal = 22.dp, vertical = 24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("PLAN DE ESTUDIO", color = ModuleInk.copy(alpha = 0.55f), style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp))
        Text("Módulos · ${language.code.uppercase()}", color = ModuleInk, style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp))
        Spacer(Modifier.height(1.dp).fillMaxWidth().background(ModuleInk.copy(alpha = 0.12f)))
        Text("${language.modules.size.toString().padStart(2, '0')} MÓDULOS   /   ${completion.toString().padStart(2, '0')}% COMPLETADO", color = ModuleInk, style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.8.sp, fontSize = 10.sp, fontWeight = FontWeight.SemiBold))
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
    Row(Modifier.fillMaxWidth().height(116.dp).background(ModuleWhite, RoundedCornerShape(24.dp))
        .border(1.dp, AcademyColors.Border, RoundedCornerShape(24.dp)).clickable(onClick = onClick)
        .padding(horizontal = 18.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text("MÓDULO ${module.number.toString().padStart(2, '0')}", color = AcademyColors.Sakura, style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.5.sp))
            Text(module.title, color = ModuleInk, style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Serif, fontSize = 22.sp), fontWeight = FontWeight.Bold)
            Text("${module.lessons.size} lecciones  ·  ${module.targetLevel}", color = ModuleInk.copy(alpha = 0.62f), style = MaterialTheme.typography.bodySmall)
            if (isCompleted) Text("COMPLETADO", color = ModuleInk, style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp, fontWeight = FontWeight.Bold))
        }
        Text("→", color = AcademyColors.Sakura, fontSize = 27.sp, fontWeight = FontWeight.Light)
    }
}
