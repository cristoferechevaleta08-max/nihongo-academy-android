package com.nihongoacademy.ui

import android.speech.tts.TextToSpeech
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@Composable
fun PronunciationButton(text: String, languageCode: String) {
    val context = LocalContext.current
    var engine by remember { mutableStateOf<TextToSpeech?>(null) }
    var voiceReady by remember { mutableStateOf(false) }

    DisposableEffect(languageCode) {
        var createdEngine: TextToSpeech? = null
        createdEngine = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = createdEngine?.setLanguage(localeFor(languageCode))
                    ?: TextToSpeech.LANG_NOT_SUPPORTED
                voiceReady = result != TextToSpeech.LANG_MISSING_DATA &&
                    result != TextToSpeech.LANG_NOT_SUPPORTED
            }
        }
        engine = createdEngine

        onDispose {
            engine?.stop()
            engine?.shutdown()
            engine = null
            voiceReady = false
        }
    }

    Button(
        onClick = {
            engine?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "nihongo-pronunciation")
        },
        enabled = voiceReady && text.isNotBlank()
    ) {
        Text(if (voiceReady) "Escuchar pronunciación" else "Voz no disponible")
    }
}

private fun localeFor(languageCode: String): Locale = when (languageCode.lowercase(Locale.ROOT)) {
    "ja" -> Locale.JAPAN
    "en" -> Locale.US
    "ko" -> Locale.KOREA
    "zh", "zh-hans" -> Locale.SIMPLIFIED_CHINESE
    "fr" -> Locale.FRANCE
    "de" -> Locale.GERMANY
    "it" -> Locale.ITALY
    "pt" -> Locale("pt", "BR")
    "es" -> Locale("es", "ES")
    "ru" -> Locale("ru", "RU")
    "ar" -> Locale("ar", "SA")
    "nl" -> Locale("nl", "NL")
    "tr" -> Locale("tr", "TR")
    "hi" -> Locale("hi", "IN")
    "th" -> Locale("th", "TH")
    else -> Locale.getDefault()
}
