package com.nihongo.academy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.launch

internal data class N4AssessmentQuestion(
    val section: String, val prompt: String, val options: List<String>, val answer: Int, val explanation: String
)

internal val n4FinalAssessment = listOf(
 N4AssessmentQuestion("Gramática","¿Qué expresa 結婚しています?",listOf("Estoy casándome ahora","Estoy casado; estado resultante","Quiero casarme","Me casé ayer"),1,"〜ています también describe un estado que continúa como resultado de una acción."),
 N4AssessmentQuestion("Gramática","Forma nai de 書く (kaku):",listOf("書かない","書きない","書けない","書くない"),0,"En verbos del grupo 1, la vocal final cambia a la fila a: kaku → kakanai."),
 N4AssessmentQuestion("Gramática","¿Cuál expresa que algo no es necesario?",listOf("勉強しなければなりません","勉強しなくてもいいです","勉強したいです","勉強してはいけません"),1,"〜なくてもいい expresa que no hace falta hacer algo."),
 N4AssessmentQuestion("Gramática","Forma potencial de 話す (hanasu):",listOf("話して","話せる","話さない","話した"),1,"El potencial del grupo 1 cambia la vocal final a e: hanasu → hanaseru."),
 N4AssessmentQuestion("Gramática","¿Cuál expresa habilidad?",listOf("ここで写真を撮ってもいいです","日本語を読むことができます","窓を開けてもいいです","入ってもいいです"),1,"〜ことができます expresa capacidad; 〜てもいい expresa permiso."),
 N4AssessmentQuestion("Gramática","¿Qué frase expresa deseo de realizar una acción?",listOf("水がほしいです","日本へ行きたいです","水があります","日本へ行きました"),1,"〜たい se une a la raíz verbal para expresar deseo de realizar una acción."),
 N4AssessmentQuestion("Gramática","¿Qué estructura significa «más barato que»?",listOf("AはBより安いです","AはBほど安いです","Aの中で安いです","AとBが安いです"),0,"より introduce el término de comparación."),
 N4AssessmentQuestion("Gramática","田中さんは私に本をくれました significa:",listOf("Le di un libro a Tanaka","Tanaka me dio un libro","Tanaka compró un libro","Recibí un libro de un desconocido"),1,"くれる describe algo que otra persona da al hablante o a su grupo."),
 N4AssessmentQuestion("Gramática","Completá: 彼は親切だ ___ 思います。",listOf("を","と","に","で"),1,"Antes de と思います se usa と para marcar el contenido del pensamiento."),
 N4AssessmentQuestion("Gramática","¿Qué expresa かもしれません?",listOf("Certeza","Posibilidad o quizá","Obligación","Prohibición"),1,"かもしれません indica posibilidad, sin afirmar que algo sea seguro."),
 N4AssessmentQuestion("Gramática","¿Qué condicional sirve para «si llueve, me quedaré en casa»?",listOf("雨が降ったら、家にいます","雨が降ると、家にいてください","雨が降って、家にいます","雨が降るたい、家にいます"),0,"〜たら permite expresar una condición para una decisión o acción posterior."),
 N4AssessmentQuestion("Gramática","¿Qué uso de 〜と es más natural?",listOf("春になると、桜が咲きます","春になると、一緒に行きましょう","春になると、窓を開けてください","春になると、映画を見たいです"),0,"〜と se usa con resultados naturales o habituales, no para invitaciones u órdenes."),
 N4AssessmentQuestion("Vocabulario","¿Qué significa 片付ける (katazukeru)?",listOf("Ordenar","Olvidar","Prestar","Romper"),0,"片付ける significa ordenar o guardar las cosas."),
 N4AssessmentQuestion("Vocabulario","¿Qué significa 予定 (yotei)?",listOf("Plan o algo previsto","Estación","Enfermedad","Recibo"),0,"予定 significa plan, programa o algo previsto."),
 N4AssessmentQuestion("Vocabulario","¿Qué significa 途中 (tochuu)?",listOf("Al principio","A mitad del camino o proceso","Al final","La próxima semana"),1,"途中 indica que algo está en curso o a mitad de camino."),
 N4AssessmentQuestion("Vocabulario","¿Qué significa 必要 (hitsuyou)?",listOf("Necesario","Peligroso","Silencioso","Antiguo"),0,"必要 significa necesario o necesidad."),
 N4AssessmentQuestion("Vocabulario","¿Qué significa 間に合う (maniau)?",listOf("Llegar a tiempo","Perderse","Mudarse","Apurarse a comer"),0,"間に合う significa llegar a tiempo o alcanzar el plazo."),
 N4AssessmentQuestion("Vocabulario","¿Qué significa 相談する (soudan suru)?",listOf("Consultar o pedir consejo","Limpiar","Invitar","Cambiar de tren"),0,"相談する es consultar o hablar con alguien sobre un problema."),
 N4AssessmentQuestion("Vocabulario","¿Qué significa 残念 (zannen)?",listOf("Lamentable","Agradable","Cómodo","Seguro"),0,"残念 expresa pena, lástima o decepción."),
 N4AssessmentQuestion("Vocabulario","¿Qué significa 用意する (youi suru)?",listOf("Preparar","Olvidar","Explicar","Elegir"),0,"用意する significa preparar o disponer algo."),
 N4AssessmentQuestion("Lectura","田中さんは先月引っ越してから、電車で会社へ行くようになりました。 ¿Qué cambió?",listOf("Ahora va en tren","Ahora trabaja en casa","Dejó de trabajar","Ahora va en autobús"),0,"Desde la mudanza empezó a ir en tren."),
 N4AssessmentQuestion("Lectura","雨が降ったら、家の中でゲームをするつもりです。 ¿Qué harán si llueve?",listOf("Salir al parque","Jugar dentro de casa","Cancelar la fiesta","Ir al cine"),1,"家の中 significa dentro de casa y つもり expresa intención."),
 N4AssessmentQuestion("Lectura","機械の使い方が分かりませんでした。 ¿Qué no sabía la persona?",listOf("Cómo usar la máquina","Dónde está la estación","Cómo leer el mapa","A qué hora sale el tren"),0,"使い方 significa la manera de usar algo."),
 N4AssessmentQuestion("Lectura","薬を飲んで、早く寝たほうがいいですよ。 ¿Qué consejo recibió?",listOf("Tomar medicina y dormir temprano","Hacer ejercicio","No comer nada","Viajar mañana"),0,"〜たほうがいい es una recomendación."),
 N4AssessmentQuestion("Lectura","燃えるごみは月曜日と木曜日に出してください。 ¿Cuándo se saca esa basura?",listOf("Lunes y jueves","Martes y viernes","Miércoles y sábado","Solo domingo"),0,"月曜日 es lunes y 木曜日 es jueves."),
 N4AssessmentQuestion("Comprensión","En el diálogo de la clínica, ¿desde cuándo tiene dolor de cabeza el paciente?",listOf("Desde anoche","Desde hace un mes","Desde esta mañana","Desde la semana pasada"),0,"El paciente responde 昨日の夜からです: desde anoche."),
 N4AssessmentQuestion("Comprensión","En el diálogo del trabajo, ¿cuántas copias se necesitan?",listOf("Diez","Cinco","Quince","Veinte"),0,"十部 (じゅっぷ) indica diez copias."),
 N4AssessmentQuestion("Comprensión","En el diálogo del objeto perdido, ¿qué perdió la persona?",listOf("Una cartera","Un paraguas","Un teléfono","Un billete"),0,"財布 (さいふ) significa cartera o billetera."),
 N4AssessmentQuestion("Comprensión","Según el pronóstico del diálogo del fin de semana, ¿qué tiempo hará?",listOf("Lloverá","Nevará","Hará mucho calor","Habrá viento fuerte"),0,"雨が降るそうです significa que va a llover."),
 N4AssessmentQuestion("Comprensión","Si A practica cada día, ¿qué podrá hacer poco a poco?",listOf("Leer kanji","Escribir novelas","Hablar sin acento","Enseñar en una escuela"),0,"読めるようになります expresa que llegará a poder leer.")
)

