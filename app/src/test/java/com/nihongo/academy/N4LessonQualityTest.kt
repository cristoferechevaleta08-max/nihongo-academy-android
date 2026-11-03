package com.nihongo.academy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N4LessonQualityTest {
    private val n4 = (1..8).map { "n4-u%02d".format(it) }

    @Test
    fun allEightN4UnitsHaveExpandedLearningMaterial() {
        n4.forEach { id ->
            val lesson = lessonSupplements.getValue(id)
            assertTrue("$id objective", lesson.objective.isNotBlank())
            assertTrue("$id grammar", lesson.grammar.length >= 180)
            assertTrue("$id vocabulary", lesson.vocabulary.size >= 6)
            assertTrue("$id examples", lesson.examples.size >= 3)
            assertTrue("$id assessment", lesson.unitAssessment.isNotBlank())
        }
    }

    @Test
    fun potentialLessonSeparatesAbilityFromPermission() {
        val lesson = lessonSupplements.getValue("n4-u03")
        assertTrue(lesson.grammar.contains("hanasu → hanaseru"))
        assertTrue(lesson.grammar.contains("~te mo ii desu"))
        assertTrue(lesson.examples.any { it.contains("にほんごをはなせます") })
        assertFalse(lesson.examples.any { it.contains("すしをたべてもいいです") })
    }

    @Test
    fun naiAndConditionalLessonsExplainTheirFormationAndLimits() {
        val negative = lessonSupplements.getValue("n4-u02").grammar
        val conditional = lessonSupplements.getValue("n4-u08").grammar
        assertTrue(negative.contains("au → awanai"))
        assertTrue(negative.contains("shinai"))
        assertTrue(conditional.contains("pasado informal + ら"))
        assertTrue(conditional.contains("orden, invitación o voluntad"))
    }

    @Test
    fun allN4LessonsContainPracticalJapaneseExamples() {
        n4.forEach { id ->
            val lesson = lessonSupplements.getValue(id)
            assertTrue("$id has Japanese script", lesson.examples.any { it.any { ch -> ch in '\u3040'..'\u30ff' } })
        }
    }
}
