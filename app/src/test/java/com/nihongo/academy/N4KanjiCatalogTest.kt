package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N4KanjiCatalogTest {
 @Test fun catalogContainsOneHundredEightyOneUniqueCuratedCharacters(){
  val chars=n4KanjiCatalog.map{it.character}
  assertEquals(181,chars.size)
  assertEquals(chars.size,chars.toSet().size)
 }
 @Test fun n4CatalogDoesNotDuplicateTheN5KanjiCatalog(){
  val n5=n5KanjiCatalog.map{it.character}.toSet()
  assertTrue(n4KanjiCatalog.none{it.character in n5})
 }
 @Test fun everyEntryHasCompleteCoreLearningContent(){
  n4KanjiCatalog.forEach{e->
   assertTrue(e.character.isNotBlank())
   assertEquals(1,e.character.codePointCount(0,e.character.length))
   assertTrue(e.meaningEs.isNotBlank())
   assertTrue(e.strokeCount>0)
   assertTrue(e.onyomi.isNotEmpty()||e.kunyomi.isNotEmpty())
   assertTrue(e.vocabulary.isNotEmpty())
   e.vocabulary.forEach{w->assertTrue(w.word.isNotBlank());assertTrue(w.reading.isNotBlank());assertTrue(w.meaningEs.isNotBlank())}
   assertTrue(e.exampleJa.isNotBlank())
   assertTrue(e.exampleReading.isNotBlank())
   assertTrue(e.exampleEs.isNotBlank())
  }
 }
 @Test fun everyN4KanjiHasOfflineStrokePathsMatchingItsStrokeCount(){
  assertEquals(181,n4KanjiStrokePaths.size)
  n4KanjiCatalog.forEach{entry->
   val strokes=n4KanjiStrokePaths[entry.character]
   assertTrue("Faltan rutas para ${entry.character}",!strokes.isNullOrEmpty())
   assertEquals("Cantidad de trazos incorrecta para ${entry.character}",entry.strokeCount,strokes!!.size)
   assertTrue(strokes.all{it.startsWith("M")&&it.length>5})
  }
 }

 @Test fun reviewSchedulerProducesValidFutureIntervals(){
  val first=scheduleKanjiReview(KanjiReviewState.initial,KanjiReviewGrade.GOOD,1_000L)
  assertEquals(1,first.intervalDays)
  assertTrue(first.nextReviewAt>1_000L)
  val again=scheduleKanjiReview(first,KanjiReviewGrade.AGAIN,1_000L)
  assertEquals(0,again.intervalDays)
  assertTrue(again.nextReviewAt>1_000L)
 }
}
