package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class N3KanjiStrokeOrderTest {
    @Test fun decodesEveryNonEmptyStrokePath() {
        val paths = decodeN3KanjiStrokePaths(
            """{"strokes":["M 0 0 L 10 10","M 10 0 L 0 10"]}"""
        )
        assertEquals(listOf("M 0 0 L 10 10", "M 10 0 L 0 10"), paths)
    }

    @Test fun rejectsMissingStrokesArray() {
        assertThrows(Exception::class.java) {
            decodeN3KanjiStrokePaths("""{"other":[]}""")
        }
    }

    @Test fun rejectsEmptyStrokesArray() {
        assertThrows(IllegalArgumentException::class.java) {
            decodeN3KanjiStrokePaths("""{"strokes":[]}""")
        }
    }

    @Test fun rejectsBlankStrokePathInsteadOfReturningPartialGuide() {
        assertThrows(IllegalArgumentException::class.java) {
            decodeN3KanjiStrokePaths("""{"strokes":["M 0 0 L 10 10","   "]}""")
        }
    }
}
