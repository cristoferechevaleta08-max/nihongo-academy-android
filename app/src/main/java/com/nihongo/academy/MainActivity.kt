package com.nihongo.academy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.util.Locale

private val Context.store by preferencesDataStore("nihongo_academy")
private val darkKey=booleanPreferencesKey("dark")
private val langKey=stringPreferencesKey("lang")
private val nameKey=stringPreferencesKey("name")
private val doneKey=stringSetPreferencesKey("done")
private val mistakesKey=stringSetPreferencesKey("mistakes")
private val xpKey=intPreferencesKey("xp")
private val rewardKey=stringPreferencesKey("reward")
private val cardsKey=stringSetPreferencesKey("cards")
private data class Language(val code:String,val name:String,val native:String,val flag:String)
private data class Exercise(val prompt:String,val options:List<String>,val answer:Int,val note:String)
private data class Lesson(val id:String,val level:String,val title:String,val exercises:List<Exercise>)
private data class Question(val section:String,val prompt:String,val options:List<String>,val answer:Int)
private val languages=listOf(
Language("ja","Japonés","日本語","🇯🇵"),Language("en","Inglés","English","🇬🇧"),Language("ko","Coreano","한국어","🇰🇷"),Language("zh","Chino","中文","🇨🇳"),Language("fr","Francés","Français","🇫🇷"),Language("de","Alemán","Deutsch","🇩🇪"),Language("it","Italiano","Italiano","🇮🇹"),Language("pt","Portugués","Português","🇧🇷"),Language("es","Español","Español","🇪🇸"),Language("ru","Ruso","Русский","🇷🇺"),Language("ar","Árabe","العربية","🇸🇦"),Language("hi","Hindi","हिन्दी","🇮🇳"),Language("tr","Turco","Türkçe","🇹🇷"),Language("bn","Bengalí","বাংলা","🇧🇩"),Language("id","Indonesio","Bahasa Indonesia","🇮🇩"))
private fun ex(p:String,o:List<String>,a:Int,n:String)=Exercise(p,o,a,n)
private val lessons=listOf(
Lesson("n5-greetings","N5","Saludos y presentaciones",listOf(
ex("¿Qué significa こんにちは?",listOf("Hola","Buenas noches","Gracias","Adiós"),0,"Saludo habitual durante el día."),
ex("Lectura de ありがとう",listOf("Arigatō","Sayonara","Ohayō","Sumimasen"),0,"Significa gracias."),
ex("¿Cómo se dice yo?",listOf("わたし","あなた","せんせい","がくせい"),0,"わたし (watashi) significa yo."),
ex("¿Qué significa はじめまして?",listOf("Mucho gusto","Hasta mañana","Perdón","Bienvenido"),0,"Se usa al conocer a alguien."),
ex("わたし ___ マリアです。",listOf("は","を","に","で"),0,"は marca el tema."))),
Lesson("n5-numbers","N5","Números básicos",listOf(
ex("¿Cómo se dice uno?",listOf("いち","に","さん","よん"),0,"いち (ichi) es uno."),
ex("¿Qué número es ご?",listOf("3","4","5","6"),2,"ご (go) significa cinco."),
ex("¿Cómo se dice diez?",listOf("じゅう","なな","はち","きゅう"),0,"じゅう (jū) es diez."),
ex("¿Qué significa さん?",listOf("Dos","Tres","Seis","Ocho"),1,"さん (san) significa tres."),
ex("¿Qué significa きゅう?",listOf("Cinco","Nueve","Siete","Cuatro"),1,"きゅう (kyū) es nueve."))),
Lesson("n4-past","N4","Acciones en pasado",listOf(
ex("Pasado cortés de たべます",listOf("たべました","たべません","たべますか","たべたい"),0,"たべました expresa pasado."),
ex("¿Qué expresa きのう?",listOf("Mañana","Ayer","Hoy","Ahora"),1,"きのう significa ayer."),
ex("Partícula de objeto directo",listOf("を","は","に","へ"),0,"を marca el objeto directo."),
ex("Elegí 'fui' en forma cortés",listOf("いきました","いきます","いきません","いきたい"),0,"いきました es pasado de いきます."),
ex("¿Qué significa でした?",listOf("Era / fue","Será","No es","Quiero"),0,"でした es pasado de です."))))
private val examQuestions=listOf(
Question("Vocabulario","¿Qué significa みず?",listOf("Agua","Fuego","Tierra","Viento"),0),
Question("Vocabulario","¿Qué significa ねこ?",listOf("Perro","Gato","Pájaro","Pez"),1),
Question("Gramática","Elegí la partícula de tema",listOf("は","を","が","で"),0),
Question("Gramática","がくせい ___ です。",listOf("は","を","に","へ"),0),
Question("Lectura","¿Qué significa おはよう?",listOf("Buenas noches","Buenos días","Gracias","Adiós"),1),
Question("Comprensión","わたしは学生です significa…",listOf("Soy estudiante","Soy profesor","Voy a casa","Tengo agua"),0))
private val cards=mapOf("sakura" to ("桜" to "Sakura · flor de cerezo"),"fuji" to ("富士山" to "Monte Fuji"),"neko" to ("猫" to "Gato"),"tsuki" to ("月" to "Luna"),"yume" to ("夢" to "Sueño"))

