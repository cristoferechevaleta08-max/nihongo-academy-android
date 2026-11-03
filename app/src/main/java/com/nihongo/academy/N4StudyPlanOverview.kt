package com.nihongo.academy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.launch

/** Plan N4 con avance, pausas y evaluaciones semanales guardadas localmente. */
@Composable
internal fun N4StudyPlanOverview(
    ds: DataStore<Preferences>,
    n5Unlocked: Boolean,
    modifier: Modifier = Modifier
) {
    val progress by ds.n4StudyProgress().collectAsState(initial = N4StudyProgress())
    val scope = rememberCoroutineScope()
    var quizWeek by remember { mutableIntStateOf(0) }
    var questions by remember { mutableStateOf<List<N4WeeklyQuestion>>(emptyList()) }
    var quizIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableIntStateOf(-1) }
    var correct by remember { mutableIntStateOf(0) }
    var submitted by remember { mutableStateOf(false) }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("Plan N4 · 16 semanas", style = MaterialTheme.typography.headlineMedium)
        Text(
            "40 minutos diarios · gramática, kanji, lectura y comprensión auditiva",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (!n5Unlocked) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Nivel bloqueado", style = MaterialTheme.typography.titleLarge)
                    Text("Completá y aprobá las 12 semanas del plan N5 para habilitar esta ruta.")
                }
            }
        } else {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        if (progress.isComplete) "¡Plan N4 completado!" else "Semana ${progress.currentWeek} de 16",
                        style = MaterialTheme.typography.titleLarge
                    )
                    LinearProgressIndicator(
                        progress = { progress.completedWeeks.size / N4_PLAN_WEEKS.toFloat() },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text("${progress.completedWeeks.size} de 16 semanas aprobadas")
                    Text(
                        if (progress.paused) "El plan está pausado. Tu avance se conserva en este dispositivo."
                        else "Cada evaluación se aprueba con al menos 70%. Si no llegás, repetís esa semana."
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            onClick = {
                                scope.launch {
                                    ds.saveN4StudyProgress(if (progress.paused) progress.resume() else progress.pause())
                                }
                            },
                            enabled = !progress.isComplete && quizWeek == 0
                        ) { Text(if (progress.paused) "Reanudar plan" else "Pausar plan") }

                        if (!progress.isComplete) {
                            Button(
                                onClick = {
                                    questions = buildN4WeeklyAssessment(progress.currentWeek)
                                    quizWeek = progress.currentWeek
                                    quizIndex = 0
                                    selectedAnswer = -1
                                    correct = 0
                                    submitted = false
                                    resultText = ""
                                },
                                enabled = !progress.paused && quizWeek == 0
                            ) { Text("Evaluación semana ${progress.currentWeek}") }
                        }
                    }
                    if (progress.lastScoreByWeek.isNotEmpty()) {
                        Text(
                            "Últimos resultados: " + progress.lastScoreByWeek.toSortedMap()
                                .entries.joinToString { "S${it.key}: ${it.value}%" }
                        )
                    }
                    if (progress.attemptsByWeek.isNotEmpty()) {
                        Text(
                            "Intentos: " + progress.attemptsByWeek.toSortedMap()
                                .entries.joinToString { "Semana ${it.key}: ${it.value}" }
                        )
                    }
                }
            }
        }

        if (quizWeek > 0 && n5Unlocked) {
            val q = questions.getOrNull(quizIndex)
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Evaluación N4 · Semana $quizWeek", style = MaterialTheme.typography.titleLarge)
                    if (submitted) {
                        Text(resultText, style = MaterialTheme.typography.titleMedium)
                        Text("Resultado: $correct de ${questions.size} · ${(correct * 100 / questions.size)}%")
                        Button(onClick = { quizWeek = 0; questions = emptyList() }, modifier = Modifier.fillMaxWidth()) {
                            Text("Volver al plan")
                        }
                    } else if (q != null) {
                        Text("Pregunta ${quizIndex + 1} de ${questions.size} · ${q.section}",
                            color = MaterialTheme.colorScheme.primary)
                        Text(q.prompt, style = MaterialTheme.typography.titleMedium)
                        q.options.forEachIndexed { index, option ->
                            OutlinedButton(
                                onClick = { selectedAnswer = index },
                                enabled = selectedAnswer < 0,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text((if (selectedAnswer == index) "● " else "○ ") + option)
                            }
                        }
                        if (selectedAnswer >= 0) {
                            val isCorrect = selectedAnswer == q.answerIndex
                            Text(
                                if (isCorrect) "Correcto" else "Respuesta correcta: ${q.options[q.answerIndex]}",
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(q.explanation, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Button(
                                onClick = {
                                    val nextCorrect = correct + if (isCorrect) 1 else 0
                                    if (quizIndex == questions.lastIndex) {
                                        scope.launch {
                                            val score = nextCorrect * 100 / questions.size
                                            val saved = ds.recordN4WeeklyAssessment(score)
                                            correct = nextCorrect
                                            resultText = if (saved.currentWeek > quizWeek || saved.isComplete) {
                                                "¡Semana aprobada! La siguiente semana quedó desbloqueada."
                                            } else {
                                                "Semana no aprobada. Repasá los errores y volvé a intentarlo."
                                            }
                                            submitted = true
                                        }
                                    } else {
                                        correct = nextCorrect
                                        quizIndex++
                                        selectedAnswer = -1
                                    }
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) { Text(if (quizIndex == questions.lastIndex) "Enviar evaluación" else "Siguiente pregunta") }
                        }
                    }
                }
            }
        }

        Text("Recorrido semanal", style = MaterialTheme.typography.titleLarge)
        n4SixteenWeekPlan.forEach { week ->
            val completed = week.week in progress.completedWeeks
            val current = n5Unlocked && !progress.isComplete && week.week == progress.currentWeek
            val locked = !n5Unlocked || (!completed && !current)
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Semana ${week.week} · ${week.title}", style = MaterialTheme.typography.titleLarge)
                    }
                    Text(
                        when {
                            completed -> "Aprobada · ${progress.lastScoreByWeek[week.week] ?: 0}%"
                            current && progress.paused -> "Semana actual · pausada"
                            current -> "Semana actual · disponible"
                            locked -> if (n5Unlocked) "Bloqueada · aprobá las semanas anteriores" else "Se habilita al completar N5"
                            else -> "Pendiente"
                        },
                        color = if (completed || current) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(week.focus, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Actividades", style = MaterialTheme.typography.titleMedium)
                    week.activities.forEachIndexed { index, activity -> Text("${index + 1}. $activity") }
                    Text("Evaluación", style = MaterialTheme.typography.titleMedium)
                    Text(week.assessment)
                }
            }
        }
    }
}
