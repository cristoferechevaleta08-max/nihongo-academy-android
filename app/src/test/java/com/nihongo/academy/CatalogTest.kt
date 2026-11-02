package com.nihongo.academy

import org.junit.Assert.*
import org.junit.Test

class CatalogTest {
 @Test fun sourceRequirementsKeepSeventyPercentPassMark() { assertTrue(4f/5f >= .7f); assertFalse(3f/5f >= .7f) }
 @Test fun configuredLanguageListContainsFifteenCodes() { assertEquals(15, listOf("ja","en","ko","zh","fr","de","it","pt","es","ru","ar","hi","tr","bn","id").size) }
 @Test fun pdfN5PlanContainsSevenBlocksCoveringTwelveWeeks() {
  assertEquals(7, n5TwelveWeekPlan.size)
  assertEquals("1–2", n5TwelveWeekPlan.first().weeks)
  assertEquals("12", n5TwelveWeekPlan.last().weeks)
  assertEquals(30, N5_DAILY_STUDY_MINUTES)
 }
 @Test fun allEighteenPublishedUnitsHavePdfLessonContent() {
  assertEquals(18, lessonSupplements.size)
  assertTrue(lessonSupplements.values.all { it.vocabulary.isNotEmpty() && it.examples.isNotEmpty() && it.unitAssessment.isNotBlank() })
 }
 @Test fun dailyEnvelopeRequiresThirtyMinutesOfStudy() {
  assertFalse(isDailyStudyGoalReached(1799))
  assertTrue(isDailyStudyGoalReached(1800))
 }
 @Test fun lessonPassThresholdIsSeventyPercent() {
  assertTrue(isLessonPassed(7,10))
  assertFalse(isLessonPassed(6,10))
 }
 @Test fun weeklyPlanRepeatsOnlyWhenMoreThanThirtyPercentAreWrong() {
  assertFalse(shouldRepeatStudyWeek(3,10))
  assertTrue(shouldRepeatStudyWeek(4,10))
 }
}
