package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Resumen por idioma", style = MaterialTheme.typography.headlineMedium)
        Text("Japonés • 82%", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = { navController.navigate("study") }) {
            Text("Estudiar")
        }
        Button(onClick = { navController.navigate("settings") }) {
            Text("Ajustes")
        }
        Button(onClick = { navController.navigate("collection") }) {
            Text("Colección")
        }
    }
}
