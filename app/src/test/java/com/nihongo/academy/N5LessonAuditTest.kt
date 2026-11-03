package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N5LessonAuditTest {
    @Test
    fun allTenN5LessonsContainFiveToEightExercises() {
        val n5 = lessons.filter { it.level == "N5" }
        assertEquals(10, n5.size)
        n5.forEach { lesson ->
            assertTrue("${lesson.id} must have 5–8 exercises", lesson.exercises.size in 5..8)
            lesson.exercises.forEachIndexed { index, exercise ->
                assertTrue("${lesson.id} exercise $index needs options", exercise.options.size >= 2)
                assertTrue("${lesson.id} exercise $index answer index", exercise.answer in exercise.options.indices)
            }
        }
    }

    @Test
    fun unitTwoUsesFiveParticlePracticeQuestionsAsSpecifiedInThePdf() {
        val unit2 = lessons.first { it.id == "n5-u02" }
        assertEquals(5, unit2.exercises.size)
        assertEquals("wa y mo", unit2.exercises.first().options[unit2.exercises.first().answer])
        assertTrue(unit2.exercises.any { it.prompt.contains("lugar donde ocurre") })
        assertTrue(unit2.exercises.any { it.prompt.contains("destino") })
    }
    @Test
    fun allN5PracticalAssessmentInstructionsMatchThePdfTasks() {
        val expected = mapOf(
            "n5-u01" to "dos frases",
            "n5-u02" to "cinco frases",
            "n5-u03" to "seis verbos",
            "n5-u04" to "cinco frases",
            "n5-u05" to "cuatro adjetivos",
            "n5-u06" to "tres frases",
            "n5-u07" to "tres horas",
            "n5-u08" to "dónde está un lugar",
            "n5-u09" to "cuatro instrucciones",
            "n5-u10" to "cuatro frases"
        )
        expected.forEach { (id, phrase) ->
            assertTrue("$id assessment must preserve its PDF task", lessonSupplements.getValue(id).unitAssessment.contains(phrase, ignoreCase = true))
        }
    }
    @Test
    fun practicalTaskPanelAppliesOnlyToN5UnitsThatRequireIt() {
        lessons.filter { it.level == "N5" }.forEach { lesson ->
            assertEquals(lesson.id != "n5-u02", requiresPracticalAssessment(lesson))
        }
        lessons.filter { it.level == "N4" }.forEach { lesson ->
            assertFalse(requiresPracticalAssessment(lesson))
        }
    }
    @Test
    fun n5GrammarSummariesRetainTheSpecificFormsListedInThePdf() {
        val unit2 = lessonSupplements.getValue("n5-u02").grammar
        listOf("wa", "ga", "o", "ni", "de", "mo").forEach { assertTrue("unit 2: $it", unit2.contains(it)) }

        val unit3 = lessonSupplements.getValue("n5-u03").grammar
        listOf("taberu", "tabemasu", "nomu", "nomimasu", "iku", "ikimasu", "kuru", "kimasu", "suru", "shimasu", "miru", "mimasu")
            .forEach { assertTrue("unit 3: $it", unit3.contains(it)) }

        val unit4 = lessonSupplements.getValue("n5-u04").grammar
        listOf("tabemashita", "nomimashita", "tabemasen", "ikimasen")
            .forEach { assertTrue("unit 4: $it", unit4.contains(it)) }

        val unit5 = lessonSupplements.getValue("n5-u05").grammar
        listOf("ookii", "chiisai", "takai", "yasui", "atsui", "samui", "~kunai desu")
            .forEach { assertTrue("unit 5: $it", unit5.contains(it)) }

        val unit6 = lessonSupplements.getValue("n5-u06").grammar
        listOf("kirei", "shizuka", "genki", "benri", "hima", "suki", "~desu", "~ja arimasen")
            .forEach { assertTrue("unit 6: $it", unit6.contains(it)) }
    }

    @Test
    fun unitsThreeFiveAndSixPracticeTheRequiredConjugationsAndAdjectiveForms() {
        val unit3 = lessons.first { it.id == "n5-u03" }
        listOf("tabemasu", "nomimasu", "ikimasu", "kimasu", "shimasu", "mimasu").forEach { form ->
            assertTrue("unit 3 must practice $form", unit3.exercises.any { exercise -> exercise.prompt.contains(form, true) || exercise.options.any { it == form } })
        }
        val unit5 = lessons.first { it.id == "n5-u05" }
        listOf("atsukunai desu", "chiisakunai desu", "yasukunai desu", "samukunai desu").forEach { form ->
            assertTrue("unit 5 must practice $form", unit5.exercises.any { exercise -> exercise.options.any { it == form } })
        }
        val unit6 = lessons.first { it.id == "n5-u06" }
        assertTrue(unit6.exercises.any { it.options.contains("genki desu") })
        assertTrue(unit6.exercises.any { it.options.contains("hima ja arimasen") })
        assertTrue(unit6.exercises.size in 5..8)
    }
    @Test
    fun n5UnitsSevenToTenKeepThePdfTopicsAndRequiredExpressions() {
        val unit7 = lessonSupplements.getValue("n5-u07")
        listOf("ichi", "ni", "san", "yon", "go", "roku", "nana", "hachi", "kyuu", "juu", "ji", "fun", "getsuyoubi")
            .forEach { assertTrue("unit 7 grammar: $it", unit7.grammar.contains(it)) }
        assertTrue(unit7.examples.any { it.contains("san ji") })
        assertTrue(unit7.examples.any { it.contains("getsuyoubi") })

        val unit8 = lessonSupplements.getValue("n5-u08")
        listOf("eki", "byouin", "toshokan", "kouen", "koko", "soko", "asoko", "doko")
            .forEach { term -> assertTrue("unit 8 content: $term", (unit8.grammar + unit8.vocabulary.joinToString { it.first }).contains(term)) }
        assertTrue(unit8.unitAssessment.contains("koko, soko o asoko"))

        val unit9 = lessonSupplements.getValue("n5-u09")
        listOf("~te kudasai", "~te mo ii desu ka").forEach { assertTrue("unit 9: $it", unit9.grammar.contains(it)) }
        listOf("mite", "kiite", "matte", "hayaku", "yukkuri", "shizuka ni")
            .forEach { term -> assertTrue("unit 9 vocabulary: $term", unit9.vocabulary.any { it.first == term }) }
        val unit9Exercises = lessons.first { it.id == "n5-u09" }.exercises
        listOf("Mite kudasai", "Kiite kudasai", "Matte kudasai", "Yukkuri hanashite kudasai")
            .forEach { instruction ->
                assertTrue("unit 9 must practice $instruction", unit9Exercises.any { exercise -> exercise.options.contains(instruction) })
            }

        val unit10 = lessonSupplements.getValue("n5-u10")
        listOf("~ga arimasu", "~ga imasu", "~ga arimasen").forEach { assertTrue("unit 10: $it", unit10.grammar.contains(it)) }
        listOf("hito", "kuruma", "tsukue", "kippu", "aru", "iru")
            .forEach { term -> assertTrue("unit 10 vocabulary: $term", unit10.vocabulary.any { it.first == term }) }
    }

    @Test
    fun testEditionUnlocksEveryPublishedLessonRegardlessOfProgress() {
        assertTrue(isLessonUnlocked("n5-u01", emptySet()))
        assertTrue(isLessonUnlocked("n5-u02", emptySet()))
        assertTrue(isLessonUnlocked("n5-u03", emptySet()))
        assertTrue(isLessonUnlocked("n4-u01", emptySet()))
        assertFalse(isLessonUnlocked("lesson-not-published", emptySet()))
    }

    @Test
    fun kanaAndKanjiRemainHiddenUntilNativeReviewIsRecorded() {
        assertTrue(lessonSupplements.keys.filter { it.startsWith("n5-") }.size == 10)
        lessonSupplements.filterKeys { it.startsWith("n5-") }.forEach { (id, lesson) ->
            assertFalse("$id must not publish unreviewed Japanese scripts", lesson.kanaReviewed)
        }
    }

    @Test
    fun n5SourceExamplesArePresentedInRomajiAsSpecifiedByThePdf() {
        assertTrue(lessonSupplements.getValue("n5-u01").examples.first().startsWith("Watashi wa Sakura desu."))
        assertTrue(lessonSupplements.getValue("n5-u10").examples.first().startsWith("Tsukue no ue ni hon ga arimasu."))
    }
}
