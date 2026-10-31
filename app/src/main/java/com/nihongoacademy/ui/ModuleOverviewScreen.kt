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
fun ModuleOverviewScreen(
    navController: NavController,
    languageCode: String = "ja"
) {
    val modules = listOf(
        "module_1" to "Módulo 1 · Fundamentos básicos",
        "module_2" to "Módulo 2 · Saludos y frases esenciales",
        "module_3" to "Módulo 3 · Vocabulario cotidiano",
        "module_4" to "Módulo 4 · Estructuras simples"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Módulos · ${languageCode.uppercase()}", style = MaterialTheme.typography.headlineMedium)

        modules.forEach { (id, title) ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(title, style = MaterialTheme.typography.titleMedium)
                    Button(
                        onClick = { navController.navigate("module/$languageCode/$id") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Abrir módulo")
                    }
                }
            }
        }
    }
}
