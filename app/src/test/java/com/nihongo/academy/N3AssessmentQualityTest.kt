package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N3AssessmentQualityTest {
    @Test fun allEightGrammarWeeksHaveBalancedAnswerPositions() {
        val assessments = (1..8).map { n3GrammarAssessmentForWeek(it) }
        assessments.forEachIndexed { index, questions ->
            assertEquals("Semana ${index + 1}: cantidad de preguntas", 12, questions.size)
            assertTrue(
                "Semana ${index + 1}: las respuestas correctas deben variar de posición",
                questions.map { it.answerIndex }.toSet().size >= 3
            )
            questions.forEach { question ->
                assertEquals(4, question.options.size)
                assertTrue(question.answerIndex in question.options.indices)
            }
        }
    }
}
