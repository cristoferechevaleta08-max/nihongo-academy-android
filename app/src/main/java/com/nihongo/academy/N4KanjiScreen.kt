package com.nihongo.academy

import androidx.compose.material.icons.outlined.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val n4KanjiLearnedKey=stringSetPreferencesKey("n4_kanji_learned")
private val n4KanjiBestExamKey=intPreferencesKey("n4_kanji_best_exam")
private fun n4KanjiSrsKey(c:String)=stringPreferencesKey("n4_kanji_srs_$c")

@Composable
internal fun N4KanjiScreen(dataStore:DataStore<Preferences>,modifier:Modifier=Modifier){
 val scope= rememberCoroutineScope()
 val learned by dataStore.data.map{it[n4KanjiLearnedKey]?:emptySet()}.collectAsState(initial=emptySet())
 val best by dataStore.data.map{it[n4KanjiBestExamKey]?:0}.collectAsState(initial=0)
 val states by dataStore.data.map{p->n4KanjiCatalog.mapNotNull{e->p[n4KanjiSrsKey(e.character)]?.let{v->KanjiReviewState.decode(v)?.let{e.character to it}}}.toMap()}.collectAsState(initial=emptyMap())
 var query by remember{mutableStateOf("")}
 var selected by remember{mutableStateOf<N4KanjiEntry?>(null)}
 var queue by remember{mutableStateOf<List<N4KanjiEntry>>(emptyList())}
 var reviewIndex by remember{mutableIntStateOf(0)}
 var answer by remember{mutableStateOf<String?>(null)}
 var revealed by remember{mutableStateOf(false)}
 var exam by remember{mutableStateOf<List<N4KanjiQuizQuestion>>(emptyList())}
 var examIndex by remember{mutableIntStateOf(0)}
 var examScore by remember{mutableIntStateOf(0)}
 var examChoice by remember{mutableStateOf<String?>(null)}
 var examDone by remember{mutableStateOf(false)}
 val now=System.currentTimeMillis()
 val due=n4KanjiCatalog.filter{states[it.character]?.nextReviewAt?.let{x->x<=now} ?: (it.character !in learned)}
 if(exam.isNotEmpty()){
  if(examDone){
   Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),verticalArrangement=Arrangement.spacedBy(14.dp),horizontalAlignment=Alignment.CenterHorizontally){
    Text("Evaluación de kanji N4",style=MaterialTheme.typography.headlineSmall)
    Text("$examScore / ${exam.size}",style=MaterialTheme.typography.displayMedium)
    Text(if(examScore>=8)"¡Buen trabajo! Sigue repasando." else "Revisa las fichas y vuelve a intentarlo.")
    Text("Mejor resultado: $best / 10")
    Button(onClick={exam=emptyList();examDone=false}){Text("Volver a la biblioteca")}
   }
  }else{
   val e=exam[examIndex]
   Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){
    TextButton(onClick={exam=emptyList();examDone=false}){Text("← Salir")}
    Text("Pregunta ${examIndex+1} de ${exam.size} · N4")
    LinearProgressIndicator(progress={(examIndex+1).toFloat()/exam.size},modifier=Modifier.fillMaxWidth())
    Text(e.display,style=MaterialTheme.typography.displayLarge)
    Text(e.prompt)
    e.options.forEach{o->OutlinedButton(onClick={examChoice=o},enabled=examChoice==null,modifier=Modifier.fillMaxWidth()){Text(o)}}
    if(examChoice!=null){
     Text(if(examChoice==e.answer)"¡Correcto!" else "Respuesta correcta: ${e.answer}",color=MaterialTheme.colorScheme.primary)
     Text(e.explanation,color=MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Button(enabled=examChoice!=null,onClick={
     val score=examScore+if(examChoice==e.answer)1 else 0
     examScore=score;examChoice=null
     if(examIndex==exam.lastIndex){examDone=true;scope.launch{dataStore.edit{p->p[n4KanjiBestExamKey]=maxOf(p[n4KanjiBestExamKey]?:0,score)}}}
     else examIndex++
    },modifier=Modifier.fillMaxWidth()){Text(if(examIndex==exam.lastIndex)"Finalizar" else "Siguiente")}
   }
  }
  return
 }
 if(queue.isNotEmpty()){
  val e=queue[reviewIndex.coerceAtMost(queue.lastIndex)]
  val options=remember(e.character){(n4KanjiCatalog.filter{it.character!=e.character}.shuffled().take(3).map{it.meaningEs}+e.meaningEs).shuffled()}
  Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){
   TextButton(onClick={queue=emptyList();revealed=false;answer=null}){Text("← Salir del repaso")}
   Text("Repaso N4 · ${reviewIndex+1} de ${queue.size}")
   Text(e.character,style=MaterialTheme.typography.displayLarge)
   Text("¿Qué significa este kanji?")
   options.forEach{o->OutlinedButton(onClick={if(!revealed)answer=o},enabled=!revealed,modifier=Modifier.fillMaxWidth()){Text(o)}}
   if(revealed){
    Text(if(answer==e.meaningEs)"¡Correcto!" else "Respuesta: ${e.meaningEs}")
    Text("¿Qué tan bien lo recordaste?")
    listOf(KanjiReviewGrade.AGAIN to "Otra vez",KanjiReviewGrade.HARD to "Difícil",KanjiReviewGrade.GOOD to "Bien",KanjiReviewGrade.EASY to "Fácil").forEach{(grade,label)->
     OutlinedButton(onClick={
      scope.launch{val previous=states[e.character]?:KanjiReviewState.initial;val next=scheduleKanjiReview(previous,grade,System.currentTimeMillis());dataStore.edit{it[n4KanjiSrsKey(e.character)]=next.encode()}}
      if(reviewIndex<queue.lastIndex){reviewIndex++;answer=null;revealed=false}else{queue=emptyList();answer=null;revealed=false}
     },modifier=Modifier.fillMaxWidth()){Text(label)}
    }
   }else Button(onClick={revealed=true},enabled=answer!=null,modifier=Modifier.fillMaxWidth()){Text("Comprobar")}
  }
  return
 }
 if(selected!=null){
  val e=selected!!
  Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){
   TextButton(onClick={selected=null}){Text("← Biblioteca N4")}
   Text("N4 · Kanji",color=MaterialTheme.colorScheme.primary)
   Text(e.character,style=MaterialTheme.typography.displayLarge)
   Text(e.meaningEs,style=MaterialTheme.typography.headlineSmall)
   Text("Trazos: ${e.strokeCount}")
   N4KanjiStrokeOrder(e.character)
   HorizontalDivider()
   Text("Lectura On'yomi",style=MaterialTheme.typography.titleMedium)
   Text(e.onyomi.joinToString(" · ").ifBlank{"No incluida en esta ficha"})
   Text("Lectura Kun'yomi",style=MaterialTheme.typography.titleMedium)
   Text(e.kunyomi.joinToString(" · ").ifBlank{"No incluida en esta ficha"})
   HorizontalDivider()
   Text("Vocabulario",style=MaterialTheme.typography.titleMedium)
   e.vocabulary.forEach{w->Card(Modifier.fillMaxWidth()){Column(Modifier.padding(14.dp)){Text(w.word,style=MaterialTheme.typography.titleLarge);Text(w.reading,color=MaterialTheme.colorScheme.primary);Text(w.meaningEs)}}}
   Text("Ejemplo",style=MaterialTheme.typography.titleMedium)
   Text(e.exampleJa,style=MaterialTheme.typography.titleLarge)
   Text(e.exampleReading,color=MaterialTheme.colorScheme.primary)
   Text(e.exampleEs)
   Button(onClick={scope.launch{dataStore.edit{p->val old=p[n4KanjiLearnedKey]?:emptySet();p[n4KanjiLearnedKey]=if(e.character in old)old-e.character else old+e.character}}},modifier=Modifier.fillMaxWidth()){Text(if(e.character in learned)"Marcar pendiente" else "Marcar aprendido")}
   OutlinedButton(onClick={queue=listOf(e);reviewIndex=0;answer=null;revealed=false},modifier=Modifier.fillMaxWidth()){Text("Practicar este kanji")}
  }
  return
 }
 val filtered=n4KanjiCatalog.filter{it.character.contains(query)||it.meaningEs.contains(query,true)||it.vocabulary.any{w->w.word.contains(query)||w.meaningEs.contains(query,true)}}
 Column(modifier.fillMaxSize().padding(horizontal=16.dp,vertical=12.dp),verticalArrangement=Arrangement.spacedBy(10.dp)){
  Text("Biblioteca de kanji",style=MaterialTheme.typography.headlineMedium)
  Text("N4 · 181 kanjis para estudiar")
  Text("Selección pedagógica propia; el JLPT no publica una lista oficial y cerrada de kanji por nivel.",style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant)
  OutlinedTextField(value=query,onValueChange={query=it},label={Text("Buscar kanji o significado")},modifier=Modifier.fillMaxWidth(),singleLine=true)
  Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){
   AssistChip(onClick={queue=due;reviewIndex=0;answer=null;revealed=false},label={Text("Repasar pendientes: ${due.size}")},enabled=due.isNotEmpty())
   AssistChip(onClick={exam=buildN4KanjiQuiz();examIndex=0;examScore=0;examChoice=null;examDone=false},label={Text("Examen · 10 preguntas")})
  }
  Text("Aprendidos: ${learned.intersect(n4KanjiCatalog.map{it.character}.toSet()).size} / ${n4KanjiCatalog.size} · Mejor resultado: $best / 10")
  LazyColumn(verticalArrangement=Arrangement.spacedBy(8.dp),contentPadding=PaddingValues(bottom=12.dp)){
   if(filtered.isEmpty()) item {
    Column(Modifier.fillMaxWidth().padding(28.dp),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.spacedBy(10.dp)){
     androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.Outlined.Search,contentDescription=null,tint=MaterialTheme.colorScheme.primary)
     Text("No se encontraron kanji",style=MaterialTheme.typography.titleMedium)
     Text("Prueba con otro término o limpia la búsqueda.",color=MaterialTheme.colorScheme.onSurfaceVariant)
     OutlinedButton(onClick={query=""}){Text("Mostrar todos los kanji")}
    }
   }
   items(filtered,key={it.character}){e->
    Card(Modifier.fillMaxWidth().clickable{selected=e},shape=RoundedCornerShape(16.dp)){
     Row(Modifier.fillMaxWidth().padding(14.dp),verticalAlignment=Alignment.CenterVertically,horizontalArrangement=Arrangement.spacedBy(14.dp)){
      Box(Modifier.width(64.dp).heightIn(min=64.dp),contentAlignment=Alignment.Center){Text(e.character,style=MaterialTheme.typography.headlineLarge,maxLines=1)}
      Column(Modifier.weight(1f)){Text(e.meaningEs,style=MaterialTheme.typography.titleMedium);Text(e.vocabulary.first().word+" · "+e.vocabulary.first().reading,color=MaterialTheme.colorScheme.onSurfaceVariant)}
      if(e.character in learned)Text("✓",color=MaterialTheme.colorScheme.primary)
     }
    }
   }
  }
 }
}
