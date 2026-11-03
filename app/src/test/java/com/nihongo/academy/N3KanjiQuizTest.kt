package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class N3KanjiQuizTest {
    @Test fun quizHasTenQuestionsAcrossMeaningAndReading() {
        val quiz = buildN3KanjiQuiz(random = Random(42))
        assertEquals(10, quiz.size)
        assertEquals(6, quiz.count { it.type == N3KanjiQuestionType.MEANING })
        assertEquals(4, quiz.count { it.type == N3KanjiQuestionType.READING })
    }

    @Test fun everyQuestionHasFourUniqueOptionsAndAnExplanation() {
        buildN3KanjiQuiz(random = Random(7)).forEach { question ->
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.display.isNotBlank())
            assertEquals(4, question.options.size)
            assertEquals(4, question.options.toSet().size)
            assertTrue(question.answer in question.options)
            assertTrue(question.explanation.isNotBlank())
        }
    }

    @Test fun readingAnswersDoNotContainDictionarySeparators() {
        buildN3KanjiQuiz(random = Random(19))
            .filter { it.type == N3KanjiQuestionType.READING }
            .forEach { question ->
                assertTrue(!question.answer.contains("."))
                assertTrue(question.options.none { it.contains(".") })
            }
    }

    @Test fun quizCanBeGeneratedRepeatedly() {
        repeat(30) { seed ->
            val quiz = buildN3KanjiQuiz(random = Random(seed))
            assertEquals(10, quiz.size)
            assertTrue(quiz.all { it.options.size == 4 && it.answer in it.options })
        }
    }
}
