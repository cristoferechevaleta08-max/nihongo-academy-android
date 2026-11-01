package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.nihongoacademy.model.LanguageEntry
import com.nihongoacademy.data.SessionStore
import kotlinx.coroutines.launch

private val AcademyPaper = Color(0xFFF9F5F0)
private val AcademyInk = Color(0xFF0A0A0A)
private val AcademyWhite = Color.White

private val academyLanguages = listOf(
    LanguageEntry("ja", "Japonés", "日本語", "Kana/Kanji", "LTR", listOf("hiragana", "katakana", "kanji", "romaji", "vocabulary", "grammar", "listening", "reading", "n5", "n4", "n3", "n2", "n1")),
    LanguageEntry("es", "Español", "Español", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("en", "Inglés", "English", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("fr", "Francés", "Français", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("de", "Alemán", "Deutsch", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("ko", "Coreano", "한국어", "Hangul", "LTR", listOf("hangul", "vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("zh-Hans", "Chino mandarín", "中文", "Han/Pinyin", "LTR", listOf("pinyin", "hanzi", "vocabulary", "grammar", "listening", "reading")),
    LanguageEntry("it", "Italiano", "Italiano", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
    LanguageEntry("pt", "Portugués", "Português", "Latin", "LTR", listOf("vocabulary", "phrases", "grammar", "listening", "reading")),
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
    var selectedLanguage by remember { mutableStateOf("ja") }
    LaunchedEffect(Unit) { selectedLanguage = sessionStore.readLanguage() ?: "ja" }
    Column(Modifier.fillMaxSize().background(AcademyPaper).padding(horizontal = 22.dp, vertical = 26.dp)) {
        Text("APRENDE A TU MANERA", color = AcademyInk.copy(alpha = 0.55f), style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp))
        Spacer(Modifier.height(14.dp))
        Text("Selecciona un idioma", color = AcademyInk, style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp, lineHeight = 38.sp))
        Spacer(Modifier.height(8.dp))
        Text("Puedes cambiarlo más tarde", color = AcademyInk.copy(alpha = 0.6f), style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(24.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(academyLanguages, key = { it.code }) { entry ->
                val active = entry.code.equals(selectedLanguage, ignoreCase = true)
                Row(
                    Modifier.fillMaxWidth().height(76.dp).background(if (active) AcademyInk else AcademyWhite, RoundedCornerShape(24.dp))
                        .border(if (active) 1.5.dp else 1.dp, if (active) AcademyInk else AcademyInk.copy(alpha = 0.14f), RoundedCornerShape(24.dp))
                        .clickable {
                            scope.launch {
                                selectedLanguage = entry.code
                                sessionStore.saveLanguage(entry.code)
                                navController.navigate("home/${entry.code.lowercase()}") {
                                    popUpTo("language") { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        }.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val monogram = if (entry.code == "zh-Hans") "ZH" else entry.code.take(2).uppercase()
                    Box(Modifier.size(46.dp).background(if (active) AcademyInk else AcademyWhite, CircleShape)
                        .border(1.5.dp, if (active) AcademyWhite else AcademyInk, CircleShape), contentAlignment = Alignment.Center) {
                        Text(monogram, color = if (active) AcademyWhite else AcademyInk, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    }
                    Column(Modifier.weight(1f).padding(start = 16.dp), verticalArrangement = Arrangement.Center) {
                        Text(entry.nameEs, color = if (active) AcademyWhite else AcademyInk, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text(entry.nameNative, color = if (active) AcademyWhite.copy(alpha = 0.62f) else AcademyInk.copy(alpha = 0.5f), style = MaterialTheme.typography.bodySmall)
                    }
                    Text(if (active) "✓" else "→", color = if (active) AcademyWhite else AcademyInk, fontSize = 23.sp, fontWeight = FontWeight.Light)
                }
            }
        }
    }
}
