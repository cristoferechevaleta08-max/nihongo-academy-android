package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N5LessonAuditTest {
    @Test
    fun allTenN5LessonsContainFiveToEightExercises() {
        val n5 = lessons.filter { it.level == "N5" }
        assertEquals(10, n5.size)
        n5.forEach { lesson ->
            assertTrue("${lesson.id} must have 5–8 exercises", lesson.exercises.size in 5..8)
            lesson.exercises.forEachIndexed { index, exercise ->
                assertTrue("${lesson.id} exercise $index needs options", exercise.options.size >= 2)
                assertTrue("${lesson.id} exercise $index answer index", exercise.answer in exercise.options.indices)
            }
        }
    }

    @Test
    fun lessonsUnlockOnlyAfterTheImmediatelyPreviousLessonIsCompleted() {
        assertTrue(isLessonUnlocked("n5-u01", emptySet()))
        assertFalse(isLessonUnlocked("n5-u02", emptySet()))
        assertTrue(isLessonUnlocked("n5-u02", setOf("n5-u01")))
        assertFalse(isLessonUnlocked("n5-u03", setOf("n5-u01")))
        assertTrue(isLessonUnlocked("n5-u03", setOf("n5-u01", "n5-u02")))
        assertFalse(isLessonUnlocked("n4-u01", (1..9).map { "n5-u%02d".format(it) }.toSet()))
        assertTrue(isLessonUnlocked("n4-u01", (1..10).map { "n5-u%02d".format(it) }.toSet()))
    }

    @Test
    fun kanaAndKanjiRemainHiddenUntilNativeReviewIsRecorded() {
        assertTrue(lessonSupplements.keys.filter { it.startsWith("n5-") }.size == 10)
        lessonSupplements.filterKeys { it.startsWith("n5-") }.forEach { (id, lesson) ->
            assertFalse("$id must not publish unreviewed Japanese scripts", lesson.kanaReviewed)
        }
    }

    @Test
    fun n5SourceExamplesArePresentedInRomajiAsSpecifiedByThePdf() {
        assertTrue(lessonSupplements.getValue("n5-u01").examples.first().startsWith("Watashi wa Sakura desu."))
        assertTrue(lessonSupplements.getValue("n5-u10").examples.first().startsWith("Tsukue no ue ni hon ga arimasu."))
    }
}
