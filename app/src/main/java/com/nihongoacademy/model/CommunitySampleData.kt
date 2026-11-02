package com.nihongoacademy.model

data class CommunityPost(
    val author: String,
    val time: String,
    val message: String,
    val likes: Int,
    val comments: Int,
    val avatar: String
)

/** Local preview content only. Replace this source with repository data when community backend exists. */
object CommunitySampleData {
    val questions = listOf(
        CommunityPost("SakuraRin", "Hace 2 h", "¿Algún consejo para memorizar hiragana sin confundir さ y ち?", 12, 4, "桜"),
        CommunityPost("NihongoPro", "Hace 3 h", "今日の勉強: diez palabras nuevas y una pequeña práctica de lectura. ¡Ánimo!", 8, 2, "日"),
        CommunityPost("HanaMori", "Ayer", "¿Qué recursos recomiendan para empezar a escuchar japonés lento?", 6, 3, "花")
    )
    val forum = listOf(
        CommunityPost("KaitoStudy", "Hace 1 h", "Grupo de estudio JLPT N5: compartimos objetivos semanales y dudas.", 15, 6, "海"),
        CommunityPost("MikaSensei", "Hace 5 h", "Hilo: diferencias entre は y が con ejemplos sencillos.", 21, 9, "美")
    )
}