package com.nihongoacademy.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun StudyScreen(navController: NavController) {
    var answer by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { 18 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { 18 })
    ) {
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
                    Text("¿Qué significa こんにちは?", style = MaterialTheme.typography.titleLarge)
                    Text("Objetivo: reconocer la forma básica de saludo y usarla en contexto cotidiano.", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Text("Elige la respuesta correcta", style = MaterialTheme.typography.titleMedium)

            val options = listOf("Hola", "Gracias", "Agua", "Casa")
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
                Button(onClick = { navController.navigate("profile") }, modifier = Modifier.weight(1f)) {
                    Text("Tutor")
                }
            }

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
                    .padding(14.dp)
                    .fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Tutor de práctica", style = MaterialTheme.typography.labelLarge)
                    Text("Recomendación: repite la pronunciación y relaciona el saludo con el contexto de la conversación.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
