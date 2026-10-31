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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun StudyScreen(navController: NavController) {
    var answer by remember { mutableStateOf("") }
    val prompt = "¿Qué significa こんにちは?"
    val options = listOf("Hola", "Gracias", "Agua", "Casa")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Lección 1 · Japonés", style = MaterialTheme.typography.headlineMedium)
        Text(prompt, style = MaterialTheme.typography.bodyLarge)

        options.forEach { option ->
            OutlinedButton(
                onClick = { answer = option },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(option)
            }
        }

        OutlinedTextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("Respuesta") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { navController.navigate("home") }) {
                Text("Comprobar")
            }
            Button(onClick = { navController.navigate("home") }) {
                Text("Saltar")
            }
        }
    }
}
