package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.model.StudyProgress

@Composable
fun HomeScreen(navController: NavController, languageCode: String = "ja") {
    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = languageCode.uppercase())) }

    LaunchedEffect(languageCode) {
        progress = progressStore.getProgress(languageCode).copy(languageCode = languageCode.uppercase())
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
        "nl" -> "Neerlandés"
        "hi" -> "Hindi"
        "th" -> "Tailandés"
        "zh", "zh-hans" -> "Mandarín"
        "ar" -> "Árabe"
        "tr" -> "Turco"
        else -> languageCode.uppercase()
    }
    val levelProgress = ((500 - progress.xpToNextLevel).coerceIn(0, 500)) / 500f

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("TU ESPACIO DE ESTUDIO", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text("Continúa aprendiendo", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(languageLabel, style = MaterialTheme.typography.titleLarge)
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("NIVEL \${progress.currentLevel}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onPrimary)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("\${progress.totalXp} XP", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
                    Text("\${progress.xpToNextLevel} XP para el siguiente nivel", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onPrimary)
                }
                LinearProgressIndicator(progress = levelProgress, modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.onPrimary, trackColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.25f))
                Text("Racha actual: \${progress.streakDays} días", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
        Text("Tu aprendizaje", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("\${progress.completedLessons.size} lecciones completadas", style = MaterialTheme.typography.titleMedium)
                Text("\${progress.completedExams.size} evaluaciones aprobadas · \${progress.recentResults.count { it.isExam }} intentos registrados", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Button(onClick = { navController.navigate("modules/\${languageCode.lowercase()}") }, modifier = Modifier.fillMaxWidth()) { Text("Continuar estudiando") }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(onClick = { navController.navigate("flashcards/\${languageCode.lowercase()}") }, modifier = Modifier.weight(1f)) { Text("Tarjetas") }
            OutlinedButton(onClick = { navController.navigate("collection") }, modifier = Modifier.weight(1f)) { Text("Colección") }
        }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Recompensas", style = MaterialTheme.typography.titleMedium)
                if (progress.unlockedRewards.isEmpty()) {
                    Text("Completa lecciones y evaluaciones para desbloquear tus primeras insignias.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                } else {
                    progress.unlockedRewards.forEach { reward -> Text("• $reward") }
                }
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        OutlinedButton(onClick = { navController.navigate("profile") }, modifier = Modifier.fillMaxWidth()) { Text("Mi perfil") }
        OutlinedButton(onClick = { navController.navigate("settings") }, modifier = Modifier.fillMaxWidth()) { Text("Configuración") }
        OutlinedButton(onClick = { navController.navigate("language") }, modifier = Modifier.fillMaxWidth()) { Text("Cambiar idioma") }
    }
}
