package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Lección 7 · Japonés",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Bloque: saludar y presentarse", style = MaterialTheme.typography.labelLarge)
                Text(prompt, style = MaterialTheme.typography.titleLarge)
                Text("Objetivo: reconocer la fórmula de saludo básica y su uso contextual.", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Text("Elige la respuesta correcta", style = MaterialTheme.typography.titleMedium)

        options.forEach { option ->
            OutlinedButton(
                onClick = { answer = option },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, if (answer == option) Color(0xFF141414) else Color(0xFFBDBDBD), RoundedCornerShape(14.dp))
            ) {
                Text(option)
            }
        }

        OutlinedTextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("Respuesta escrita") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { navController.navigate("home") }, modifier = Modifier.weight(1f)) {
                Text("Comprobar")
            }
            Button(onClick = { navController.navigate("home") }, modifier = Modifier.weight(1f)) {
                Text("Siguiente")
            }
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Tutor de práctica", style = MaterialTheme.typography.labelLarge)
                Text("Recomendación: repite la pronunciación y revisa la forma del saludo en contexto.", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
