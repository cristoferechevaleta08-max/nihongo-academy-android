package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
        "日本語" to "JA",
        "English" to "EN",
        "Español" to "ES",
        "中文" to "ZH",
        "हिन्दी" to "HI",
        "العربية" to "AR",
        "Français" to "FR",
        "Português" to "PT",
        "বাংলা" to "BN",
        "Русский" to "RU",
        "Deutsch" to "DE",
        "한국어" to "KO",
        "Italiano" to "IT",
        "Türkçe" to "TR",
        "Bahasa" to "ID"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(languages.size) { index ->
            val (native, code) = languages[index]
            Card(onClick = { navController.navigate("home") }) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(native, style = MaterialTheme.typography.titleMedium)
                    Text(code, style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}
