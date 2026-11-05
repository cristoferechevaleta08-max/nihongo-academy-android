package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class N2KanjiStrokeOrderTest {
    @Test
    fun decodesEveryNonEmptyStrokePath() {
        val paths = decodeN2KanjiStrokePaths(
            """{"strokes":["M 0 0 L 10 10","M 10 0 L 0 10"]}"""
        )

        assertEquals(2, paths.size)
        assertEquals("M 0 0 L 10 10", paths[0])
        assertEquals("M 10 0 L 0 10", paths[1])
    }

    @Test
    fun rejectsMissingStrokesArray() {
        assertThrows(Exception::class.java) {
            decodeN2KanjiStrokePaths("""{"other":[]}""")
        }
    }

    @Test
    fun rejectsEmptyStrokesArray() {
        assertThrows(IllegalArgumentException::class.java) {
            decodeN2KanjiStrokePaths("""{"strokes":[]}""")
        }
    }

    @Test
    fun rejectsBlankStrokePathInsteadOfReturningPartialGuide() {
        assertThrows(IllegalArgumentException::class.java) {
            decodeN2KanjiStrokePaths("""{"strokes":["M 0 0 L 10 10","   "]}""")
        }
    }
}
