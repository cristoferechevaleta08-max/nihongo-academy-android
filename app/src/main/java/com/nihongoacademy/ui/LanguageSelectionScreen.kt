package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.model.LanguageEntry

@Composable
fun LanguageSelectionScreen(
    navController: NavController,
    repository: Any? = null
) {
    val languages = listOf(
        LanguageEntry("en", "Inglés", "English", "Latin", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("zh-Hans", "Mandarín", "中文", "Han", "LTR", listOf("hanzi", "pinyin")),
        LanguageEntry("es", "Español", "Español", "Latin", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("hi", "Hindi", "हिन्दी", "Devanagari", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("ar", "Árabe", "العربية", "Arabic", "RTL", listOf("vocabulary", "phrases")),
        LanguageEntry("fr", "Francés", "Français", "Latin", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("pt", "Portugués", "Português", "Latin", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("bn", "Bengalí", "বাংলা", "Bengali", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("ru", "Ruso", "Русский", "Cyrillic", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("de", "Alemán", "Deutsch", "Latin", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("ja", "Japonés", "日本語", "Kana/Kanji", "LTR", listOf("hiragana", "katakana", "romaji", "n5")),
        LanguageEntry("ko", "Coreano", "한국어", "Hangul", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("it", "Italiano", "Italiano", "Latin", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("tr", "Turco", "Türkçe", "Latin", "LTR", listOf("vocabulary", "phrases")),
        LanguageEntry("id", "Indonesio", "Bahasa Indonesia", "Latin", "LTR", listOf("vocabulary", "phrases"))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Selecciona tu idioma",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Un catálogo pensado como un cuaderno de estudio y no como una lista genérica.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(languages) { entry ->
                LanguageCard(
                    entry = entry,
                    onClick = { navController.navigate("home") }
                )
            }
        }
    }
}

@Composable
private fun LanguageCard(
    entry: LanguageEntry,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(1.dp, Color(0xFF1F1F1F), RoundedCornerShape(18.dp)),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = entry.nameNative,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = entry.script,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = entry.code.uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
