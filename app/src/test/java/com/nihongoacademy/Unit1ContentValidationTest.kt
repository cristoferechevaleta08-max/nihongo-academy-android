package com.nihongoacademy

import com.google.gson.JsonParser
import com.google.common.truth.Truth.assertThat
import com.nihongoacademy.content.Unit1ReviewPolicy
import org.junit.Test
import java.io.File

class Unit1ContentValidationTest {
    private val root=File("src/main/assets")
    private val scope=JsonParser.parseString(File(root,"content/ja/unit1/scope.json").readText()).asJsonObject
    private val allowed=buildSet<Char> {
        fun walk(e:com.google.gson.JsonElement) {
            when { e.isJsonPrimitive -> e.asString.forEach { if(it in '\u3040'..'\u309f') add(it) }; e.isJsonArray -> e.asJsonArray.forEach(::walk); e.isJsonObject -> e.asJsonObject.entrySet().forEach { walk(it.value) } }
        }
        walk(scope.getAsJsonObject("kana_allowed"))
    }
    private val lessons=(1..5).map { File(root,"content/ja/unit1/lessons/1-$it.json") }
    private val examFile=File(root,"content/ja/unit1/exams/unit1.json")
    private val exam=JsonParser.parseString(examFile.readText()).asJsonObject
    private val items=exam.getAsJsonArray("items")
    private fun strings(e:com.google.gson.JsonElement):List<String> = when { e.isJsonPrimitive && e.asJsonPrimitive.isString -> listOf(e.asString); e.isJsonArray -> e.asJsonArray.flatMap(::strings); e.isJsonObject -> e.asJsonObject.entrySet().flatMap { strings(it.value) }; else -> emptyList() }

    @Test fun everyKanaInLessonsExamAndDistractorsIsInScope() {
        (lessons+examFile).forEach { f -> strings(JsonParser.parseString(f.readText())).forEach { s -> s.filter { it in '\u3040'..'\u309f' && it !in '\u3099'..'\u309c' }.forEach { kana -> assertThat(allowed.contains(kana)).isTrue() } } }
    }
    @Test fun romanizationTableCoversEveryAllowedKana() {
        val table=scope.getAsJsonObject("romanization_reference")
        assertThat(table.keySet()).containsExactlyElementsIn(allowed.map { it.toString() })
        table.entrySet().forEach { (k,v) -> assertThat(k.length).isEqualTo(1); assertThat(v.asString).isNotEmpty() }
        assertThat(scope.getAsJsonObject("romanization").get("system").asString).isEqualTo("Hepburn")
        assertThat(scope.getAsJsonObject("romanization").get("macrons").asBoolean).isFalse()
    }
    @Test fun eachExamItemHasOneCorrectAnswerAndExplainedDistractors() {
        items.forEach { e -> val i=e.asJsonObject
            if(i.get("type").asString=="kana_input") { assertThat(i.getAsJsonArray("options")).isEmpty(); assertThat(i.get("expected_answer").asString).isNotEmpty(); assertThat(i.getAsJsonArray("distractors")).isEmpty() }
            else { assertThat(i.getAsJsonArray("options").count { it.asJsonObject.get("correct").asBoolean }).isEqualTo(1); assertThat(i.getAsJsonArray("distractors").size()).isAtLeast(3); i.getAsJsonArray("distractors").forEach { d -> assertThat(d.asJsonObject.get("error_tag").asString).isNotEmpty(); assertThat(d.asJsonObject.get("explanation").asString).isNotEmpty() } }
            assertThat(i.get("source_note").asString).isNotEmpty()
        }
    }
    @Test fun noUnreviewedItemIsPublished() {
        assertThat(exam.getAsJsonObject("publication").getAsJsonArray("official_item_ids")).isEmpty()
        val candidates=items.map { e -> val i=e.asJsonObject; Unit1ReviewPolicy.ReviewItem(i.get("id").asString,i.get("status").asString,null,null,false,i.get("competency").asString=="auditiva") }
        assertThat(Unit1ReviewPolicy.officialItemIds(candidates)).isEmpty()
        assertThat(items.all { it.asJsonObject.get("status").asString=="en_revision" }).isTrue()
    }
    @Test fun listeningItemsHavePendingNativeAudioAndTranscripts() {
        val listening=items.map { it.asJsonObject }.filter { it.get("competency").asString=="auditiva" }
        assertThat(listening).hasSize(2)
        listening.forEach { i -> assertThat(i.get("audio_id").asString).isNotEmpty(); assertThat(File(root,"audio/ja/${i.get("audio_id").asString}.mp3").exists()).isFalse(); assertThat(i.getAsJsonArray("review_warnings").map { it.asString }).contains("audio_nativo_faltante") }
        assertThat(File(root,"audio/ja/ja-u1-exam-hai-11.txt").exists()).isTrue()
        assertThat(File(root,"audio/ja/ja-u1-exam-iie-12.txt").exists()).isTrue()
    }
    @Test fun noParticleWoKanjiOrExcludedCombinationsAreAssessed() {
        val text=(lessons+examFile).flatMap { strings(JsonParser.parseString(it.readText())) }.joinToString(" ")
        listOf("を","学校","漢字","きゃ","っ").forEach { assertThat(text).doesNotContain(it) }
    }
    @Test fun allFiveLessonsHaveThreeQuestionClosingQuizzes() {
        lessons.forEach { f -> val l=JsonParser.parseString(f.readText()).asJsonObject; assertThat(l.getAsJsonArray("closing_quiz")).hasSize(3); assertThat(l.get("objective").asString).isNotEmpty(); assertThat(l.get("explanation").asString).isNotEmpty(); assertThat(l.getAsJsonArray("guided_practice")).isNotEmpty(); assertThat(l.get("free_practice").asString).isNotEmpty() }
    }
}
