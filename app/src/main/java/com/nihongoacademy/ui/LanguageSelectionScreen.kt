package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(languages) { entry ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("home/${entry.code.lowercase()}")
                    },
                shape = RoundedCornerShape(18.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = entry.nativeName,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = entry.code.uppercase(),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
