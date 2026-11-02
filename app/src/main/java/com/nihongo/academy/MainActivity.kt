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
private val bestExamKey=intPreferencesKey("best_exam_score")
private val romajiKey=booleanPreferencesKey("show_romaji")
private val dailySecondsKey=intPreferencesKey("daily_seconds")
private val dailyDateKey=stringPreferencesKey("daily_date")
private val streakKey=intPreferencesKey("daily_streak")
private val lastGoalDateKey=stringPreferencesKey("last_goal_date")
private data class Language(val code:String,val name:String,val native:String,val flag:String)
private data class Exercise(val prompt:String,val options:List<String>,val answer:Int,val note:String)
private data class Lesson(val id:String,val level:String,val title:String,val exercises:List<Exercise>)
private data class Question(val section:String,val prompt:String,val options:List<String>,val answer:Int)
private val languages=listOf(
Language("en","Inglés","English","🇬🇧"),Language("zh","Mandarín","中文","🇨🇳"),Language("es","Español","Español","🇪🇸"),Language("hi","Hindi","हिन्दी","🇮🇳"),Language("ar","Árabe","العربية","🇸🇦"),Language("fr","Francés","Français","🇫🇷"),Language("pt","Portugués","Português","🇧🇷"),Language("bn","Bengalí","বাংলা","🇧🇩"),Language("ru","Ruso","Русский","🇷🇺"),Language("de","Alemán","Deutsch","🇩🇪"),Language("ja","Japonés","日本語","🇯🇵"),Language("ko","Coreano","한국어","🇰🇷"),Language("it","Italiano","Italiano","🇮🇹"),Language("tr","Turco","Türkçe","🇹🇷"),Language("id","Indonesio","Bahasa Indonesia","🇮🇩"))
private fun ex(p:String,o:List<String>,a:Int,n:String)=Exercise(p,o,a,n)
private fun lesson(id:String,level:String,title:String,vararg exercises:Exercise)=Lesson(id,level,title,exercises.toList())
private val lessons=listOf(
 lesson("n5-u01","N5","Saludos y presentación",
  ex("¿Qué significa watashi?",listOf("Yo","Tú","Maestro","Estudiante"),0,"watashi significa yo."),
  ex("Completá: Watashi ___ Sakura desu.",listOf("wa","o","ni","de"),0,"wa marca el tema de la frase."),
  ex("¿Qué significa hajimemashite?",listOf("Encantado de conocerte","Gracias","Disculpe","Hasta mañana"),0,"Se usa al conocer a alguien."),
  ex("¿Cuál es la palabra para estudiante?",listOf("gakusei","sensei","anata","sumimasen"),0,"gakusei significa estudiante."),
  ex("¿Qué expresión se usa para pedir algo con cortesía?",listOf("onegaishimasu","arigatou","hajimemashite","anata"),0,"onegaishimasu se usa al pedir algo, como por favor.")),
 lesson("n5-u02","N5","Partículas básicas",
  ex("¿Qué partícula marca el tema?",listOf("wa","ga","o","de"),0,"wa marca el tema de la oración."),
  ex("Completá: Neko ___ imasu.",listOf("ga","o","de","mo"),0,"ga marca el sujeto en esta frase."),
  ex("¿Qué partícula marca el objeto directo?",listOf("o","wa","ni","mo"),0,"o marca el objeto directo."),
  ex("¿Qué significa mo?",listOf("También","Lugar","Objeto","Tema"),0,"mo expresa también."),
  ex("¿Qué partícula indica el lugar donde ocurre una acción?",listOf("de","wa","o","ga"),0,"de indica el lugar de la acción."),
  ex("¿Qué partícula indica destino o dirección?",listOf("ni","wa","o","ga"),0,"ni puede marcar destino, como en gakkou ni ikimasu.")),
 lesson("n5-u03","N5","Verbos en forma masu",
  ex("Forma masu de taberu (comer)",listOf("tabemasu","tabenai","tabete","tabemashita"),0,"taberu cambia a tabemasu."),
  ex("Forma masu de nomu (beber)",listOf("nomimasu","nomitai","nomimasen","nomimashita"),0,"nomu cambia a nomimasu."),
  ex("¿Qué significa ikimasu?",listOf("Ir","Venir","Hacer","Ver"),0,"iku → ikimasu significa ir."),
  ex("Forma masu de kuru (venir)",listOf("kimasu","kimasen","kita","kite"),0,"kuru cambia irregularmente a kimasu."),
  ex("Completá: Mainichi gohan o ___.",listOf("tabemasu","ikimasu","mimasu","kimasu"),0,"tabemasu significa comer en forma cortés."),
  ex("Forma masu de suru (hacer)",listOf("shimasu","mimasu","kimasu","ikimasu"),0,"suru cambia a shimasu."),
  ex("Forma masu de miru (ver)",listOf("mimasu","shimasu","nomimasu","kimasu"),0,"miru cambia a mimasu.")),
 lesson("n5-u04","N5","Pasado y negativo de masu",
  ex("Pasado cortés de tabemasu",listOf("tabemashita","tabemasen","tabemasu","tabete"),0,"tabemashita expresa comí."),
  ex("¿Qué significa kinou?",listOf("Ayer","Hoy","Mañana","Ahora"),0,"kinou significa ayer."),
  ex("Negativo cortés de nomimasu",listOf("nomimasen","nomimashita","nomimasu","nomitai"),0,"nomimasen significa no bebo."),
  ex("¿Qué significa ikimashita?",listOf("Fui","Voy","No voy","Quiero ir"),0,"ikimashita es el pasado de ikimasu."),
  ex("Completá: Kyou wa benkyou ___.",listOf("shimasen","shimashita","shimasu ka","shitai"),0,"shimasen expresa la forma negativa cortés.")),
 lesson("n5-u05","N5","Adjetivos en i",
  ex("¿Qué significa ookii?",listOf("Grande","Pequeño","Caro","Frío"),0,"ookii significa grande."),
  ex("¿Qué significa chiisai?",listOf("Pequeño","Grande","Barato","Caliente"),0,"chiisai significa pequeño."),
  ex("¿Qué significa takai?",listOf("Caro o alto","Barato","Frío","Tranquilo"),0,"takai puede significar caro o alto."),
  ex("Negativo de un adjetivo i: atsui",listOf("atsukunai desu","atsui desu","atsukatta","atsuna desu"),0,"La forma negativa se construye con -kunai desu."),
  ex("¿Qué significa yasui?",listOf("Barato","Caro","Grande","Largo"),0,"yasui significa barato."),
  ex("¿Qué significa samui?",listOf("Frío","Caliente","Caro","Grande"),0,"samui describe algo frío o el clima frío.")),
 lesson("n5-u06","N5","Adjetivos en na",
  ex("¿Qué significa kirei?",listOf("Bonito","Rápido","Caro","Frío"),0,"kirei significa bonito."),
  ex("¿Qué significa shizuka?",listOf("Tranquilo","Grande","Divertido","Pequeño"),0,"shizuka significa tranquilo."),
  ex("¿Qué significa genki?",listOf("Sano o animado","Caro","Frío","Largo"),0,"genki significa sano o animado."),
  ex("Forma negativa de benri desu",listOf("benri ja arimasen","benri kunai","benri mashita","benri nai desu"),0,"Los adjetivos na usan ja arimasen en negativo."),
  ex("¿Qué significa suki?",listOf("Gustar","Descansar","Volver","Prestar"),0,"suki expresa gusto."),
  ex("¿Qué significa hima?",listOf("Tener tiempo libre","Estar enfermo","Ser práctico","Ser grande"),0,"hima significa libre o desocupado.")),
 lesson("n5-u07","N5","Números, horas y días",
  ex("¿Cómo se dice uno?",listOf("ichi","ni","san","go"),0,"ichi significa uno."),
  ex("¿Qué número es roku?",listOf("Seis","Siete","Ocho","Nueve"),0,"roku significa seis."),
  ex("¿Qué significa ji al indicar la hora?",listOf("Hora","Minuto","Día","Semana"),0,"ji se usa para indicar la hora."),
  ex("¿Qué significa nichiyoubi?",listOf("Domingo","Lunes","Miércoles","Viernes"),0,"nichiyoubi significa domingo."),
  ex("Completá: Ima san ___ desu.",listOf("ji","fun","nichi","getsu"),0,"san ji significa las tres."),
  ex("¿Cómo se dice diez?",listOf("juu","kyuu","nana","yon"),0,"juu significa diez.")),
 lesson("n5-u08","N5","Lugares y direcciones",
  ex("¿Qué significa eki?",listOf("Estación","Hospital","Biblioteca","Parque"),0,"eki significa estación."),
  ex("¿Qué significa byouin?",listOf("Hospital","Escuela","Casa","Parque"),0,"byouin significa hospital."),
  ex("¿Qué significa koko?",listOf("Aquí","Allí","Ayer","Mañana"),0,"koko significa aquí."),
  ex("¿Cómo se pregunta dónde está un lugar?",listOf("wa doko desu ka","wa nan desu ka","wa dare desu ka","wa itsu desu ka"),0,"doko pregunta dónde."),
  ex("¿Qué significa toshokan?",listOf("Biblioteca","Estación","Hospital","Casa"),0,"toshokan significa biblioteca."),
  ex("¿Qué significa kouen?",listOf("Parque","Hospital","Estación","Biblioteca"),0,"kouen significa parque."),
  ex("¿Qué significa asoko?",listOf("Allí","Aquí","Ayer","Mañana"),0,"asoko significa allí.")),
 lesson("n5-u09","N5","Pedir y dar instrucciones",
  ex("¿Qué expresa ~te kudasai?",listOf("Por favor, haga algo","No hacer algo","Quiero hacer algo","Ayer hice algo"),0,"~te kudasai se usa para pedir una acción."),
  ex("¿Qué significa mite?",listOf("Mire","Escuche","Espere","Vuelva"),0,"mite significa mire."),
  ex("¿Qué significa kiite?",listOf("Escuche","Mire","Espere","Hable"),0,"kiite significa escuche."),
  ex("¿Qué significa yukkuri?",listOf("Despacio","Rápido","En silencio","Ayer"),0,"yukkuri significa despacio."),
  ex("¿Cómo se dice 'Hable despacio, por favor'?",listOf("Yukkuri hanashite kudasai","Mite kudasai","Matte kudasai","Hayaku kudasai"),0,"La frase del catálogo usa yukkuri hanashite kudasai."),
  ex("¿Qué expresa ~te mo ii desu ka?",listOf("¿Puedo hacer...?","No debo hacerlo","Hay que hacerlo","No hace falta hacerlo"),0,"~te mo ii desu ka se usa para pedir permiso.")),
 lesson("n5-u10","N5","Existencia y posesión",
  ex("¿Qué verbo se usa para existencia de cosas?",listOf("aru","iru","kuru","suru"),0,"aru se usa para cosas."),
  ex("¿Qué verbo se usa para existencia de seres vivos?",listOf("iru","aru","iku","miru"),0,"iru se usa para seres vivos."),
  ex("¿Qué significa kuruma?",listOf("Coche","Persona","Escritorio","Boleto"),0,"kuruma significa coche."),
  ex("¿Qué significa arimasen?",listOf("No hay","Hay","Está aquí","Quiero"),0,"arimasen es la forma negativa de arimasu."),
  ex("Completá: Heya ni hito ga ___.",listOf("imasu","arimasu","ikimasu","kimasu"),0,"imasu expresa que hay una persona en la habitación."),
  ex("¿Qué significa tsukue?",listOf("Escritorio","Boleto","Coche","Persona"),0,"tsukue significa escritorio."),
  ex("¿Qué significa kippu?",listOf("Boleto","Escritorio","Persona","Coche"),0,"kippu significa boleto.")),
 lesson("n4-u01","N4","Forma te y te iru",
  ex("¿Qué expresa tabete imasu?",listOf("Estoy comiendo","No como","Comí ayer","Quiero comer"),0,"~te imasu expresa una acción en curso."),
  ex("Forma te de yomu (leer)",listOf("yonde","yomimasu","yomanai","yomashita"),0,"yomu cambia a yonde."),
  ex("Forma te de kaku (escribir)",listOf("kaite","kakimasu","kakanai","kaita"),0,"kaku cambia a kaite."),
  ex("¿Qué significa matte?",listOf("Esperando / espera","Leyendo","Escribiendo","Comiendo"),0,"matte es la forma te de matsu."),
  ex("Completá: Ima hon o ___ imasu.",listOf("yonde","yomu","yomimasu","yomanai"),0,"yonde imasu significa estoy leyendo."),
  ex("¿Qué significa tabete?",listOf("Comiendo / y comer","Leyendo","Escribiendo","Esperando"),0,"tabete es la forma te de taberu.")),
 lesson("n4-u02","N4","Forma nai y obligación",
  ex("¿Qué significa tabenai?",listOf("No comer","Comer","Comí","Poder comer"),0,"tabenai es la forma negativa informal."),
  ex("¿Qué expresa nakereba narimasen?",listOf("Hay que hacerlo","No hace falta","Quiero hacerlo","Ya lo hice"),0,"Expresa obligación."),
  ex("¿Qué expresa nakute mo ii desu?",listOf("No hace falta hacerlo","Es obligatorio","No puedo hacerlo","Lo hice"),0,"Expresa que algo no es necesario."),
  ex("¿Qué significa yasumu?",listOf("Descansar","Estudiar","Ir","Crear"),0,"yasumu significa descansar."),
  ex("¿Qué significa kusuri?",listOf("Medicina","Agua","Libro","Estación"),0,"kusuri significa medicina."),
  ex("¿Qué significa ikanai?",listOf("No ir","No comer","Descansar","Estudiar"),0,"ikanai es la forma negativa informal de iku.")),
 lesson("n4-u03","N4","Forma potencial",
  ex("¿Qué expresa taberareru?",listOf("Poder comer","No comer","Comer ayer","Querer comer"),0,"taberareru expresa capacidad o posibilidad."),
  ex("¿Qué significa oyogeru?",listOf("Poder nadar","Poder hablar","Poder crear","Poder volver"),0,"oyogeru significa poder nadar."),
  ex("¿Qué significa dekiru?",listOf("Poder hacer","Descansar","Recibir","Dar"),0,"dekiru significa poder hacer."),
  ex("¿Qué expresa koto ga dekimasu?",listOf("Puedo hacer algo","No debo hacerlo","Lo hice ayer","Quiero hacerlo"),0,"La estructura expresa capacidad."),
  ex("¿Qué significa hanasu?",listOf("Hablar","Nadar","Crear","Volver"),0,"hanasu significa hablar."),
  ex("¿Qué significa tsukuru?",listOf("Hacer o crear","Nadar","Hablar","Descansar"),0,"tsukuru significa hacer o crear.")),
 lesson("n4-u04","N4","Expresar deseos",
  ex("¿Qué expresa tabetai desu?",listOf("Quiero comer","No quiero comer","Estoy comiendo","Comí"),0,"~tai desu expresa deseo."),
  ex("¿Qué significa nomitai?",listOf("Quiero beber","Quiero ir","Quiero descansar","Quiero ver"),0,"nomitai significa quiero beber."),
  ex("¿Qué significa ikitai?",listOf("Quiero ir","Quiero comer","Quiero hablar","Quiero dormir"),0,"ikitai significa quiero ir."),
  ex("Negativo de deseo: ~takunai desu",listOf("No quiero hacer","Quiero hacer","Puedo hacer","Debo hacer"),0,"~takunai desu expresa no querer."),
  ex("¿Cómo se pregunta '¿Quieres comer algo?'",listOf("Nanika tabetai desu ka","Nihon ni ikitai desu","Tabenai desu","Nomimasu ka"),0,"Es el ejemplo de pregunta de deseo del catálogo."),
  ex("¿Qué significa aitai?",listOf("Quiero ver a alguien","Quiero beber","Quiero ir","Quiero descansar"),0,"aitai expresa el deseo de ver a alguien."),
  ex("¿Qué significa yasumitai?",listOf("Quiero descansar","Quiero ir","Quiero hablar","Quiero beber"),0,"yasumitai significa quiero descansar.")),
 lesson("n4-u05","N4","Comparación y superlativo",
  ex("¿Qué significa ~yori?",listOf("Más que","También","Porque","Quizá"),0,"~yori se usa para comparar."),
  ex("¿Qué significa ichiban?",listOf("Lo más","Poco","Igual","Rápido"),0,"ichiban expresa el superlativo."),
  ex("¿Qué significa hayai?",listOf("Rápido","Largo","Divertido","Escaso"),0,"hayai significa rápido."),
  ex("¿Qué significa nagai?",listOf("Largo","Rápido","Corto","Pequeño"),0,"nagai significa largo."),
  ex("¿Qué estructura expresa 'lo más ... de entre'?",listOf("no naka de ichiban","~nakereba narimasen","~te imasu","~kamo shiremasen"),0,"no naka de ichiban forma el superlativo."),
  ex("¿Qué significa tanoshii?",listOf("Divertido","Largo","Rápido","Escaso"),0,"tanoshii significa divertido."),
  ex("¿Qué significa sukunai?",listOf("Poco o escaso","Divertido","Largo","Rápido"),0,"sukunai significa poco o escaso.")),
 lesson("n4-u06","N4","Dar y recibir",
  ex("¿Qué significa ageru?",listOf("Dar","Recibir","Prestar","Volver"),0,"ageru significa dar."),
  ex("¿Qué significa morau?",listOf("Recibir","Dar","Crear","Hablar"),0,"morau significa recibir."),
  ex("¿Qué significa kureru?",listOf("Dar a mí","Recibir","Pedir prestado","Descansar"),0,"kureru indica que alguien da algo al hablante."),
  ex("¿Qué significa kasu?",listOf("Prestar","Pedir prestado","Dar","Recibir"),0,"kasu significa prestar."),
  ex("¿Qué significa kariru?",listOf("Pedir prestado","Prestar","Dar","Hacer"),0,"kariru significa pedir prestado.")),
 lesson("n4-u07","N4","Opinión y probabilidad",
  ex("¿Qué expresa to omoimasu?",listOf("Pienso que","Es obligatorio","Quiero","No hay"),0,"to omoimasu expresa opinión."),
  ex("¿Qué significa tabun?",listOf("Probablemente","Seguramente","Quizá","Ayer"),0,"tabun significa probablemente."),
  ex("¿Qué significa kitto?",listOf("Seguramente","Quizá","Poco","Rápido"),0,"kitto significa seguramente."),
  ex("¿Qué expresa kamo shiremasen?",listOf("Quizá","Siempre","Nunca","También"),0,"kamo shiremasen expresa posibilidad."),
  ex("Completá: Kanojo wa kuru ___ shiremasen.",listOf("kamo","yori","naka","te"),0,"kamo shiremasen significa quizá."),
  ex("¿Qué expresa ~deshou?",listOf("Probablemente","También","No hace falta","Más que"),0,"deshou expresa una probabilidad o suposición."),
  ex("¿Qué significa shitte imasu?",listOf("Sé / conozco","Pienso","Quizá","Seguramente"),0,"shitte imasu significa sé o conozco.")),
 lesson("n4-u08","N4","Condicionales",
  ex("¿Qué significa ~tara?",listOf("Si / cuando","Más que","También","No hace falta"),0,"~tara expresa condición o momento."),
  ex("¿Qué expresa ~to en una condición?",listOf("Resultado natural o habitual","Deseo","Obligación","Pasado"),0,"~to se usa para resultados naturales o habituales."),
  ex("¿Qué significa ame?",listOf("Lluvia","Buen tiempo","Cansancio","Casa"),0,"ame significa lluvia."),
  ex("¿Qué significa hare?",listOf("Buen tiempo","Lluvia","Estar cansado","Volver"),0,"hare significa buen tiempo."),
  ex("¿Qué significa kaeru?",listOf("Volver","Llover","Florecer","Estudiar"),0,"kaeru significa volver."),
  ex("¿Qué significa tsukarete iru?",listOf("Estar cansado","Estar contento","Estar ocupado","Estar enfermo"),0,"tsukarete iru significa estar cansado."))
)
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
val showRomaji by ds.data.map{it[romajiKey]?:true}.collectAsState(initial=true)
val name by ds.data.map{it[nameKey]?:"Estudiante"}.collectAsState(initial="Estudiante")
val done by ds.data.map{it[doneKey]?:emptySet()}.collectAsState(initial=emptySet())
val mistakes by ds.data.map{it[mistakesKey]?:emptySet()}.collectAsState(initial=emptySet())
val xp by ds.data.map{it[xpKey]?:0}.collectAsState(initial=0)
val owned by ds.data.map{it[cardsKey]?:emptySet()}.collectAsState(initial=emptySet())
val today=LocalDate.now().toString()
val dailySeconds by ds.data.map{if(it[dailyDateKey]==today)it[dailySecondsKey]?:0 else 0}.collectAsState(initial=0)
val streak by ds.data.map{val last=it[lastGoalDateKey];if(last==today||last==LocalDate.now().minusDays(1).toString())it[streakKey]?:0 else 0}.collectAsState(initial=0)
var sessionRunning by remember{mutableStateOf(false)}
var sessionElapsed by remember{mutableIntStateOf(0)}
LaunchedEffect(sessionRunning){while(sessionRunning){delay(1000);sessionElapsed++}}
var tab by remember{mutableStateOf("Inicio")}
val scheme=if(dark)darkColorScheme(primary=androidx.compose.ui.graphics.Color(0xFFE7A8B8),secondary=androidx.compose.ui.graphics.Color(0xFFD6B56D),background=androidx.compose.ui.graphics.Color(0xFF101014),surface=androidx.compose.ui.graphics.Color(0xFF1B1B22))else lightColorScheme(primary=androidx.compose.ui.graphics.Color(0xFF9E5268),secondary=androidx.compose.ui.graphics.Color(0xFF856B34))
MaterialTheme(colorScheme=scheme){Scaffold(bottomBar={NavigationBar{listOf("Inicio","Estudiar","Plan","Exámenes","Colección","Perfil").forEach{t->NavigationBarItem(selected=tab==t,onClick={tab=t},icon={Text(when(t){"Inicio"->"⌂";"Estudiar"->"文";"Exámenes"->"✓";"Colección"->"✧";else->"○"})},label={Text(t)})}}}){p->
when(tab){
"Inicio"->Home(name,xp,done.size,mistakes.size,dailySeconds,streak,sessionRunning,sessionElapsed,{sessionElapsed=0;sessionRunning=true},{val elapsed=sessionElapsed;sessionRunning=false;sessionElapsed=0;scope.launch{ds.edit{prefs->val date=LocalDate.now().toString();val previous=if(prefs[dailyDateKey]==date)prefs[dailySecondsKey]?:0 else 0;val total=previous+elapsed;prefs[dailyDateKey]=date;prefs[dailySecondsKey]=total;if(isDailyStudyGoalReached(total)&&prefs[lastGoalDateKey]!=date){prefs[streakKey]=nextDailyStreak(prefs[streakKey]?:0,prefs[lastGoalDateKey],date);prefs[lastGoalDateKey]=date}}}},{tab="Estudiar"},{tab="Exámenes"},{tab="Plan"},Modifier.padding(p))
"Estudiar"->Study(ds,lang,done,mistakes,showRomaji,Modifier.padding(p))
"Plan"->StudyPlanScreen(ds,Modifier.padding(p))
"Exámenes"->Exam(ds,Modifier.padding(p))
"Colección"->CollectionScreen(ds,owned,dailySeconds,Modifier.padding(p))
else->Profile(ds,name,lang,Modifier.padding(p))
}}}
}
@Composable private fun StudyPlanScreen(ds:androidx.datastore.core.DataStore<Preferences>,m:Modifier){
 val scope=rememberCoroutineScope()
 val progress by ds.weeklyStudyProgress().collectAsState(initial=WeeklyStudyProgress())
 val savedKanaMistakes by ds.weeklyKanaMistakes().collectAsState(initial=emptySet())
 val wrongKana=remember{mutableStateListOf<String>()}
 var quizWeek by remember{mutableIntStateOf(0)}
 var quizIndex by remember{mutableIntStateOf(0)}
 var selectedAnswer by remember{mutableIntStateOf(-1)}
 var correct by remember{mutableIntStateOf(0)}
 var submitted by remember{mutableStateOf(false)}
 var resultText by remember{mutableStateOf("")}
 val week=progress.currentWeek
 val entries=kanaForWeek(week)
 val assessment=weeklyAssessmentForWeek(week)
 val weekDays=when(week){1->n5KanaWeeks.take(7);2->n5KanaWeeks.drop(7).take(7);3,4->grammarDaysForWeek(week);5,6->verbDaysForWeek(week);7,8->adjectiveDaysForWeek(week);9,10->kanjiDaysForWeek(week);else->emptyList()}
 Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
  Heading("Plan N5 · 12 semanas","30 minutos diarios · semana actual: $week de 12")
  LinearProgressIndicator(progress={progress.completedWeeks.size/12f},modifier=Modifier.fillMaxWidth())
  BoxCard{
   Text(if(progress.paused)"Plan pausado" else "Semana $week · "+(if(week==1)"Hiragana" else if(week==2)"Katakana" else if(week==3)"Saludos y presentación" else if(week==4)"Partículas básicas" else if(week==5)"Verbos en forma masu" else if(week==6)"Pasado y negativo" else if(week==7)"Adjetivos i" else if(week==8)"Adjetivos na" else if(week==9)"Kanji básicos · primer bloque" else if(week==10)"Kanji básicos · segundo bloque y repaso" else "Próximo bloque"),style=MaterialTheme.typography.titleLarge)
   Text(if(progress.paused)"Tu avance se conserva. Reanudá cuando quieras." else if(week<=2)"Aprendé a reconocer, leer y escribir los caracteres del silabario de esta semana." else if(week==3)"Practicá saludos, presentaciones y expresiones de cortesía." else if(week==4)"Aprendé a distinguir wa, ga, o, ni y de en frases sencillas." else if(week==5)"Conjugá verbos comunes en forma masu para expresar acciones en presente cortés." else if(week==6)"Usá mashita para el pasado y masen para el negativo cortés." else if(week==7)"Describí cosas con adjetivos i y practicá su forma negativa ~kunai desu." else if(week==8)"Usá adjetivos na con las formas ~desu y ~ja arimasen." else if(week==9)"Iniciá la práctica de kanji frecuentes mediante lectura, escritura repetida y repaso." else if(week==10)"Consolidá la lectura y escritura de los kanji frecuentes y prepará la prueba semanal." else "El siguiente bloque del PDF se habilitará al completar las semanas anteriores.")
   Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){
    OutlinedButton(onClick={scope.launch{ds.saveWeeklyStudyProgress(if(progress.paused)progress.resume() else progress.pause())}}){Text(if(progress.paused)"Reanudar plan" else "Pausar plan")}
    if(week<=8) Button(onClick={quizWeek=week;quizIndex=0;selectedAnswer=-1;correct=0;wrongKana.clear();submitted=false;resultText=""},enabled=!progress.paused){Text("Evaluación semanal")}
     else if(week in 9..10) Text("Evaluación pendiente: falta conectar el banco validado de kanji y sus lecturas.")
   }
   progress.lastAssessment?.let{last->
    Text("Última prueba: semana ${last.week} · ${last.correctAnswers}/${last.totalQuestions} · ${last.incorrectPercent}% de errores")
    Text(if(last.passed)"Semana aprobada." else "Hay que repetir la semana con práctica adicional.",color=MaterialTheme.colorScheme.primary)
   }
   if(resultText.isNotBlank())Text(resultText)
  }
  if(quizWeek>0){
   val q=assessment.getOrNull(quizIndex)
   BoxCard{
    Text("Evaluación · "+when(quizWeek){1->"hiragana";2->"katakana";3->"saludos y presentación";4->"partículas";5->"verbos en masu";6->"pasado y negativo";7->"adjetivos i";8->"adjetivos na";else->"N5"},style=MaterialTheme.typography.titleLarge)
    if(!submitted&&q!=null){
     Text("Pregunta ${quizIndex+1} de ${assessment.size}")
     Text(q.prompt,style=MaterialTheme.typography.titleMedium)
     q.options.forEachIndexed{i,option->OutlinedButton(onClick={selectedAnswer=i},enabled=selectedAnswer<0,modifier=Modifier.fillMaxWidth()){Text((if(selectedAnswer==i)"● " else "○ ")+option)}}
     Button(onClick={
      if(selectedAnswer>=0){
       if(selectedAnswer==q.answerIndex)correct++ else wrongKana.add(q.prompt.substringAfter("representa "))
       if(quizIndex==assessment.lastIndex){
        scope.launch{
         val saved=ds.recordWeeklyAssessment(correct,assessment.size,wrongKana.toSet())
         resultText=if(saved.lastAssessment?.passed==true)"¡Semana aprobada! Se desbloqueó la siguiente semana." else "Semana no aprobada. Repasá los errores y volvé a intentarlo."
         submitted=true
        }
       }else{quizIndex++;selectedAnswer=-1}
      }
     },enabled=selectedAnswer>=0,modifier=Modifier.fillMaxWidth()){Text(if(quizIndex==assessment.lastIndex)"Enviar evaluación" else "Siguiente pregunta")}
    }else if(submitted){
     Text(resultText)
     Text("Resultado: $correct de ${assessment.size}.")
     Button(onClick={quizWeek=0},modifier=Modifier.fillMaxWidth()){Text("Volver al plan")}
    }
   }
  }else if(week<=2){
   BoxCard{
    Text("Caracteres de la semana",style=MaterialTheme.typography.titleLarge)
    Text(if(week==1)"Hiragana · 46 caracteres básicos" else "Katakana · 46 caracteres básicos")
    entries.chunked(5).forEach{line->
     Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceEvenly){
      line.forEach{entry->Column(horizontalAlignment=androidx.compose.ui.Alignment.CenterHorizontally){
       Text(entry.kana,style=MaterialTheme.typography.headlineSmall)
       Text(entry.romaji,color=MaterialTheme.colorScheme.onSurfaceVariant)
      }}
     }
    }
    Text("El romaji sirve de apoyo inicial. Intentá leer primero el kana y usá la transliteración para comprobar.")
   }
   Text("Rutina diaria",style=MaterialTheme.typography.titleLarge)
   weekDays.forEach{day->BoxCard{
    Text("Día ${day.day} · ${day.title}",style=MaterialTheme.typography.titleMedium)
    Text(day.focus)
    day.practice.forEachIndexed{i,task->Text("${i+1}. $task")}
   }}
  }else if(week in 3..10){
   Text("Rutina diaria",style=MaterialTheme.typography.titleLarge)
   weekDays.forEach{day->BoxCard{
    Text("Día ${day.day} · ${day.title}",style=MaterialTheme.typography.titleMedium)
    Text(day.focus)
    day.practice.forEachIndexed{i,task->Text("${i+1}. $task")}
   }}
  }else{
   BoxCard{
    Text("Contenido de la siguiente etapa",style=MaterialTheme.typography.titleLarge)
    Text("El plan continúa con la semana 11: comprensión auditiva mediante diálogos cortos del nivel N5.")
   }
  }
  if(savedKanaMistakes.isNotEmpty())BoxCard{
   Text("Errores para repasar",style=MaterialTheme.typography.titleLarge)
   Text("Estos elementos quedaron guardados de una evaluación no aprobada:")
   savedKanaMistakes.sorted().forEach{item->Text("• "+item.replaceFirst(":", " · "))}
  }
  BoxCard{
   Text("Reglas del plan",style=MaterialTheme.typography.titleLarge)
   Text("• Si los errores superan el 30%, repetís la semana con práctica adicional.")
   Text("• Con 30% de errores o menos, aprobás y se desbloquea la semana siguiente.")
   Text("• Podés pausar y reanudar; el progreso se guarda localmente.")
   Text("• Cada semana está limitada a 30 minutos diarios como propone el PDF.")
  }
  BoxCard{
   Text("Progreso",style=MaterialTheme.typography.titleLarge)
   Text("Semanas aprobadas: ${progress.completedWeeks.sorted().joinToString().ifBlank{"ninguna"}}")
   Text("Intentos registrados: ${progress.attemptsByWeek.toSortedMap().entries.joinToString { "Semana ${it.key}: ${it.value}" }.ifBlank{"ninguno"}}")
  }
 }
}
@Composable private fun BoxCard(content:@Composable ColumnScope.()->Unit){Card(Modifier.fillMaxWidth(),shape=RoundedCornerShape(20.dp)){Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(10.dp),content=content)}}
@Composable private fun Heading(a:String,b:String){Text(a,style=MaterialTheme.typography.headlineMedium);Text(b,color=MaterialTheme.colorScheme.onSurfaceVariant)}
@Composable private fun Home(name:String,xp:Int,done:Int,mistakes:Int,dailySeconds:Int,streak:Int,sessionRunning:Boolean,sessionElapsed:Int,startSession:()->Unit,stopSession:()->Unit,study:()->Unit,exam:()->Unit,plan:()->Unit,m:Modifier){
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(16.dp)){
Text("NIHONGO ACADEMY",color=MaterialTheme.colorScheme.primary);Heading("こんにちは, "+name,"Tu camino para aprender un idioma empieza hoy.")
BoxCard{Text("Tu progreso",style=MaterialTheme.typography.titleLarge);Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text(xp.toString()+" XP");Text(done.toString()+" lecciones");Text(mistakes.toString()+" para repasar")};LinearProgressIndicator(progress={(done/18f).coerceIn(0f,1f)},modifier=Modifier.fillMaxWidth())}
BoxCard{Text("Continuar aprendiendo",style=MaterialTheme.typography.titleLarge);Text("Lecciones breves con ejercicios y corrección inmediata.");Button(onClick=study,modifier=Modifier.fillMaxWidth()){Text("Ir a mis lecciones")}}
BoxCard{Text("Simulacro de examen",style=MaterialTheme.typography.titleLarge);Text("Practicá vocabulario, gramática, lectura y comprensión.");OutlinedButton(onClick=exam,modifier=Modifier.fillMaxWidth()){Text("Preparar examen")}}
BoxCard{Text("Meta diaria · 30 minutos",style=MaterialTheme.typography.titleLarge);val totalSeconds=dailySeconds+if(sessionRunning)sessionElapsed else 0;Text((totalSeconds/60).toString()+" / 30 minutos");Text("Racha: "+streak+" días");LinearProgressIndicator(progress={(totalSeconds/N5_DAILY_STUDY_SECONDS.toFloat()).coerceIn(0f,1f)},modifier=Modifier.fillMaxWidth());Button(onClick=if(sessionRunning)stopSession else startSession,modifier=Modifier.fillMaxWidth()){Text(if(sessionRunning)"Finalizar sesión" else "Iniciar sesión de estudio")};Text("El tiempo se acumula mientras la sesión está activa, incluso si cambiás de pantalla.")}
BoxCard{Text("Plan de 12 semanas",style=MaterialTheme.typography.titleLarge);Text("Ruta N5 del PDF: 30 minutos diarios, pruebas semanales y repaso de errores.");Button(onClick=plan,modifier=Modifier.fillMaxWidth()){Text("Ver plan completo")}}
}}
@Composable private fun Study(ds:androidx.datastore.core.DataStore<Preferences>,language:String,done:Set<String>,mistakes:Set<String>,showRomaji:Boolean,m:Modifier){
val scope=rememberCoroutineScope()
var selected by remember{mutableStateOf<Lesson?>(null)}
var lang by remember(language){mutableStateOf(language)}
var picker by remember{mutableStateOf(false)}
var reviewing by remember{mutableStateOf(false)}
if(selected!=null){
 LessonPage(selected!!,showRomaji,{selected=null}){wrong,passed->
  scope.launch{ds.edit{p->
   p[mistakesKey]=(p[mistakesKey]?:emptySet())+wrong
   if(passed&&selected!!.id !in (p[doneKey]?:emptySet())){
    p[doneKey]=(p[doneKey]?:emptySet())+selected!!.id
    p[xpKey]=(p[xpKey]?:0)+20
   }
  }}
  selected=null
 }
 return
}
if(reviewing){
 MistakeReview(mistakes,{key->scope.launch{ds.edit{p->p[mistakesKey]=(p[mistakesKey]?:emptySet())-key}}},{reviewing=false})
 return
}
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
 Heading("Tu ruta de estudio","Elegí idioma, nivel y lección. Las lecciones publicadas funcionan sin conexión.")
 OutlinedButton(onClick={picker=true},modifier=Modifier.fillMaxWidth()){val l=languages.first{it.code==lang};Text(l.flag+" "+l.name+" · Cambiar idioma")}
 if(picker) AlertDialog(
  onDismissRequest={picker=false},
  title={Text("Elegí un idioma")},
  text={Column(Modifier.heightIn(max=400.dp).verticalScroll(rememberScrollState())){
   languages.forEach{item->TextButton(onClick={lang=item.code;picker=false;scope.launch{ds.edit{prefs->prefs[langKey]=item.code}}},modifier=Modifier.fillMaxWidth()){Text(item.flag+" "+item.name+" ("+item.native+")")}}
  }},
  confirmButton={TextButton(onClick={picker=false}){Text("Cerrar")}}
 )
 if(lang=="ja"){
  listOf("N5" to "Principiante","N4" to "Básico","N3" to "Intermedio","N2" to "Intermedio alto","N1" to "Avanzado").forEach{pair->
   BoxCard{
    Text(pair.first+" · "+pair.second,style=MaterialTheme.typography.titleLarge)
    val items=lessons.filter{it.level==pair.first}
    if(items.isEmpty())Text("Este nivel está previsto en la ruta, pero su catálogo todavía no está publicado.")
    items.forEach{l->OutlinedButton(onClick={selected=l},modifier=Modifier.fillMaxWidth()){Text((if(l.id in done)"✓ " else "")+l.title)}}
   }
  }
 }else BoxCard{
  Text("Ruta de "+languages.first{it.code==lang}.name,style=MaterialTheme.typography.titleLarge)
  Text("El catálogo de 15 idiomas está configurado. Esta versión publica únicamente lecciones japonesas; los demás cursos se incorporarán con contenido propio revisado.")
 }
 if(mistakes.isNotEmpty())BoxCard{
  Text("Cuaderno de errores",style=MaterialTheme.typography.titleLarge)
  Text("Tenés "+mistakes.size+" respuestas falladas guardadas para revisar.")
  Button(onClick={reviewing=true},modifier=Modifier.fillMaxWidth()){Text("Repasar mis errores")}
 }
}
}
@Composable private fun MistakeReview(mistakes:Set<String>,remove:(String)->Unit,back:()->Unit){
Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
 TextButton(onClick=back){Text("← Volver a las lecciones")}
 Heading("Cuaderno de errores","Revisá qué fallaste, cuál era la respuesta correcta y la explicación.")
 if(mistakes.isEmpty())Text("No tenés errores pendientes. ¡Buen trabajo!")
 mistakes.sorted().forEach{key->
  val split=key.lastIndexOf('-')
  val lessonId=if(split>0)key.substring(0,split) else ""
  val index=if(split>0)key.substring(split+1).toIntOrNull()?:-1 else -1
  val lesson=lessons.firstOrNull{it.id==lessonId}
  val exercise=lesson?.exercises?.getOrNull(index)
  if(lesson!=null&&exercise!=null)BoxCard{
   Text(lesson.level+" · "+lesson.title,color=MaterialTheme.colorScheme.primary)
   Text(exercise.prompt,style=MaterialTheme.typography.titleMedium)
   Text("Respuesta correcta: "+exercise.options[exercise.answer])
   Text(exercise.note,color=MaterialTheme.colorScheme.onSurfaceVariant)
   OutlinedButton(onClick={remove(key)},modifier=Modifier.fillMaxWidth()){Text("Marcar como repasado")}
  }
 }
}
}
@Composable private fun LessonPage(lesson:Lesson,showRomaji:Boolean,back:()->Unit,finish:(Set<String>,Boolean)->Unit){
var index by remember{mutableIntStateOf(0)}
var choice by remember{mutableIntStateOf(-1)}
var correct by remember{mutableIntStateOf(0)}
var answered by remember{mutableStateOf(false)}
val wrong=remember{mutableStateListOf<String>()}
val q=lesson.exercises[index]
Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
 TextButton(onClick=back){Text("← Volver")}
 Heading(lesson.title,lesson.level+" · Ejercicio "+(index+1)+" de "+lesson.exercises.size)
 LinearProgressIndicator(progress={(index+if(answered)1 else 0).toFloat()/lesson.exercises.size},modifier=Modifier.fillMaxWidth())
 lessonSupplements[lesson.id]?.let{content->
  BoxCard{
   Text("Objetivo",style=MaterialTheme.typography.titleMedium)
   Text(content.objective)
   Text("Gramática",style=MaterialTheme.typography.titleMedium)
   Text(content.grammar)
   Text("Vocabulario",style=MaterialTheme.typography.titleMedium)
   content.vocabulary.forEach{word->Text((if(showRomaji)word.first else word.second)+" — "+word.third)}
   Text("Ejemplos",style=MaterialTheme.typography.titleMedium)
   content.examples.forEach{example->Text(example)}
   Text("Prueba de unidad",style=MaterialTheme.typography.titleMedium)
   Text(content.unitAssessment)
   Text("Las escrituras japonesas son una incorporación preliminar y requieren revisión nativa antes de publicarse, tal como advierte el PDF.",color=MaterialTheme.colorScheme.onSurfaceVariant)
  }
 }
 BoxCard{
  Text(q.prompt,style=MaterialTheme.typography.titleLarge)
  q.options.forEachIndexed{i,s->OutlinedButton(onClick={if(!answered)choice=i},enabled=!answered,modifier=Modifier.fillMaxWidth()){Text((if(choice==i)"● " else "○ ")+s)}}
  if(answered){Text(if(choice==q.answer)"¡Correcto!" else "Respuesta: "+q.options[q.answer],color=MaterialTheme.colorScheme.primary);Text(q.note)}
  Button(onClick={
   if(!answered&&choice>=0){
    answered=true
    if(choice==q.answer)correct++ else wrong.add(lesson.id+"-"+index)
   }else if(answered){
    if(index==lesson.exercises.lastIndex)finish(wrong.toSet(),isLessonPassed(correct,lesson.exercises.size))
    else{index++;choice=-1;answered=false}
   }
  },enabled=answered||choice>=0,modifier=Modifier.fillMaxWidth()){Text(if(!answered)"Comprobar" else if(index==lesson.exercises.lastIndex)"Finalizar lección" else "Siguiente")}
  if(answered&&index==lesson.exercises.lastIndex){
   val percentage=correct*100/lesson.exercises.size
   Text("Resultado: "+percentage+"%. Se requiere 70% para completar.")
   if(percentage<70)Text("La lección no se marcará como completada. Tus errores quedarán guardados para repasarlos.")
  }
 }
}
}
@Composable private fun Exam(ds:androidx.datastore.core.DataStore<Preferences>,m:Modifier){
val scope=rememberCoroutineScope()
val bestScore by ds.data.map{it[bestExamKey]?:0}.collectAsState(initial=0)
var started by remember{mutableStateOf(false)}
var index by remember{mutableIntStateOf(0)}
var choice by remember{mutableIntStateOf(-1)}
var score by remember{mutableIntStateOf(0)}
var seconds by remember{mutableIntStateOf(600)}
var finished by remember{mutableStateOf(false)}
val sectionCorrect=remember{mutableStateMapOf<String,Int>()}
LaunchedEffect(started,finished){while(started&&!finished&&seconds>0){delay(1000);seconds--};if(seconds==0)finished=true}
LaunchedEffect(finished){
 if(finished){
  val percent=score*100/examQuestions.size
  ds.edit{prefs->if(percent>(prefs[bestExamKey]?:0))prefs[bestExamKey]=percent}
 }
}
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
 Heading("Exámenes","Práctica breve con resultados separados por sección.")
 if(!started)BoxCard{
  Text("Simulacro de práctica",style=MaterialTheme.typography.titleLarge)
  Text(examQuestions.size.toString()+" preguntas · 10 minutos · vocabulario, gramática, lectura y comprensión.")
  Text("Mejor resultado: "+bestScore+"%")
  Text("Este ejercicio es una práctica breve, no un examen oficial JLPT. Los tiempos y criterios oficiales deben verificarse en jlpt.jp.",color=MaterialTheme.colorScheme.onSurfaceVariant)
  Button(onClick={started=true},modifier=Modifier.fillMaxWidth()){Text("Comenzar examen")}
 }
 else if(finished)BoxCard{
  Text("Resultado",style=MaterialTheme.typography.headlineSmall)
  Text(score.toString()+" de "+examQuestions.size+" respuestas correctas · "+(score*100/examQuestions.size)+"%")
  examQuestions.map{it.section}.distinct().forEach{section->
   val total=examQuestions.count{it.section==section}
   val correctInSection=sectionCorrect[section]?:0
   Text(section+" · "+correctInSection+"/"+total+" correctas ("+(correctInSection*100/total)+"%)")
  }
  Button(onClick={started=false;finished=false;index=0;choice=-1;score=0;seconds=600;sectionCorrect.clear()},modifier=Modifier.fillMaxWidth()){Text("Repetir")}
 }
 else{
  val q=examQuestions[index]
  BoxCard{
   Text("Tiempo restante "+(seconds/60)+":"+ (seconds%60).toString().padStart(2,'0'))
   Text("Pregunta "+(index+1)+" de "+examQuestions.size)
   Text(q.section,color=MaterialTheme.colorScheme.primary)
   Text(q.prompt,style=MaterialTheme.typography.titleLarge)
   q.options.forEachIndexed{i,option->OutlinedButton(onClick={choice=i},enabled=choice<0,modifier=Modifier.fillMaxWidth()){Text((if(choice==i)"● " else "○ ")+option)}}
   Button(onClick={
    if(choice>=0){
     if(choice==q.answer){score++;sectionCorrect[q.section]=(sectionCorrect[q.section]?:0)+1}
     if(index==examQuestions.lastIndex)finished=true else{index++;choice=-1}
    }
   },enabled=choice>=0,modifier=Modifier.fillMaxWidth()){Text(if(index==examQuestions.lastIndex)"Terminar" else "Siguiente")}
  }
 }
}
}
@Composable private fun CollectionScreen(ds:androidx.datastore.core.DataStore<Preferences>,owned:Set<String>,dailySeconds:Int,m:Modifier){
val scope=rememberCoroutineScope();var message by remember{mutableStateOf("")}
val today=LocalDate.now().toString()
val claimedToday by ds.data.map{it[rewardKey]==today}.collectAsState(initial=false)
val goalReached=isDailyStudyGoalReached(dailySeconds)
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
Heading("Colección","Sobres diarios con tres cartas coleccionables.")
BoxCard{Text("Sobre diario",style=MaterialTheme.typography.titleLarge);Text("Podés reclamar un sobre por día.");Button(onClick={scope.launch{var got=false;ds.edit{p->if(p[rewardKey]!=today&&p[dailyDateKey]==today&&isDailyStudyGoalReached(p[dailySecondsKey]?:0)){p[rewardKey]=today;val picks=cards.keys.shuffled().take(3).toSet();p[cardsKey]=(p[cardsKey]?:emptySet())+picks;p[xpKey]=(p[xpKey]?:0)+10;got=true}};message=if(got)"¡Sobre abierto! Se agregaron tres cartas." else "Completá la meta diaria de 30 minutos para desbloquear el sobre."}},enabled=goalReached&&!claimedToday,modifier=Modifier.fillMaxWidth()){Text(if(claimedToday)"Sobre de hoy reclamado" else if(goalReached)"Abrir sobre" else "Desbloquear al completar 30 min")};if(message.isNotBlank())Text(message);if(!goalReached)Text("Progreso de hoy: "+(dailySeconds/60)+" / 30 minutos.")}
Text("Cartas obtenidas: "+owned.size,style=MaterialTheme.typography.titleMedium);cards.filterKeys{it in owned}.values.forEach{c->BoxCard{Text(c.first,style=MaterialTheme.typography.headlineMedium);Text(c.second)}};if(owned.isEmpty())Text("Todavía no tenés cartas.")
}}
@Composable private fun Profile(ds:androidx.datastore.core.DataStore<Preferences>,name:String,lang:String,m:Modifier){
val scope=rememberCoroutineScope();val dark by ds.data.map{it[darkKey]?:true}.collectAsState(initial=true);val showRomaji by ds.data.map{it[romajiKey]?:true}.collectAsState(initial=true);var edit by remember(name){mutableStateOf(name)}
Column(m.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){
Heading("Perfil y ajustes","Preferencias, privacidad y accesibilidad.")
BoxCard{Text("Tu perfil",style=MaterialTheme.typography.titleLarge);OutlinedTextField(value=edit,onValueChange={edit=it},label={Text("Nombre visible")},modifier=Modifier.fillMaxWidth());Button(onClick={scope.launch{ds.edit{it[nameKey]=edit.trim().ifBlank{"Estudiante"}}}}){Text("Guardar nombre")};Text("Idioma: "+languages.first{it.code==lang}.name)}
BoxCard{Text("Apariencia",style=MaterialTheme.typography.titleLarge);Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text(if(dark)"Tema oscuro" else "Tema claro");Switch(checked=dark,onCheckedChange={v->scope.launch{ds.edit{it[darkKey]=v}}})}}
BoxCard{Text("Lectura japonesa",style=MaterialTheme.typography.titleLarge);Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text(if(showRomaji)"Mostrar romaji" else "Mostrar kana");Switch(checked=showRomaji,onCheckedChange={v->scope.launch{ds.edit{it[romajiKey]=v}}})};Text("Alterná entre romaji y kana en el vocabulario de las unidades.")}
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
