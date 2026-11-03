package com.nihongo.academy

import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N3KanjiReviewTest {
    private val today = LocalDate.of(2026, 11, 3)

    @Test fun unseenKanjiAreDueImmediately() {
        val due = n3KanjiDueForReview(n3KanjiCatalog, emptySet(), today)
        assertEquals(n3KanjiCatalog.size, due.size)
    }

    @Test fun goodAndEasyScheduleIncreasingIntervals() {
        val good = parseN3ReviewRecords(scheduleN3KanjiReview(emptySet(), "政", N3ReviewRating.GOOD, today)).getValue("政")
        val easy = parseN3ReviewRecords(scheduleN3KanjiReview(emptySet(), "議", N3ReviewRating.EASY, today)).getValue("議")
        assertEquals(3, good.intervalDays)
        assertEquals(today.plusDays(3), good.dueDate)
        assertEquals(7, easy.intervalDays)
        assertEquals(today.plusDays(7), easy.dueDate)
    }

    @Test fun hardReviewShortensPreviousInterval() {
        val first = scheduleN3KanjiReview(emptySet(), "政", N3ReviewRating.EASY, today)
        val second = scheduleN3KanjiReview(first, "政", N3ReviewRating.HARD, today)
        assertEquals(3, parseN3ReviewRecords(second).getValue("政").intervalDays)
    }

    @Test fun futureReviewsAreHiddenUntilDueDate() {
        val stored = scheduleN3KanjiReview(emptySet(), "政", N3ReviewRating.GOOD, today)
        val dueToday = n3KanjiDueForReview(n3KanjiCatalog, stored, today)
        assertTrue(dueToday.none { it.character == "政" })
        val dueLater = n3KanjiDueForReview(n3KanjiCatalog, stored, today.plusDays(3))
        assertTrue(dueLater.any { it.character == "政" })
    }

    @Test fun kunyomiDisplayRemovesDictionaryNotationMarkers() {
        assertEquals("つらなる", displayN3KunYomi("つら.なる"))
        assertEquals("づれ", displayN3KunYomi("-づ.れ"))
        assertEquals("あい", displayN3KunYomi("あい-"))
    }

    @Test fun malformedStoredRecordsAreIgnored() {
        assertTrue(parseN3ReviewRecords(setOf("政|bad|3", "議|1|0", "broken")).isEmpty())
    }

    @Test fun reviewingMarksKanjiAsLearnedWithoutLosingExistingProgress() {
        val existing = setOf("政", "議")
        val updated = markN3KanjiLearned(existing, "連")
        assertEquals(setOf("政", "議", "連"), updated)
        assertEquals(updated, markN3KanjiLearned(updated, "連"))
    }

}