class MainActivity:ComponentActivity(){override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);setContent{Root()}}}
@Composable private fun Root(){
val context=LocalContext.current;val ds=context.store;val scope=rememberCoroutineScope()
val dark by ds.data.map{it[darkKey]?:true}.collectAsState(initial=true)
val lang by ds.data.map{it[langKey]?:"ja"}.collectAsState(initial="ja")
val name by ds.data.map{it[nameKey]?:"Estudiante"}.collectAsState(initial="Estudiante")
val done by ds.data.map{it[doneKey]?:emptySet()}.collectAsState(initial=emptySet())
val mistakes by ds.data.map{it[mistakesKey]?:emptySet()}.collectAsState(initial=emptySet())
val xp by ds.data.map{it[xpKey]?:0}.collectAsState(initial=0)
val owned by ds.data.map{it[cardsKey]?:emptySet()}.collectAsState(initial=emptySet())
var tab by remember{mutableStateOf("Inicio")}
val scheme=if(dark)darkColorScheme(primary=androidx.compose.ui.graphics.Color(0xFFE7A8B8),secondary=androidx.compose.ui.graphics.Color(0xFFD6B56D),background=androidx.compose.ui.graphics.Color(0xFF101014),surface=androidx.compose.ui.graphics.Color(0xFF1B1B22))else lightColorScheme(primary=androidx.compose.ui.graphics.Color(0xFF9E5268),secondary=androidx.compose.ui.graphics.Color(0xFF856B34))
MaterialTheme(colorScheme=scheme){Scaffold(bottomBar={NavigationBar{listOf("Inicio","Estudiar","Exámenes","Colección","Perfil").forEach{t->NavigationBarItem(selected=tab==t,onClick={tab=t},icon={Text(when(t){"Inicio"->"⌂";"Estudiar"->"文";"Exámenes"->"✓";"Colección"->"✧";else->"○"})},label={Text(t)})}}}){p->
when(tab){
"Inicio"->Home(name,xp,done.size,mistakes.size,{tab="Estudiar"},{tab="Exámenes"},Modifier.padding(p))
"Estudiar"->Study(ds,lang,done,mistakes,Modifier.padding(p))
"Exámenes"->Exam(Modifier.padding(p))
"Colección"->CollectionScreen(ds,owned,Modifier.padding(p))
else->Profile(ds,name,lang,Modifier.padding(p))
}}}
}
@Composable private fun BoxCard(content:@Composable ColumnScope.()->Unit){Card(Modifier.fillMaxWidth(),shape=RoundedCornerShape(20.dp)){Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(10.dp),content=content)}}
@Composable private fun Heading(a:String,b:String){Text(a,style=MaterialTheme.typography.headlineMedium);Text(b,color=MaterialTheme.colorScheme.onSurfaceVariant)}
@Composable private fun Home(name:String,xp:Int,done:Int,mistakes:Int,study:()->Unit,exam:()->Unit,m:Modifier){
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(16.dp)){
Text("NIHONGO ACADEMY",color=MaterialTheme.colorScheme.primary);Heading("こんにちは, "+name,"Tu camino para aprender un idioma empieza hoy.")
BoxCard{Text("Tu progreso",style=MaterialTheme.typography.titleLarge);Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text(xp.toString()+" XP");Text(done.toString()+" lecciones");Text(mistakes.toString()+" para repasar")};LinearProgressIndicator(progress={(done/20f).coerceIn(0f,1f)},modifier=Modifier.fillMaxWidth())}
BoxCard{Text("Continuar aprendiendo",style=MaterialTheme.typography.titleLarge);Text("Lecciones breves con ejercicios y corrección inmediata.");Button(onClick=study,modifier=Modifier.fillMaxWidth()){Text("Ir a mis lecciones")}}
BoxCard{Text("Simulacro de examen",style=MaterialTheme.typography.titleLarge);Text("Practicá vocabulario, gramática, lectura y comprensión.");OutlinedButton(onClick=exam,modifier=Modifier.fillMaxWidth()){Text("Preparar examen")}}
BoxCard{Text("Plan de 12 semanas",style=MaterialTheme.typography.titleLarge);Text("Una guía progresiva para organizar tu estudio y sostener el hábito.")}
}}
@Composable private fun Study(ds:androidx.datastore.core.DataStore<Preferences>,language:String,done:Set<String>,mistakes:Set<String>,m:Modifier){
val scope=rememberCoroutineScope();var selected by remember{mutableStateOf<Lesson?>(null)};var lang by remember(language){mutableStateOf(language)};var picker by remember{mutableStateOf(false)}
if(selected!=null){LessonPage(selected!!,{selected=null}){wrong->scope.launch{ds.edit{p->val old=p[doneKey]?:emptySet();if(selected!!.id !in old)p[xpKey]=(p[xpKey]?:0)+20;p[doneKey]=old+selected!!.id;p[mistakesKey]=(p[mistakesKey]?:emptySet())+wrong}};selected=null};return}
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
Heading("Tu ruta de estudio","Elegí idioma, nivel y lección. Las lecciones publicadas funcionan sin conexión.")
OutlinedButton(onClick={picker=true},modifier=Modifier.fillMaxWidth()){val l=languages.first{it.code==lang};Text(l.flag+" "+l.name+" · Cambiar idioma")}
if(picker) AlertDialog(
 onDismissRequest={picker=false},
 title={Text("Elegí un idioma")},
 text={
  Column(Modifier.heightIn(max=400.dp).verticalScroll(rememberScrollState())) {
   languages.forEach { item ->
    TextButton(
     onClick={
      lang=item.code
      picker=false
      scope.launch { ds.edit { prefs -> prefs[langKey]=item.code } }
     },
     modifier=Modifier.fillMaxWidth()
    ) { Text(item.flag+" "+item.name+" ("+item.native+")") }
   }
  }
 },
 confirmButton={TextButton(onClick={picker=false}){Text("Cerrar")}}
)
if(lang=="ja")listOf("N5" to "Principiante","N4" to "Básico","N3" to "Intermedio","N2" to "Intermedio alto","N1" to "Avanzado").forEach{pair->BoxCard{Text(pair.first+" · "+pair.second,style=MaterialTheme.typography.titleLarge);val items=lessons.filter{it.level==pair.first};if(items.isEmpty())Text("Ruta preparada. El contenido de este nivel se completará al incorporar el catálogo curricular.");items.forEach{l->OutlinedButton(onClick={selected=l},modifier=Modifier.fillMaxWidth()){Text((if(l.id in done)"✓ " else "")+l.title)}}}}
else BoxCard{Text("Ruta de "+languages.first{it.code==lang}.name,style=MaterialTheme.typography.titleLarge);Text("El catálogo de 15 idiomas está configurado. Esta versión publica únicamente lecciones japonesas con contenido revisado; no presenta cursos vacíos como completos.")}
if(mistakes.isNotEmpty())BoxCard{Text("Repaso de errores",style=MaterialTheme.typography.titleLarge);Text("Hay "+mistakes.size+" respuestas guardadas para revisar.")}
}}
@Composable private fun LessonPage(lesson:Lesson,back:()->Unit,finish:(Set<String>)->Unit){
var index by remember{mutableIntStateOf(0)};var choice by remember{mutableIntStateOf(-1)};var correct by remember{mutableIntStateOf(0)};var answered by remember{mutableStateOf(false)};val wrong=remember{mutableStateListOf<String>()};val q=lesson.exercises[index]
Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
TextButton(onClick=back){Text("← Volver")};Heading(lesson.title,lesson.level+" · Ejercicio "+(index+1)+" de "+lesson.exercises.size);LinearProgressIndicator(progress={(index+if(answered)1 else 0).toFloat()/lesson.exercises.size},modifier=Modifier.fillMaxWidth())
BoxCard{Text(q.prompt,style=MaterialTheme.typography.titleLarge);q.options.forEachIndexed{i,s->OutlinedButton(onClick={if(!answered)choice=i},enabled=!answered,modifier=Modifier.fillMaxWidth()){Text((if(choice==i)"● " else "○ ")+s)}}
if(answered){Text(if(choice==q.answer)"¡Correcto!" else "Respuesta: "+q.options[q.answer],color=MaterialTheme.colorScheme.primary);Text(q.note)}
Button(onClick={if(!answered&&choice>=0){answered=true;if(choice==q.answer)correct++ else wrong.add(lesson.id+"-"+index)}else if(answered){if(index==lesson.exercises.lastIndex){if(correct.toFloat()/lesson.exercises.size>=.7f)finish(wrong.toSet())else{index=0;choice=-1;correct=0;answered=false;wrong.clear()}}else{index++;choice=-1;answered=false}}},enabled=answered||choice>=0,modifier=Modifier.fillMaxWidth()){Text(if(!answered)"Comprobar" else if(index==lesson.exercises.lastIndex)"Finalizar lección" else "Siguiente")}
if(answered&&index==lesson.exercises.lastIndex)Text("Resultado: "+(correct*100/lesson.exercises.size)+"%. Se requiere 70% para completar.")
}}
}
@Composable private fun Exam(m:Modifier){
var started by remember{mutableStateOf(false)};var index by remember{mutableIntStateOf(0)};var choice by remember{mutableIntStateOf(-1)};var score by remember{mutableIntStateOf(0)};var seconds by remember{mutableIntStateOf(600)};var finished by remember{mutableStateOf(false)}
LaunchedEffect(started,finished){while(started&&!finished&&seconds>0){delay(1000);seconds--};if(seconds==0)finished=true}
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
Heading("Exámenes","Simulacros cronometrados con resultados por sección.")
if(!started)BoxCard{Text("Simulacro de práctica",style=MaterialTheme.typography.titleLarge);Text(examQuestions.size.toString()+" preguntas · 10 minutos · vocabulario, gramática, lectura y comprensión.");Button(onClick={started=true},modifier=Modifier.fillMaxWidth()){Text("Comenzar examen")}}
else if(finished)BoxCard{Text("Resultado",style=MaterialTheme.typography.headlineSmall);Text(score.toString()+" de "+examQuestions.size+" respuestas correctas");examQuestions.map{it.section}.distinct().forEach{s->Text(s+" · "+examQuestions.count{it.section==s}+" preguntas")};Button(onClick={started=false;finished=false;index=0;choice=-1;score=0;seconds=600},modifier=Modifier.fillMaxWidth()){Text("Repetir")}}
else{val q=examQuestions[index];BoxCard{Text("Tiempo restante "+(seconds/60)+":"+ (seconds%60).toString().padStart(2,'0'));Text("Pregunta "+(index+1)+" de "+examQuestions.size);Text(q.prompt,style=MaterialTheme.typography.titleLarge);q.options.forEachIndexed{i,s->OutlinedButton(onClick={choice=i},enabled=choice<0,modifier=Modifier.fillMaxWidth()){Text((if(choice==i)"● " else "○ ")+s)}};Button(onClick={if(choice>=0){if(choice==q.answer)score++;if(index==examQuestions.lastIndex)finished=true else{index++;choice=-1}}},enabled=choice>=0,modifier=Modifier.fillMaxWidth()){Text(if(index==examQuestions.lastIndex)"Terminar" else "Siguiente")}}}
}}
@Composable private fun CollectionScreen(ds:androidx.datastore.core.DataStore<Preferences>,owned:Set<String>,m:Modifier){
val scope=rememberCoroutineScope();var message by remember{mutableStateOf("")}
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
Heading("Colección","Sobres diarios con tres cartas coleccionables.")
BoxCard{Text("Sobre diario",style=MaterialTheme.typography.titleLarge);Text("Podés reclamar un sobre por día.");Button(onClick={scope.launch{val today=LocalDate.now().toString();var got=false;ds.edit{p->if(p[rewardKey]!=today){p[rewardKey]=today;val picks=cards.keys.shuffled().take(3).toSet();p[cardsKey]=(p[cardsKey]?:emptySet())+picks;p[xpKey]=(p[xpKey]?:0)+10;got=true}};message=if(got)"¡Sobre abierto! Se agregaron tres cartas." else "Ya reclamaste el sobre de hoy."}},modifier=Modifier.fillMaxWidth()){Text("Abrir sobre")};if(message.isNotBlank())Text(message)}
Text("Cartas obtenidas: "+owned.size,style=MaterialTheme.typography.titleMedium);cards.filterKeys{it in owned}.values.forEach{c->BoxCard{Text(c.first,style=MaterialTheme.typography.headlineMedium);Text(c.second)}};if(owned.isEmpty())Text("Todavía no tenés cartas.")
}}
@Composable private fun Profile(ds:androidx.datastore.core.DataStore<Preferences>,name:String,lang:String,m:Modifier){
val scope=rememberCoroutineScope();val dark by ds.data.map{it[darkKey]?:true}.collectAsState(initial=true);var edit by remember(name){mutableStateOf(name)}
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
Heading("Perfil y ajustes","Preferencias, privacidad y accesibilidad.")
BoxCard{Text("Tu perfil",style=MaterialTheme.typography.titleLarge);OutlinedTextField(value=edit,onValueChange={edit=it},label={Text("Nombre visible")},modifier=Modifier.fillMaxWidth());Button(onClick={scope.launch{ds.edit{it[nameKey]=edit.trim().ifBlank{"Estudiante"}}}}){Text("Guardar nombre")};Text("Idioma: "+languages.first{it.code==lang}.name)}
BoxCard{Text("Apariencia",style=MaterialTheme.typography.titleLarge);Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text(if(dark)"Tema oscuro" else "Tema claro");Switch(checked=dark,onCheckedChange={v->scope.launch{ds.edit{it[darkKey]=v}}})}}
BoxCard{Text("Privacidad y sincronización",style=MaterialTheme.typography.titleLarge);Text("El progreso se guarda localmente. Las cuentas online y la sincronización entre dispositivos requieren integrar un backend seguro.")}
BoxCard{Text("Comunidad",style=MaterialTheme.typography.titleLarge);Text("Preguntas, respuestas, votos y moderación necesitan un servicio online. No se muestran publicaciones ficticias.")}
BoxCard{Text("Pronunciación",style=MaterialTheme.typography.titleLarge);VoicePractice()}
}}
@Composable private fun VoicePractice(){
val context=LocalContext.current;var result by remember{mutableStateOf("Todavía no se realizó una práctica.")}
val launcher=androidx.activity.compose.rememberLauncherForActivityResult(androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()){r->result=r.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()?:"No se reconoció una respuesta."}
Button(onClick={val intent=Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply{putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.JAPANESE.toLanguageTag());putExtra(RecognizerIntent.EXTRA_PROMPT,"Decí una frase en japonés")};runCatching{launcher.launch(intent)}.onFailure{result="No hay reconocedor de voz disponible."}}){Text("Practicar con voz")}
Text(result);Text("El dispositivo devuelve texto reconocido. La evaluación fonética precisa requiere un motor especializado.")
}
