package com.nihongoacademy.content

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject

class Unit1ContentRepository(context: Context) {
    private val appContext = context.applicationContext
    private val gson = Gson()

    fun loadLesson(number: Int): JsonObject {
        require(number in 1..5) { "Unidad 1: lección fuera de rango" }
        return appContext.assets.open("content/ja/unit1/lessons/1-$number.json").bufferedReader().use {
            gson.fromJson(it, JsonObject::class.java)
        }
    }

    fun loadExam(): JsonObject = appContext.assets.open("content/ja/unit1/exams/unit1.json").bufferedReader().use {
        gson.fromJson(it, JsonObject::class.java)
    }

    fun loadReviewerMode(): JsonObject = appContext.assets.open("content/ja/unit1/reviewer-mode.json").bufferedReader().use {
        gson.fromJson(it, JsonObject::class.java)
    }

    fun publishedExamItems(): List<JsonObject> {
        val exam = loadExam()
        val publication = exam.getAsJsonObject("publication")
        val allowed = publication.getAsJsonArray("official_item_ids").map { it.asString }.toSet()
        return exam.getAsJsonArray("items").map { it.asJsonObject }.filter { item ->
            item.get("id").asString in allowed &&
                item.get("status").asString == "revisado" &&
                !item.get("reviewer_native").isJsonNull &&
                !item.get("review_date").isJsonNull &&
                (item.get("competency").asString != "auditiva" || item.get("audio_id") != null)
        }
    }
}
