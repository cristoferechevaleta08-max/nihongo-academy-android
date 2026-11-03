package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N3StudyPlanTest {
    @Test fun planHasSixteenOrderedWeeksWithCompleteContent() {
        assertEquals(N3_PLAN_WEEKS, n3SixteenWeekPlan.size)
        assertEquals((1..16).toList(), n3SixteenWeekPlan.map { it.week })
        n3SixteenWeekPlan.forEach { week ->
            assertTrue("week ${week.week} title", week.title.isNotBlank())
            assertTrue("week ${week.week} focus", week.focus.isNotBlank())
            assertTrue("week ${week.week} activities", week.activities.size >= 3)
            assertTrue("week ${week.week} assessment", week.assessment.isNotBlank())
        }
    }

    @Test fun planMovesFromGrammarToKanjiListeningReadingAndIntegration() {
        assertTrue(n3SixteenWeekPlan[0].title.contains("Voz pasiva"))
        assertTrue(n3SixteenWeekPlan[3].title.contains("Condiciones"))
        assertTrue(n3SixteenWeekPlan.slice(8..10).all { it.title.contains("Kanji") })
        assertTrue(n3SixteenWeekPlan[11].title.contains("auditiva"))
        assertTrue(n3SixteenWeekPlan[12].title.contains("lectora"))
        assertTrue(n3SixteenWeekPlan[14].title.contains("Simulacro"))
        assertTrue(n3SixteenWeekPlan.last().title.contains("final", ignoreCase = true))
    }

    @Test fun planUsesConsistentStudyTargets() {
        assertEquals(16, N3_PLAN_WEEKS)
        assertEquals(45, N3_DAILY_TARGET_MINUTES)
        assertEquals(70, N3_WEEKLY_PASS_PERCENT)
        assertFalse(n3SixteenWeekPlan.any { it.activities.isEmpty() })
    }
}
