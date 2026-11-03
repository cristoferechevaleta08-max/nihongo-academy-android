package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N4AssessmentTest {
    @Test fun integratedAssessmentHasThirtyQuestionsAcrossFourSections() {
        assertEquals(30, n4FinalAssessment.size)
        assertEquals(setOf("Gramática", "Vocabulario", "Lectura", "Comprensión"), n4FinalAssessment.map { it.section }.toSet())
        assertEquals(12, n4FinalAssessment.count { it.section == "Gramática" })
        assertEquals(8, n4FinalAssessment.count { it.section == "Vocabulario" })
        assertEquals(5, n4FinalAssessment.count { it.section == "Lectura" })
        assertEquals(5, n4FinalAssessment.count { it.section == "Comprensión" })
    }

    @Test fun everyQuestionHasValidAnswerAndExplanation() {
        n4FinalAssessment.forEach { question ->
            assertTrue(question.options.size >= 4)
            assertTrue(question.answer in question.options.indices)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.explanation.isNotBlank())
        }
    }
}
