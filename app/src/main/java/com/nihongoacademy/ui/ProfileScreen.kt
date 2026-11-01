package com.nihongoacademy.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalContext
import com.nihongoacademy.data.AuthRepository
import com.nihongoacademy.data.PreferencesStore
import com.nihongoacademy.data.SessionStore
import com.nihongoacademy.data.StudySettings
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.model.StudyProgress

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val progressStore = remember { StudyProgressStore(context) }
    val preferencesStore = remember { PreferencesStore(context) }
    val settingsFlow = remember(preferencesStore) { preferencesStore.studySettingsFlow() }
    val settings by settingsFlow.collectAsState(initial = StudySettings())
    var displayName by remember { mutableStateOf("Estudiante") }
    var email by remember { mutableStateOf("") }
    var languageCode by remember { mutableStateOf("ja") }
    var progress by remember { mutableStateOf(StudyProgress()) }
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        val user = authRepository.currentUser()
        displayName = user?.displayName ?: "Estudiante"
        email = user?.email.orEmpty()
        languageCode = SessionStore(context).readLanguage() ?: "ja"
        progress = progressStore.getProgress(languageCode)
        delay(120)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = if (settings.reduceMotion) EnterTransition.None else fadeIn() + slideInVertically(initialOffsetY = { 20 }),
        exit = if (settings.reduceMotion) ExitTransition.None else fadeOut() + slideOutVertically(targetOffsetY = { 20 })
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Perfil del alumno", style = MaterialTheme.typography.headlineMedium)
            Text("Idioma activo · ${languageCode.uppercase()}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(displayName.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("").uppercase(), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }

                    Column {
                        Text(displayName, style = MaterialTheme.typography.titleLarge)
                        Text(email, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("Nivel ${progress.currentLevel}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Rendimiento", style = MaterialTheme.typography.titleMedium)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        MetricChip("${progress.totalXp}", "XP")
                        MetricChip("${progress.completedLessons.size}", "Lecciones")
                        MetricChip("${progress.streakDays}d", "Racha")
                    }
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Próximo objetivo", style = MaterialTheme.typography.titleMedium)
                    Text(if (progress.completedLessons.isEmpty()) "Completa tu primera lección" else "Continúa con tu siguiente lección", style = MaterialTheme.typography.bodyLarge)
                    Text("${progress.completedLessons.size} lecciones registradas · ${progress.xpToNextLevel} XP para el próximo nivel", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
private fun MetricChip(value: String, label: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.28f)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
