package com.nihongo.academy

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.util.Locale
import java.util.concurrent.atomic.AtomicReference

internal data class N4DialogueLine(val speaker: String, val japanese: String, val reading: String, val spanish: String)
internal data class N4DialogueQuestion(val prompt: String, val options: List<String>, val answer: Int, val explanation: String)
internal data class N4Dialogue(val id: String, val title: String, val situation: String, val lines: List<N4DialogueLine>, val questions: List<N4DialogueQuestion>)

internal val n4ListeningDialogues = listOf(
    N4Dialogue("clinic", "En la clínica", "Explicar síntomas y recibir indicaciones", listOf(
        N4DialogueLine("Paciente", "昨日から頭が痛くて、少し熱もあります。", "Kinou kara atama ga itakute, sukoshi netsu mo arimasu.", "Desde ayer me duele la cabeza y también tengo un poco de fiebre."),
        N4DialogueLine("Médica", "いつからですか。", "Itsu kara desu ka.", "¿Desde cuándo?"),
        N4DialogueLine("Paciente", "昨日の夜からです。", "Kinou no yoru kara desu.", "Desde anoche."),
        N4DialogueLine("Médica", "今日は薬を飲んで、早く寝たほうがいいですよ。", "Kyou wa kusuri o nonde, hayaku neta hou ga ii desu yo.", "Hoy debería tomar el medicamento y acostarse temprano.")
    ), listOf(
        N4DialogueQuestion("¿Qué síntomas tiene?", listOf("Dolor de cabeza y algo de fiebre", "Dolor de estómago", "Tos y dolor de garganta"), 0, "Atama ga itai es dolor de cabeza y netsu es fiebre."),
        N4DialogueQuestion("¿Desde cuándo?", listOf("Desde esta mañana", "Desde anoche", "Desde hace una semana"), 1, "Responde kinou no yoru kara: desde anoche."),
        N4DialogueQuestion("¿Qué recomienda la médica?", listOf("Hacer ejercicio", "Tomar medicina y dormir temprano", "No beber agua"), 1, "Nonde y neta hou ga ii: tomar y dormir es lo recomendable.")
    )),
    N4Dialogue("work", "Un cambio en el trabajo", "Explicar una tarea y pedir ayuda", listOf(
        N4DialogueLine("A", "この書類、今日中にコピーしておいてください。", "Kono shorui, kyoujuu ni kopii shite oite kudasai.", "Por favor, deje copiado este documento antes de que termine el día."),
        N4DialogueLine("B", "分かりました。何部必要ですか。", "Wakarimashita. Nanbu hitsuyou desu ka.", "Entendido. ¿Cuántas copias necesita?"),
        N4DialogueLine("A", "十部お願いします。", "Juppu onegaishimasu.", "Diez copias, por favor."),
        N4DialogueLine("B", "コピーしたら、机の上に置いておきます。", "Kopii shitara, tsukue no ue ni oite okimasu.", "Cuando las copie, las dejaré sobre el escritorio.")
    ), listOf(
        N4DialogueQuestion("¿Qué debe hacer B?", listOf("Traducir una carta", "Copiar un documento", "Enviar un correo"), 1, "Kopii shite oite kudasai pide dejar hechas las copias."),
        N4DialogueQuestion("¿Cuántas copias?", listOf("Cinco", "Diez", "Veinte"), 1, "Juppu significa diez copias."),
        N4DialogueQuestion("¿Dónde las dejará?", listOf("En el escritorio", "En la entrada", "En una caja"), 0, "Tsukue no ue significa encima del escritorio.")
    )),
    N4Dialogue("lost", "Un objeto perdido", "Preguntar si alguien encontró una cartera", listOf(
        N4DialogueLine("A", "駅で財布を落としてしまったんです。", "Eki de saifu o otoshite shimattan desu.", "Se me cayó la cartera en la estación."),
        N4DialogueLine("B", "何色の財布ですか。", "Nani iro no saifu desu ka.", "¿De qué color es?"),
        N4DialogueLine("A", "黒くて、小さい財布です。", "Kurokute, chiisai saifu desu.", "Es negra y pequeña."),
        N4DialogueLine("B", "駅員さんに聞いてみたらどうですか。", "Ekiin-san ni kiite mitara dou desu ka.", "¿Por qué no prueba preguntándole al personal de la estación?")
    ), listOf(
        N4DialogueQuestion("¿Qué perdió?", listOf("Un teléfono", "Una cartera", "Un paraguas"), 1, "Saifu significa cartera o billetera."),
        N4DialogueQuestion("¿Cómo es?", listOf("Roja y grande", "Negra y pequeña", "Azul y nueva"), 1, "Kurokute, chiisai: negra y pequeña."),
        N4DialogueQuestion("¿Qué le aconsejan?", listOf("Preguntar al personal", "Volver a casa", "Comprar otra"), 0, "Ekiin-san ni kiite mitara dou: probar preguntando al personal.")
    )),
    N4Dialogue("weekend", "Planes y posibilidades", "Cambiar una salida según el clima", listOf(
        N4DialogueLine("A", "明日、海へ行くつもりです。", "Ashita, umi e iku tsumori desu.", "Mañana tengo pensado ir al mar."),
        N4DialogueLine("B", "天気予報によると、雨が降るそうですよ。", "Tenki yohou ni yoru to, ame ga furu sou desu yo.", "Según el pronóstico, parece que va a llover."),
        N4DialogueLine("A", "それなら、家で映画を見ようと思います。", "Sore nara, ie de eiga o miyou to omoimasu.", "En ese caso, creo que veré una película en casa."),
        N4DialogueLine("B", "いいですね。私も行ってもいいですか。", "Ii desu ne. Watashi mo itte mo ii desu ka.", "¡Qué bien! ¿Puedo ir yo también?")
    ), listOf(
        N4DialogueQuestion("¿Qué pensaba hacer A?", listOf("Ir al mar", "Visitar un museo", "Estudiar"), 0, "Umi e iku tsumori: tenía pensado ir al mar."),
        N4DialogueQuestion("¿Qué anuncia el pronóstico?", listOf("Viento", "Lluvia", "Nieve"), 1, "Ame ga furu: va a llover."),
        N4DialogueQuestion("¿Qué decide hacer A?", listOf("Ver una película en casa", "Ir a trabajar", "Salir a correr"), 0, "Ie de eiga o miyou to omoimasu: piensa ver una película en casa.")
    )),
    N4Dialogue("school", "Una experiencia de estudio", "Contar algo que ocurrió mientras se estudiaba", listOf(
        N4DialogueLine("A", "日本語を勉強し始めてから、半年になります。", "Nihongo o benkyou shi hajimete kara, hantoshi ni narimasu.", "Hace medio año que empecé a estudiar japonés."),
        N4DialogueLine("B", "漢字を覚えるのは大変でしょう。", "Kanji o oboeru no wa taihen deshou.", "Memorizar kanji debe ser difícil, ¿verdad?"),
        N4DialogueLine("A", "はい。でも、毎日練習すれば、少しずつ読めるようになります。", "Hai. Demo, mainichi renshuu sureba, sukoshi zutsu yomeru you ni narimasu.", "Sí. Pero si practico todos los días, poco a poco podré leerlos."),
        N4DialogueLine("B", "私も一緒に勉強してみたいです。", "Watashi mo issho ni benkyou shite mitai desu.", "Yo también quisiera probar estudiar contigo.")
    ), listOf(
        N4DialogueQuestion("¿Cuánto tiempo lleva estudiando A?", listOf("Un mes", "Medio año", "Dos años"), 1, "Hantoshi significa medio año."),
        N4DialogueQuestion("¿Qué le resulta difícil?", listOf("Hablar rápido", "Memorizar kanji", "Escribir correos"), 1, "Oboeru no wa taihen: memorizar es difícil."),
        N4DialogueQuestion("¿Qué ocurre si practica cada día?", listOf("Leerá poco a poco", "Olvidará los kanji", "Dejará de estudiar"), 0, "Yomeru you ni narimasu: llegará a poder leer.")
    )),
    N4Dialogue("apartment", "Reglas del apartamento", "Hablar de una mudanza y las normas del edificio", listOf(
        N4DialogueLine("A", "来月、このアパートに引っ越すことになりました。", "Raigetsu, kono apaato ni hikkosu koto ni narimashita.", "El mes que viene se decidió que me mudaré a este apartamento."),
        N4DialogueLine("B", "そうですか。ごみは何曜日に出せばいいですか。", "Sou desu ka. Gomi wa nan youbi ni daseba ii desu ka.", "Ya veo. ¿Qué día debo sacar la basura?"),
        N4DialogueLine("A", "燃えるごみは月曜日と木曜日に出してください。", "Moeru gomi wa getsuyoubi to mokuyoubi ni dashite kudasai.", "Saque la basura combustible los lunes y jueves."),
        N4DialogueLine("B", "分かりました。教えてくれてありがとうございます。", "Wakarimashita. Oshiete kurete arigatou gozaimasu.", "Entendido. Gracias por explicármelo.")
    ), listOf(
        N4DialogueQuestion("¿Cuándo se muda?", listOf("El mes que viene", "Mañana", "El año que viene"), 0, "Raigetsu significa el mes que viene."),
        N4DialogueQuestion("¿Qué pregunta B?", listOf("Dónde aparcar", "Qué días sacar la basura", "Cómo pagar el alquiler"), 1, "Gomi wa nan youbi: qué día se saca la basura."),
        N4DialogueQuestion("¿Qué días se saca la basura combustible?", listOf("Martes y viernes", "Lunes y jueves", "Sábado y domingo"), 1, "Getsuyoubi to mokuyoubi: lunes y jueves.")
    ))
)

