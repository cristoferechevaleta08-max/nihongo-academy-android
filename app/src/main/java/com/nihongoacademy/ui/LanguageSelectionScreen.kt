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
fun LanguageSelectionScreen(navController: NavController) {
    val languages = listOf(
        "ja" to "Japonés",
        "es" to "Español",
        "en" to "Inglés",
        "fr" to "Francés",
        "de" to "Alemán"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Selecciona un idioma", style = MaterialTheme.typography.headlineMedium)

        languages.forEach { (code, label) ->
            Button(
                onClick = { navController.navigate("home/$code") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(label)
            }
        }
    }
}
