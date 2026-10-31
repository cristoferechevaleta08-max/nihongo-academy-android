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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ModuleDetailScreen(
    navController: NavController,
    languageCode: String = "ja",
    moduleId: String = "module_1"
) {
    val moduleTitle = when (moduleId) {
        "module_1" -> "Fundamentos básicos"
        "module_2" -> "Saludos y frases esenciales"
        "module_3" -> "Vocabulario cotidiano"
        else -> "Módulo"
    }

    val lessons = listOf(
        "lesson_1" to "Lección 1 · Introducción",
        "lesson_2" to "Lección 2 · Práctica",
        "lesson_3" to "Lección 3 · Repaso"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("$moduleTitle", style = MaterialTheme.typography.headlineMedium)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Lecciones del módulo")

                lessons.forEach { (lessonId, title) ->
                    Button(
                        onClick = { navController.navigate("lesson/$languageCode/$moduleId/$lessonId") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(title)
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigate("home/$languageCode") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Marcar módulo como completado")
        }
    }
}
