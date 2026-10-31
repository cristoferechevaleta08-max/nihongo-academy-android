package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.nihongoacademy.model.StudyProgress

@Composable
fun HomeScreen(navController: NavController, languageCode: String = "ja") {
    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = languageCode.uppercase())) }

    LaunchedEffect(languageCode) {
        progress = progressStore.getProgress().copy(languageCode = languageCode.uppercase())
    }

    val languageLabel = when (languageCode.lowercase()) {
        "ja" -> "Japonés"
        "es" -> "Español"
        "en" -> "Inglés"
        "fr" -> "Francés"
        "de" -> "Alemán"
        "pt" -> "Portugués"
        "it" -> "Italiano"
        "ru" -> "Ruso"
        "ko" -> "Coreano"
        "zh" -> "Mandarín"
        "ar" -> "Árabe"
        else -> languageCode.uppercase()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("Panel del alumno", style = MaterialTheme.typography.labelLarge)
        Text(languageLabel, style = MaterialTheme.typography.headlineMedium)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Nivel actual")
                Text("${progress.totalXp} XP · Racha de ${progress.streakDays} días")
                Text("${progress.completedModules.size} módulos completados · ${progress.completedLessons.size} lecciones revisadas")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Historial reciente")
                if (progress.recentResults.isEmpty()) {
                    Text("Todavía no has realizado ninguna lección.")
                } else {
                    progress.recentResults.takeLast(5).forEach { result ->
                        Text(
                            text = "Lección ${result.lessonId} · +${result.xpEarned} XP · ${result.correctAnswers}/${result.totalQuestions}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { navController.navigate("modules/${languageCode.lowercase()}") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Estudiar")
            }
            Button(
                onClick = { navController.navigate("profile") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Perfil")
            }
        }

        Button(
            onClick = { navController.navigate("collection") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Colección")
        }
    }
}
