package com.nihongo.academy

import android.content.Intent
import android.provider.Settings
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Locale
import java.util.concurrent.atomic.AtomicReference

internal data class N5DialogueLine(val speaker: String, val japanese: String, val reading: String, val spanish: String)
internal data class N5DialogueQuestion(val prompt: String, val options: List<String>, val answer: Int, val explanation: String)
internal data class N5Dialogue(val id: String, val title: String, val situation: String, val lines: List<N5DialogueLine>, val questions: List<N5DialogueQuestion>)

internal val n5ListeningDialogues = listOf(
    N5Dialogue("cafe", "En la cafetería", "Pedir una bebida y consultar el precio", listOf(
        N5DialogueLine("Cliente", "すみません。コーヒーを一つください。", "Sumimasen. Koohii o hitotsu kudasai.", "Disculpe. Un café, por favor."),
        N5DialogueLine("Empleado", "はい。ホットですか。アイスですか。", "Hai. Hotto desu ka. Aisu desu ka.", "Sí. ¿Caliente o frío?"),
        N5DialogueLine("Cliente", "ホットをお願いします。いくらですか。", "Hotto o onegaishimasu. Ikura desu ka.", "Caliente, por favor. ¿Cuánto cuesta?"),
        N5DialogueLine("Empleado", "三百円です。", "Sanbyaku en desu.", "Son 300 yenes.")
    ), listOf(
        N5DialogueQuestion("¿Qué pide el cliente?", listOf("Un café","Un té","Un agua"), 0, "Dice koohii o hitotsu kudasai: un café, por favor."),
        N5DialogueQuestion("¿Cómo quiere el café?", listOf("Frío","Caliente","Con leche"), 1, "Responde hotto o onegaishimasu."),
        N5DialogueQuestion("¿Cuánto cuesta?", listOf("100 yenes","200 yenes","300 yenes"), 2, "El empleado responde sanbyaku en desu.")
    )),
    N5Dialogue("station", "En la estación", "Preguntar por el tren y el horario", listOf(
        N5DialogueLine("Pasajera", "すみません。東京行きの電車は何時ですか。", "Sumimasen. Toukyou yuki no densha wa nanji desu ka.", "Disculpe. ¿A qué hora sale el tren hacia Tokio?"),
        N5DialogueLine("Empleado", "十時半です。", "Juuji han desu.", "A las diez y media."),
        N5DialogueLine("Pasajera", "何番線ですか。", "Nanbansen desu ka.", "¿De qué andén?"),
        N5DialogueLine("Empleado", "二番線です。", "Nibansen desu.", "Del andén 2.")
    ), listOf(
        N5DialogueQuestion("¿A dónde va el tren?", listOf("A Osaka","A Tokio","A Kioto"), 1, "Toukyou yuki significa con destino a Tokio."),
        N5DialogueQuestion("¿A qué hora sale?", listOf("10:00","10:30","11:30"), 1, "Juuji han significa las diez y media."),
        N5DialogueQuestion("¿De qué andén sale?", listOf("Andén 1","Andén 2","Andén 3"), 1, "Nibansen significa andén número dos.")
    )),
    N5Dialogue("school", "En la escuela", "Hablar de una tarea y pedir ayuda", listOf(
        N5DialogueLine("A", "田中さん、宿題をしましたか。", "Tanaka-san, shukudai o shimashita ka.", "Tanaka, ¿hiciste la tarea?"),
        N5DialogueLine("B", "いいえ、まだです。", "Iie, mada desu.", "No, todavía no."),
        N5DialogueLine("A", "この漢字は難しいですね。", "Kono kanji wa muzukashii desu ne.", "Este kanji es difícil, ¿verdad?"),
        N5DialogueLine("B", "はい。いっしょに勉強しませんか。", "Hai. Issho ni benkyou shimasen ka.", "Sí. ¿Estudiamos juntos?")
    ), listOf(
        N5DialogueQuestion("¿Hizo B la tarea?", listOf("Sí","No, todavía no","No tiene tarea"), 1, "Responde iie, mada desu: no, todavía no."),
        N5DialogueQuestion("¿Cómo describen el kanji?", listOf("Fácil","Nuevo","Difícil"), 2, "Muzukashii significa difícil."),
        N5DialogueQuestion("¿Qué propone B?", listOf("Estudiar juntos","Ir a casa","Comer juntos"), 0, "Issho ni benkyou shimasen ka es una invitación a estudiar juntos.")
    )),
    N5Dialogue("weather", "Planes para el domingo", "Hablar del clima y cambiar planes", listOf(
        N5DialogueLine("A", "日曜日、何をしますか。", "Nichiyoubi, nani o shimasu ka.", "¿Qué vas a hacer el domingo?"),
        N5DialogueLine("B", "公園へ行きたいです。", "Kouen e ikitai desu.", "Quiero ir al parque."),
        N5DialogueLine("A", "でも、日曜日は雨ですよ。", "Demo. Nichiyoubi wa ame desu yo.", "Pero el domingo va a llover."),
        N5DialogueLine("B", "そうですか。じゃ、家で映画を見ます。", "Sou desu ka. Ja, ie de eiga o mimasu.", "¿Ah, sí? Entonces veré una película en casa.")
    ), listOf(
        N5DialogueQuestion("¿A dónde quiere ir B?", listOf("A la escuela","Al parque","A la estación"), 1, "Kouen significa parque."),
        N5DialogueQuestion("¿Qué clima se anuncia?", listOf("Lluvia","Nieve","Sol"), 0, "Ame significa lluvia."),
        N5DialogueQuestion("¿Qué hará finalmente B?", listOf("Estudiar","Dormir","Ver una película en casa"), 2, "Dice ie de eiga o mimasu.")
    )),
    N5Dialogue("shopping", "En una tienda", "Preguntar por una prenda y su tamaño", listOf(
        N5DialogueLine("Cliente", "このシャツはいくらですか。", "Kono shatsu wa ikura desu ka.", "¿Cuánto cuesta esta camisa?"),
        N5DialogueLine("Empleado", "二千円です。", "Nisen en desu.", "Cuesta 2.000 yenes."),
        N5DialogueLine("Cliente", "もう少し大きいサイズがありますか。", "Mou sukoshi ookii saizu ga arimasu ka.", "¿Tiene una talla un poco más grande?"),
        N5DialogueLine("Empleado", "はい、あります。", "Hai, arimasu.", "Sí, tenemos.")
    ), listOf(
        N5DialogueQuestion("¿Qué producto consulta?", listOf("Un pantalón","Una camisa","Un libro"), 1, "Shatsu significa camisa."),
        N5DialogueQuestion("¿Cuál es el precio?", listOf("1.000 yenes","2.000 yenes","3.000 yenes"), 1, "Nisen en significa 2.000 yenes."),
        N5DialogueQuestion("¿Qué necesita el cliente?", listOf("Una talla más grande","Un color diferente","Un descuento"), 0, "Pide mou sukoshi ookii saizu: una talla un poco más grande.")
    )),
    N5Dialogue("home", "Una invitación", "Aceptar una invitación y acordar la hora", listOf(
        N5DialogueLine("A", "土曜日、うちへ来ませんか。", "Doyoubi, uchi e kimasen ka.", "¿Quieres venir a mi casa el sábado?"),
        N5DialogueLine("B", "いいですね。何時に行きますか。", "Ii desu ne. Nanji ni ikimasu ka.", "¡Qué bien! ¿A qué hora voy?"),
        N5DialogueLine("A", "午後三時はどうですか。", "Gogo sanji wa dou desu ka.", "¿Qué tal a las tres de la tarde?"),
        N5DialogueLine("B", "はい。楽しみにしています。", "Hai. Tanoshimi ni shite imasu.", "Sí. Lo espero con ilusión.")
    ), listOf(
        N5DialogueQuestion("¿Qué día es la invitación?", listOf("Viernes","Sábado","Domingo"), 1, "Doyoubi significa sábado."),
        N5DialogueQuestion("¿A qué hora acuerdan encontrarse?", listOf("A las 3 de la tarde","A las 3 de la mañana","A las 5 de la tarde"), 0, "Gogo sanji significa las tres de la tarde."),
        N5DialogueQuestion("¿Acepta B la invitación?", listOf("Sí","No","Todavía no decide"), 0, "B responde hai: sí.")
    ))
)

