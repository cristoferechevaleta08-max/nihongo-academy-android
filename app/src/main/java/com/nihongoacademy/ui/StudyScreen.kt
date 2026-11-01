package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.nihongoacademy.data.SessionStore
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.model.StudyProgress

@Composable
fun StudyScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionStore = remember { SessionStore(context.applicationContext) }
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    var languageCode by remember { mutableStateOf("ja") }
    var progress by remember { mutableStateOf(StudyProgress()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        languageCode = sessionStore.readLanguage() ?: "ja"
        progress = progressStore.getProgress(languageCode)
        loading = false
    }

    val totalAnswered = progress.recentResults.sumOf { it.totalQuestions }
    val totalCorrect = progress.recentResults.sumOf { it.correctAnswers }
    val accuracy = if (totalAnswered == 0) null else totalCorrect * 100 / totalAnswered
    val recent = progress.recentResults.asReversed()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Text("Estadísticas de estudio", style = MaterialTheme.typography.headlineMedium)
        }
        Text(
            "Resumen de tu actividad guardada · ${languageCode.uppercase()}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (loading) {
            Text("Cargando tu progreso…", style = MaterialTheme.typography.bodyMedium)
        } else {
            Card(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth().padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text("Tu avance", style = MaterialTheme.typography.titleLarge)
                    StudyStatRow("Nivel actual", progress.currentLevel.toString())
                    StudyStatRow("Experiencia acumulada", "${progress.totalXp} EXP")
                    StudyStatRow("Lecciones completadas", progress.completedLessons.size.toString())
                    StudyStatRow("Exámenes aprobados", progress.completedExams.size.toString())
                    StudyStatRow("Racha actual", "${progress.streakDays} días")
                    StudyStatRow("Precisión reciente", accuracy?.let { "${it}%" } ?: "Aún sin resultados")
                }
            }

            Card(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth().padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Actividad reciente", style = MaterialTheme.typography.titleLarge)
                    if (recent.isEmpty()) {
                        Text(
                            "Todavía no hay actividad registrada. Completa una lección o una evaluación para comenzar tu historial.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        recent.take(10).forEachIndexed { index, result ->
                            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                                Text(
                                    if (result.isExam) "Evaluación · ${result.moduleId}" else "Lección · ${result.moduleId}",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    "${result.correctAnswers} de ${result.totalQuestions} respuestas correctas · +${result.xpEarned} EXP",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (index < recent.take(10).lastIndex) {
                                androidx.compose.material3.HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }

        androidx.compose.material3.OutlinedButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}

@Composable
private fun StudyStatRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
    }
}
