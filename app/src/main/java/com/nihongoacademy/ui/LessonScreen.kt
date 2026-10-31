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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LessonScreen(
    navController: NavController,
    languageCode: String = "ja",
    moduleId: String = "module_1",
    lessonId: String = "lesson_1"
) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    val options = listOf("Hola", "Gracias", "Agua", "Casa")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("Lección · ${lessonId.uppercase()}", style = MaterialTheme.typography.headlineMedium)
        Text("Módulo: ${moduleId}", style = MaterialTheme.typography.titleMedium)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Bloque: saludos y presentaciones")
                Text("¿Qué significa こんにちは?", style = MaterialTheme.typography.titleLarge)
            }
        }

        options.forEach { option ->
            OutlinedButton(
                onClick = { selectedAnswer = option },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(option)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { navController.navigate("home/$languageCode") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Comprobar")
            }
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Volver")
            }
        }

        if (selectedAnswer != null) {
            Text("Seleccionaste: $selectedAnswer")
        }
    }
}
