package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class N4KanjiQuizTest {
    @Test fun quizHasTenQuestionsAcrossThreeLearningSkills() {
        val quiz = buildN4KanjiQuiz(random = Random(42))
        assertEquals(10, quiz.size)
        assertEquals(4, quiz.count { it.type == N4KanjiQuestionType.MEANING })
        assertEquals(3, quiz.count { it.type == N4KanjiQuestionType.READING })
        assertEquals(3, quiz.count { it.type == N4KanjiQuestionType.VOCABULARY })
    }

    @Test fun everyQuestionHasFourUniqueOptionsAndAnExplanation() {
        buildN4KanjiQuiz(random = Random(7)).forEach { question ->
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.display.isNotBlank())
            assertEquals(4, question.options.size)
            assertEquals(4, question.options.toSet().size)
            assertTrue(question.answer in question.options)
            assertTrue(question.explanation.isNotBlank())
        }
    }

    @Test fun readingAnswersDoNotExposeDictionaryOkuriganaSeparators() {
        buildN4KanjiQuiz(random = Random(19))
            .filter { it.type == N4KanjiQuestionType.READING }
            .forEach { question ->
                assertTrue(!question.answer.contains("."))
                assertTrue(question.options.none { it.contains(".") })
            }
    }

    @Test fun quizCanBeGeneratedRepeatedlyWithoutInvalidAnswers() {
        repeat(30) { seed ->
            val quiz = buildN4KanjiQuiz(random = Random(seed))
            assertEquals(10, quiz.size)
            assertTrue(quiz.all { it.options.size == 4 && it.answer in it.options })
        }
    }
}
