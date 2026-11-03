package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N4ListeningPracticeTest {
    @Test fun sixDialoguesHaveFourLinesAndThreeQuestionsEach() {
        assertEquals(6, n4ListeningDialogues.size)
        assertEquals(6, n4ListeningDialogues.map { it.id }.distinct().size)
        n4ListeningDialogues.forEach { dialogue ->
            assertEquals(4, dialogue.lines.size)
            assertEquals(3, dialogue.questions.size)
            dialogue.lines.forEach { line ->
                assertTrue(line.japanese.any { it in '\u3040'..'\u30ff' || it in '\u4e00'..'\u9fff' })
                assertTrue(line.spanish.isNotBlank())
                assertTrue(line.reading.isNotBlank())
            }
            dialogue.questions.forEach { question ->
                assertTrue(question.answer in question.options.indices)
                assertTrue(question.explanation.isNotBlank())
            }
        }
    }

    @Test fun threeReadingPassagesHaveNineQuestions() {
        assertEquals(3, n4ReadingPassages.size)
        assertEquals(9, n4ReadingPassages.sumOf { it.questions.size })
        n4ReadingPassages.forEach { passage ->
            assertTrue(passage.japanese.isNotBlank())
            assertTrue(passage.reading.isNotBlank())
            assertTrue(passage.spanish.isNotBlank())
            passage.questions.forEach { question ->
                assertTrue(question.answer in question.options.indices)
                assertTrue(question.explanation.isNotBlank())
            }
        }
    }
}