private val n4BestAssessmentKey = intPreferencesKey("n4_best_final_assessment")

@Composable
internal fun N4AssessmentScreen(ds: DataStore<Preferences>, modifier: Modifier = Modifier) {
 val progress by ds.weeklyStudyProgress().collectAsState(initial = WeeklyStudyProgress())
 val saved by ds.data.collectAsState(initial = androidx.datastore.preferences.core.emptyPreferences())
 val bestScore = saved[n4BestAssessmentKey] ?: 0
 val scope = rememberCoroutineScope()
 var index by remember { mutableIntStateOf(0) }
 var selected by remember { mutableIntStateOf(-1) }
 var score by remember { mutableIntStateOf(0) }
 var finished by remember { mutableStateOf(false) }
 var confirmed by remember { mutableStateOf(false) }
 Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
  Text("Evaluación integrada N4", style = MaterialTheme.typography.headlineMedium)
  Text("30 preguntas: gramática, vocabulario, lectura y comprensión de los diálogos. Se aprueba con al menos 70%.", color = MaterialTheme.colorScheme.onSurfaceVariant)
  if (!progress.isPlanComplete) {
   Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Text("Evaluación bloqueada", style = MaterialTheme.typography.titleLarge)
    Text("Primero completá y aprobá las 12 semanas del plan N5. Tu progreso actual es \${progress.completedWeeks.size} de 12 semanas.")
   } }
  } else if (finished) {
   Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Text("Resultado final", style = MaterialTheme.typography.titleLarge)
    Text("$score de \${n4FinalAssessment.size} respuestas correctas · \${score * 100 / n4FinalAssessment.size}%")
    Text(if (score * 100 / n4FinalAssessment.size >= 70) "Evaluación aprobada." else "Todavía no está aprobada. Repasá las explicaciones y volvé a intentarlo.")
    Button(onClick = { index = 0; selected = -1; score = 0; finished = false; confirmed = false }, modifier = Modifier.fillMaxWidth()) { Text("Repetir evaluación") }
   } }
  } else {
   val question = n4FinalAssessment[index]
   LinearProgressIndicator(progress = { (index + 1).toFloat() / n4FinalAssessment.size }, modifier = Modifier.fillMaxWidth())
   Text("Mejor resultado guardado: $bestScore%")
   Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Text("Pregunta \${index + 1} de \${n4FinalAssessment.size} · \${question.section}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
    Text(question.prompt, style = MaterialTheme.typography.titleMedium)
    question.options.forEachIndexed { optionIndex, option ->
     OutlinedButton(onClick = { selected = optionIndex }, enabled = selected < 0, modifier = Modifier.fillMaxWidth()) { Text((if (selected == optionIndex) "● " else "○ ") + option) }
    }
    if (confirmed) {
     Text(if (selected == question.answer) "Correcto" else "Respuesta: \${question.options[question.answer]}", color = MaterialTheme.colorScheme.primary)
     Text(question.explanation, color = MaterialTheme.colorScheme.onSurfaceVariant)
     Button(onClick = {
      if (index == n4FinalAssessment.lastIndex) {
       finished = true
       scope.launch { ds.edit { prefs -> val percent = score * 100 / n4FinalAssessment.size; if (percent > (prefs[n4BestAssessmentKey] ?: 0)) prefs[n4BestAssessmentKey] = percent } }
      } else { index++; selected = -1; confirmed = false }
     }, modifier = Modifier.fillMaxWidth()) { Text(if (index == n4FinalAssessment.lastIndex) "Finalizar evaluación" else "Siguiente pregunta") }
    } else {
     Button(onClick = { if (selected >= 0) { if (selected == question.answer) score++; confirmed = true } }, enabled = selected >= 0, modifier = Modifier.fillMaxWidth()) { Text("Comprobar respuesta") }
    }
   } }
  }
 }
}