internal data class N4ReadingPassage(val title: String, val japanese: String, val reading: String, val spanish: String, val questions: List<N4DialogueQuestion>)

internal val n4ReadingPassages = listOf(
    N4ReadingPassage("Un cambio de rutina",
        "田中さんは去年まで毎朝バスで会社へ通っていました。でも、先月引っ越してから、駅まで歩いて電車で行くようになりました。電車のほうが早いし、雨の日も便利だそうです。",
        "Tanaka-san wa kyonen made maiasa basu de kaisha e kayotte imashita. Demo, sengetsu hikkoshite kara, eki made aruite densha de iku you ni narimashita. Densha no hou ga hayai shi, ame no hi mo benri da sou desu.",
        "Hasta el año pasado, Tanaka iba cada mañana al trabajo en autobús. Pero, desde que se mudó el mes pasado, empezó a caminar hasta la estación y viajar en tren. Dice que el tren es más rápido y también cómodo los días de lluvia.",
        listOf(
            N4DialogueQuestion("¿Cómo iba antes al trabajo?", listOf("En autobús", "En tren", "En bicicleta"), 0, "El texto dice basu de kaisha e kayotte imashita."),
            N4DialogueQuestion("¿Qué cambió después de mudarse?", listOf("Va caminando a la estación y luego en tren", "Trabaja desde casa", "Va en taxi"), 0, "Eki made aruite densha de iku you ni narimashita."),
            N4DialogueQuestion("¿Por qué prefiere el tren?", listOf("Es más barato", "Es más rápido y cómodo con lluvia", "Está más cerca"), 1, "Hayai y benri: rápido y práctico.")
        )),
    N4ReadingPassage("Preparar una fiesta",
        "友だちの誕生日のために、土曜日にパーティーをすることにしました。私はケーキを作っておきます。ミカさんは飲み物を買ってくれるそうです。雨が降ったら、家の中でゲームをするつもりです。",
        "Tomodachi no tanjoubi no tame ni, doyoubi ni paatii o suru koto ni shimashita. Watashi wa keeki o tsukutte okimasu. Mika-san wa nomimono o katte kureru sou desu. Ame ga futtara, ie no naka de geemu o suru tsumori desu.",
        "Decidimos hacer una fiesta el sábado por el cumpleaños de un amigo. Yo dejaré preparado el pastel. Al parecer, Mika comprará las bebidas para nosotros. Si llueve, tenemos pensado jugar dentro de casa.",
        listOf(
            N4DialogueQuestion("¿Por qué hacen la fiesta?", listOf("Por un cumpleaños", "Por una graduación", "Por Año Nuevo"), 0, "Tomodachi no tanjoubi: el cumpleaños de un amigo."),
            N4DialogueQuestion("¿Qué preparará quien narra?", listOf("Las bebidas", "El pastel", "La música"), 1, "Keeki o tsukutte okimasu: dejará preparado el pastel."),
            N4DialogueQuestion("¿Qué harán si llueve?", listOf("Cancelar", "Jugar dentro de casa", "Ir al parque"), 1, "Ame ga futtara: si llueve, jugarán dentro.")
        )),
    N4ReadingPassage("Un viaje en tren",
        "京都へ行ったとき、駅で切符を買おうとしましたが、機械の使い方が分かりませんでした。近くにいた人が教えてくれたので、簡単に買うことができました。次は一人でも買えると思います。",
        "Kyouto e itta toki, eki de kippu o kaou to shimashita ga, kikai no tsukaikata ga wakarimasen deshita. Chikaku ni ita hito ga oshiete kureta node, kantan ni kau koto ga dekimashita. Tsugi wa hitori demo kaeru to omoimasu.",
        "Cuando fui a Kioto, intenté comprar un billete en la estación, pero no sabía cómo usar la máquina. Una persona que estaba cerca me enseñó, así que pude comprarlo fácilmente. Creo que la próxima vez podré hacerlo solo.",
        listOf(
            N4DialogueQuestion("¿Qué intentó comprar?", listOf("Un billete", "Una bebida", "Un mapa"), 0, "Kippu significa billete."),
            N4DialogueQuestion("¿Por qué pudo comprarlo?", listOf("La máquina era nueva", "Alguien le explicó cómo usarla", "Ya tenía una tarjeta"), 1, "Oshiete kureta: alguien se lo explicó."),
            N4DialogueQuestion("¿Qué piensa para la próxima vez?", listOf("No viajar", "Poder comprarlo solo", "Tomar un taxi"), 1, "Hitori demo kaeru to omoimasu: cree que podrá comprarlo solo.")
        ))
)

