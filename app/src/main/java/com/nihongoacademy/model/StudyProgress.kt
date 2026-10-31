package com.nihongoacademy.model

data class StudyProgress(
    val languageCode: String = "JA",
    val completedModules: Set<String> = emptySet(),
    val completedLessons: Set<String> = emptySet(),
    val totalXp: Int = 0,
    val streakDays: Int = 0,
    val lastStudyDate: Long = 0L
)

val StudyProgress.completedModuleCount: Int
    get() = completedModules.size
