package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N3AppearanceInformationProbabilityTest {
    @Test fun eighthGrammarWeekHasSevenOrderedLessons() {
        assertEquals(7, n3AppearanceInformationProbabilityDays.size)
        assertEquals((1..7).toList(), n3AppearanceInformationProbabilityDays.map { it.day })
        assertTrue(n3AppearanceInformationProbabilityDays.all {
            it.title.isNotBlank() && it.focus.isNotBlank() && it.practice.size >= 4
        })
    }

    @Test fun lessonsCoverAppearanceHearsayInferenceAndExpectation() {
        val text = n3AppearanceInformationProbabilityDays.joinToString(" ") {
            it.title + " " + it.focus + " " + it.practice.joinToString(" ")
        }
        listOf("そうだ", "ようだ", "みたいだ", "らしい", "はずだ", "はずがない",
            "おいしそう", "雨が降るそう", "子どもらしい").forEach { term ->
            assertTrue("Missing appearance/inference content for $term", text.contains(term))
        }
    }

    @Test fun assessmentHasTwelveValidQuestions() {
        assertEquals(12, n3AppearanceInformationProbabilityAssessment.size)
        n3AppearanceInformationProbabilityAssessment.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.answerIndex in question.options.indices)
            assertFalse(question.prompt.isBlank())
        }
    }

    @Test fun eighthWeekRoutesCorrectlyAndNinthWeekRemainsEmpty() {
        assertEquals(n3AppearanceInformationProbabilityDays, n3GrammarDaysForWeek(8))
        assertEquals(n3AppearanceInformationProbabilityAssessment, n3GrammarAssessmentForWeek(8))
        assertTrue(n3GrammarDaysForWeek(9).isEmpty())
        assertTrue(n3GrammarAssessmentForWeek(9).isEmpty())
    }
}
