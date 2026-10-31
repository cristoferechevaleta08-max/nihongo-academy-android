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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    languageCode: String = "ja"
) {
    val label = when (languageCode.lowercase()) {
        "ja" -> "Japonés"
        "es" -> "Español"
        "en" -> "Inglés"
        "fr" -> "Francés"
        else -> languageCode.uppercase()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("Resumen por idioma", style = MaterialTheme.typography.headlineMedium)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(label)
                Text("1200 XP · 3 módulos completados")
                Text("Racha: 7 días")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Progreso actual")
                Text("3/10 módulos")
                Text("24 lecciones revisadas")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { navController.navigate("modules/$languageCode") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Estudiar")
            }
            Button(
                onClick = { navController.navigate("settings") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Ajustes")
            }
        }

        Button(
            onClick = { navController.navigate("collection") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Colección")
        }
    }
}