@Composable
internal fun N5ListeningPractice(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    var voiceReady by remember { mutableStateOf(false) }
    var voiceMessage by remember { mutableStateOf("Comprobando la voz japonesa del dispositivo…") }
    var selectedId by remember { mutableStateOf(n5ListeningDialogues.first().id) }
    var showReading by remember { mutableStateOf(false) }
    var showTranslation by remember { mutableStateOf(false) }
    var answers by remember { mutableStateOf(emptyMap<String, Int>()) }
    val selected = n5ListeningDialogues.first { it.id == selectedId }

    DisposableEffect(context) {
        val engineReference = AtomicReference<TextToSpeech?>()
        val engine = TextToSpeech(context) { status ->
            val readyEngine = engineReference.get()
            if (status == TextToSpeech.SUCCESS && readyEngine != null) {
                val languageResult = readyEngine.setLanguage(Locale.JAPANESE)
                voiceReady = languageResult >= TextToSpeech.LANG_AVAILABLE
                voiceMessage = if (voiceReady) "Voz japonesa disponible. La reproducción se realiza en el dispositivo." else "El dispositivo no tiene una voz japonesa instalada. Podés leer el diálogo; instalá una voz japonesa desde los ajustes de texto a voz para escucharla."
                if (voiceReady) readyEngine.setSpeechRate(0.82f)
            } else {
                voiceReady = false
                voiceMessage = "No se pudo iniciar el motor de voz del dispositivo."
            }
        }
        engineReference.set(engine)
        tts = engine
        onDispose {
            engine.stop()
            engine.shutdown()
            tts = null
        }
    }

    fun speak(text: String) {
        val engine = tts
        if (voiceReady && engine != null) engine.speak(text, TextToSpeech.QUEUE_FLUSH, null, "n5-listening")
    }

    Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Comprensión auditiva N5", style = MaterialTheme.typography.headlineMedium)
        Text("Diálogos originales de Nihongo Academy. Escuchá primero sin mirar la traducción y después comprobá cuánto entendiste.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Audio del dispositivo", style = MaterialTheme.typography.titleMedium)
                Text(voiceMessage, color = MaterialTheme.colorScheme.onSurfaceVariant)
                if (!voiceReady) {
                    Text("La app no descarga audios ni necesita conexión: utiliza el motor TTS local si hay una voz japonesa instalada.", style = MaterialTheme.typography.bodySmall)
                    OutlinedButton(onClick = {
                        runCatching { context.startActivity(Intent(Settings.ACTION_SETTINGS)) }
                            .onFailure { voiceMessage = "No se pudieron abrir los ajustes de texto a voz de este dispositivo." }
                    }, modifier = Modifier.fillMaxWidth()) { Text("Abrir ajustes del teléfono") }
                }
            }
        }
        Text("Elegí una situación", style = MaterialTheme.typography.titleLarge)
        n5ListeningDialogues.forEach { dialogue ->
            OutlinedButton(onClick = { selectedId = dialogue.id; answers = emptyMap(); showReading = false; showTranslation = false }, modifier = Modifier.fillMaxWidth()) {
                Text((if (selectedId == dialogue.id) "● " else "○ ") + dialogue.title)
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(selected.title, style = MaterialTheme.typography.titleLarge)
                Text(selected.situation, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Button(onClick = { speak(selected.lines.joinToString(" ") { it.japanese }) }, enabled = voiceReady, modifier = Modifier.fillMaxWidth()) { Text("▶ Escuchar diálogo completo") }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { showReading = !showReading }, modifier = Modifier.weight(1f)) { Text(if (showReading) "Ocultar lectura" else "Ver romaji") }
                    OutlinedButton(onClick = { showTranslation = !showTranslation }, modifier = Modifier.weight(1f)) { Text(if (showTranslation) "Ocultar traducción" else "Ver traducción") }
                }
                selected.lines.forEach { line ->
                    HorizontalDivider()
                    Text(line.speaker, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                    Text(line.japanese, style = MaterialTheme.typography.titleMedium)
                    if (showReading) Text(line.reading, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    if (showTranslation) Text(line.spanish)
                    OutlinedButton(onClick = { speak(line.japanese) }, enabled = voiceReady, modifier = Modifier.fillMaxWidth()) { Text("▶ Escuchar esta frase") }
                }
            }
        }
        Text("Comprobá tu comprensión", style = MaterialTheme.typography.titleLarge)
        selected.questions.forEachIndexed { index, question ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("${index + 1}. ${question.prompt}", style = MaterialTheme.typography.titleMedium)
                    question.options.forEachIndexed { optionIndex, option ->
                        OutlinedButton(onClick = { answers = answers + (selected.id + "-" + index to optionIndex) }, modifier = Modifier.fillMaxWidth()) {
                            Text((if (answers[selected.id + "-" + index] == optionIndex) "● " else "○ ") + option)
                        }
                    }
                    val answer = answers[selected.id + "-" + index]
                    if (answer != null) {
                        Text(if (answer == question.answer) "Correcto" else "Respuesta: ${question.options[question.answer]}", color = MaterialTheme.colorScheme.primary)
                        Text(question.explanation, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
        val score = selected.questions.indices.count { answers[selected.id + "-" + it] == selected.questions[it].answer }
        Text("Resultado: $score de ${selected.questions.size} respuestas correctas.")
        OutlinedButton(onClick = { answers = emptyMap() }, modifier = Modifier.fillMaxWidth()) { Text("Repetir preguntas") }
    }
}



/** Banco de escucha: preguntas originales ligadas a los seis diálogos. */
internal val n5ListeningAssessment: List<KanaQuizQuestion> =
    n5ListeningDialogues.flatMap { dialogue ->
        dialogue.questions.map { question ->
            KanaQuizQuestion(question.prompt, question.options, question.answer)
        }
    }

/** Tres lecturas breves originales con preguntas de comprensión N5. */
internal val n5ReadingAssessment = listOf(
    KanaQuizQuestion(
        "Leé: 田中さんは毎朝七時に起きます。八時に学校へ行きます。 ¿A qué hora se levanta Tanaka?",
        listOf("A las seis", "A las siete", "A las ocho", "A las nueve"), 1
    ),
    KanaQuizQuestion(
        "Leé: 日曜日、山田さんは友だちと公園へ行きました。公園でサッカーをしました。 ¿Qué hicieron en el parque?",
        listOf("Jugaron al fútbol", "Leyeron un libro", "Comieron", "Estudiaron"), 0
    ),
    KanaQuizQuestion(
        "Leé: 今日は雨です。鈴木さんは家で本を読みます。 ¿Dónde lee Suzuki?",
        listOf("En la escuela", "En el parque", "En casa", "En la estación"), 2
    )
)

/** Simulacro breve de 30 ítems: vocabulario/gramática, lectura y escucha. */
internal val n5FinalMockAssessment: List<KanaQuizQuestion> =
    lessons.filter { it.level == "N5" }
        .flatMap { lesson -> lesson.exercises.map { exercise ->
            KanaQuizQuestion(exercise.prompt, exercise.options, exercise.answer)
        } }
        .take(15) + n5ReadingAssessment + n5ListeningAssessment.take(12)
