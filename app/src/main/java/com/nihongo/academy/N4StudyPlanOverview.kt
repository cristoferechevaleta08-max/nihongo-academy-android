package com.nihongo.academy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/** Vista de consulta del recorrido N4; no marca semanas como aprobadas sin evaluación. */
@Composable
internal fun N4StudyPlanOverview(n5Unlocked: Boolean, modifier: Modifier = Modifier) {
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
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Recorrido preparado", style = MaterialTheme.typography.titleLarge)
                    Text("Las 16 semanas están organizadas en orden. El seguimiento de aprobaciones y las evaluaciones semanales N4 se incorporarán antes de habilitar el avance automático.")
                    LinearProgressIndicator(progress = { 0f }, modifier = Modifier.fillMaxWidth())
                    Text("0 de 16 semanas evaluadas")
                }
            }
        }
        n4SixteenWeekPlan.forEach { week ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Semana ${week.week} · ${week.title}", style = MaterialTheme.typography.titleLarge)
                    Text(week.focus, color = MaterialTheme.colorScheme.primary)
                    Text("Actividades", style = MaterialTheme.typography.titleMedium)
                    week.activities.forEachIndexed { index, activity ->
                        Text("${index + 1}. $activity")
                    }
                    Text("Evaluación prevista", style = MaterialTheme.typography.titleMedium)
                    Text(week.assessment)
                }
            }
        }
    }
}
