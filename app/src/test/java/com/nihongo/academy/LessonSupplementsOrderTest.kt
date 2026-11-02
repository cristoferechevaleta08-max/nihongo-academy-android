package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LessonSupplementsOrderTest {
    @Test
    fun catalogKeepsPdfSequenceFromN5UnitOneThroughN4UnitEight() {
        val expectedKeys = (1..10).map { "n5-u%02d".format(it) } +
            (1..8).map { "n4-u%02d".format(it) }

        assertEquals(expectedKeys, lessonSupplements.keys.toList())
    }

    @Test
    fun everyPdfCatalogUnitHasItsRequiredLearningSections() {
        assertEquals(18, lessonSupplements.size)
        lessonSupplements.forEach { (key, lesson) ->
            assertTrue("$key objective", lesson.objective.isNotBlank())
            assertTrue("$key grammar", lesson.grammar.isNotBlank())
            assertFalse("$key vocabulary", lesson.vocabulary.isEmpty())
            assertFalse("$key examples", lesson.examples.isEmpty())
            assertTrue("$key assessment", lesson.unitAssessment.isNotBlank())
        }
    }

    @Test
    fun n5UnitsSevenThroughTenStayInThePdfOrder() {
        val expected = listOf(
            "Decir números, horas y días de la semana.",
            "Nombrar lugares y preguntar dónde están.",
            "Pedir a alguien que haga algo con ~te kudasai.",
            "Decir qué hay y dónde está algo o alguien."
        )
        val actual = (7..10).map { lessonSupplements.getValue("n5-u%02d".format(it)).objective }

        assertEquals(expected, actual)
    }

    @Test
    fun n4CatalogStartsOnlyAfterAllTenN5Units() {
        val keys = lessonSupplements.keys.toList()
        assertEquals("n5-u10", keys[9])
        assertEquals("n4-u01", keys[10])
        assertEquals("n4-u08", keys.last())
    }
}
