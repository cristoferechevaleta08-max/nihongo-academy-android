package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.model.LanguageEntry
import com.nihongoacademy.data.SessionStore
import kotlinx.coroutines.launch

private val academyLanguages = listOf(
    LanguageEntry("ja", "Japonés", "日本語", "Kana/Kanji", "LTR", listOf("hiragana", "katakana", "kanji", "romaji", "vocabulary", "grammar", "listening", "reading", "n5", "n4", "n3", "n2", "n1")),
    LanguageEntry("en", "Inglés", "English", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("ko", "Coreano", "한국어", "Hangul", "LTR", listOf("hangul", "vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("zh-Hans", "Chino mandarín", "中文", "Han/Pinyin", "LTR", listOf("pinyin", "hanzi", "vocabulary", "grammar", "listening", "reading")),
    LanguageEntry("fr", "Francés", "Français", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("de", "Alemán", "Deutsch", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("it", "Italiano", "Italiano", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("pt", "Portugués", "Português", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("es", "Español", "Español", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("ru", "Ruso", "Русский", "Cyrillic", "LTR", listOf("alphabet", "vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("ar", "Árabe", "العربية", "Arabic", "RTL", listOf("alphabet", "vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("nl", "Neerlandés", "Nederlands", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("tr", "Turco", "Türkçe", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("hi", "Hindi", "हिन्दी", "Devanagari", "LTR", listOf("alphabet", "vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("th", "Tailandés", "ไทย", "Thai", "LTR", listOf("alphabet", "vocabulary", "phrases", "grammar", "listening", "reading"))
)

@Composable
fun LanguageSelectionScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionStore = remember { SessionStore(context) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text("Elige tu idioma", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Selecciona el idioma que quieres aprender.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 6.dp, bottom = 18.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(academyLanguages, key = { it.code }) { entry ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { scope.launch { sessionStore.saveLanguage(entry.code); navController.navigate("home/${entry.code.lowercase()}") { popUpTo("language") { inclusive = true }; launchSingleTop = true } } },
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = entry.nameNative,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = entry.nameEs,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