@Composable
internal fun ListeningHub(ds: DataStore<Preferences>, modifier: Modifier = Modifier) {
    val progress by ds.weeklyStudyProgress().collectAsState(initial = WeeklyStudyProgress())
    var level by remember { mutableStateOf("N5") }
    Column(modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = level == "N5", onClick = { level = "N5" }, label = { Text("Escucha N5") })
            FilterChip(selected = level == "N4", onClick = { level = "N4" }, enabled = progress.isPlanComplete, label = { Text(if (progress.isPlanComplete) "Escucha N4" else "N4 bloqueado") })
        }
        if (level == "N4" && !progress.isPlanComplete) {
            Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Comprensión auditiva N4", style = MaterialTheme.typography.headlineSmall)
                Text("Primero completá las 12 semanas del plan N5. Así mantenemos el orden de aprendizaje y desbloqueo.")
            }
        } else if (level == "N4") N4ListeningPractice(Modifier.weight(1f))
        else N5ListeningPractice(Modifier.weight(1f))
    }
}

@Composable
internal fun N4ListeningPractice(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    var voiceReady by remember { mutableStateOf(false) }
    var voiceMessage by remember { mutableStateOf("Comprobando la voz japonesa del dispositivo…") }
    var selectedId by remember { mutableStateOf(n4ListeningDialogues.first().id) }
    var showReading by remember { mutableStateOf(false) }
    var showTranslation by remember { mutableStateOf(false) }
    var answers by remember { mutableStateOf(emptyMap<String, Int>()) }
    var readingIndex by remember { mutableIntStateOf(0) }
    var showReadingText by remember { mutableStateOf(false) }
    val selected = n4ListeningDialogues.first { it.id == selectedId }
    val passage = n4ReadingPassages[readingIndex]

    DisposableEffect(context) {
        val engineReference = AtomicReference<TextToSpeech?>()
        val engine = TextToSpeech(context) { status ->
            val readyEngine = engineReference.get()
            if (status == TextToSpeech.SUCCESS && readyEngine != null) {
                val result = readyEngine.setLanguage(Locale.JAPANESE)
                voiceReady = result >= TextToSpeech.LANG_AVAILABLE
                voiceMessage = if (voiceReady) "Voz japonesa disponible. El audio se genera localmente en el dispositivo." else "No hay una voz japonesa instalada. Podés leer el material e instalar una voz japonesa desde los ajustes de texto a voz."
                if (voiceReady) readyEngine.setSpeechRate(0.78f)
            } else {
                voiceReady = false
                voiceMessage = "No se pudo iniciar el motor de voz del dispositivo."
            }
        }
        engineReference.set(engine)
        tts = engine
        onDispose { engine.stop(); engine.shutdown(); tts = null }
    }

    fun speak(text: String) {
        val engine = tts
        if (voiceReady && engine != null) engine.speak(text, TextToSpeech.QUEUE_FLUSH, null, "n4-audio")
    }

    Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Comprensión auditiva N4", style = MaterialTheme.typography.headlineMedium)
        Text("Seis diálogos originales de situaciones cotidianas. Escuchá primero, respondé y después revisá el texto y la traducción.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Audio sin descargas", style = MaterialTheme.typography.titleMedium)
                Text(voiceMessage, color = MaterialTheme.colorScheme.onSurfaceVariant)
                if (!voiceReady) {
                    Text("La app utiliza la voz japonesa instalada en Android/Huawei. No descarga archivos de audio ni necesita conexión.", style = MaterialTheme.typography.bodySmall)
                    OutlinedButton(onClick = { runCatching { context.startActivity(Intent(Settings.ACTION_SETTINGS)) }.onFailure { voiceMessage = "No se pudieron abrir los ajustes del teléfono." } }, modifier = Modifier.fillMaxWidth()) { Text("Abrir ajustes del teléfono") }
                }
            }
        }
        Text("Elegí una situación", style = MaterialTheme.typography.titleLarge)
        n4ListeningDialogues.forEach { dialogue ->
            OutlinedButton(onClick = { selectedId = dialogue.id; answers = emptyMap(); showReading = false; showTranslation = false }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.outlinedButtonColors(containerColor = if (selectedId == dialogue.id) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.onSurface)) {
                Text((if (selectedId == dialogue.id) "● " else "○ ") + dialogue.title)
            }
        }
        Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(selected.title, style = MaterialTheme.typography.titleLarge)
                Text(selected.situation, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Button(onClick = { speak(selected.lines.joinToString(" ") { it.japanese }) }, enabled = voiceReady, modifier = Modifier.fillMaxWidth()) { Text("▶ Escuchar diálogo completo") }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { showReading = !showReading }, modifier = Modifier.weight(1f)) { Text(if (showReading) "Ocultar romaji" else "Ver romaji") }
                    OutlinedButton(onClick = { showTranslation = !showTranslation }, modifier = Modifier.weight(1f)) { Text(if (showTranslation) "Ocultar traducción" else "Ver traducción") }
                }
                selected.lines.forEach { line ->
                    HorizontalDivider()
                    Text(line.speaker, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                    Text(line.japanese, style = MaterialTheme.typography.titleMedium)
                    if (showReading) Text(line.reading, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    if (showTranslation) Text(line.spanish)
                    OutlinedButton(onClick = { speak(line.japanese) }, enabled = voiceReady, modifier = Modifier.fillMaxWidth()) { Text("▶ Escuchar frase") }
                }
            }
        }
        Text("Comprensión lectora N4", style = MaterialTheme.typography.titleLarge)
        n4ReadingPassages.forEachIndexed { index, item ->
            OutlinedButton(onClick = { readingIndex = index; answers = answers.filterKeys { !it.startsWith("reading-") }; showReadingText = false }, modifier = Modifier.fillMaxWidth()) {
                Text((if (readingIndex == index) "● " else "○ ") + item.title)
            }
        }
        Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(passage.title, style = MaterialTheme.typography.titleMedium)
                OutlinedButton(onClick = { showReadingText = !showReadingText }, modifier = Modifier.fillMaxWidth()) { Text(if (showReadingText) "Ocultar lectura" else "Mostrar lectura japonesa") }
                if (showReadingText) {
                    Text(passage.japanese, style = MaterialTheme.typography.titleMedium)
                    Text(passage.reading, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(passage.spanish)
                }
                passage.questions.forEachIndexed { index, question ->
                    Text(question.prompt, style = MaterialTheme.typography.titleSmall)
                    question.options.forEachIndexed { optionIndex, option ->
                        OutlinedButton(onClick = { answers = answers + ("reading-$readingIndex-$index" to optionIndex) }, modifier = Modifier.fillMaxWidth()) {
                            Text((if (answers["reading-$readingIndex-$index"] == optionIndex) "● " else "○ ") + option)
                        }
                    }
                    val answer = answers["reading-$readingIndex-$index"]
                    if (answer != null) {
                        Text(if (answer == question.answer) "Correcto" else "Respuesta: ${question.options[question.answer]}", color = MaterialTheme.colorScheme.primary)
                        Text(question.explanation, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
        Text("Comprensión auditiva: preguntas")
        selected.questions.forEachIndexed { index, question ->
            Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("${index + 1}. ${question.prompt}", style = MaterialTheme.typography.titleMedium)
                    question.options.forEachIndexed { optionIndex, option ->
                        OutlinedButton(onClick = { answers = answers + ("dialogue-$selectedId-$index" to optionIndex) }, modifier = Modifier.fillMaxWidth()) {
                            Text((if (answers["dialogue-$selectedId-$index"] == optionIndex) "● " else "○ ") + option)
                        }
                    }
                    val answer = answers["dialogue-$selectedId-$index"]
                    if (answer != null) {
                        Text(if (answer == question.answer) "Correcto" else "Respuesta: ${question.options[question.answer]}", color = MaterialTheme.colorScheme.primary)
                        Text(question.explanation, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
        val listeningScore = selected.questions.indices.count { answers["dialogue-$selectedId-$it"] == selected.questions[it].answer }
        val readingScore = passage.questions.indices.count { answers["reading-$readingIndex-$it"] == passage.questions[it].answer }
        Text("Resultado de este diálogo: $listeningScore de ${selected.questions.size}. Lectura: $readingScore de ${passage.questions.size}.")
    }
}
