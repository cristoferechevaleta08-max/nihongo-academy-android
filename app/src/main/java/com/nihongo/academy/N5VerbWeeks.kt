package com.nihongo.academy

/** Semanas 5–6 del PDF: verbos en forma masu, presente, pasado y negativo. */
internal val n5VerbWeeks = listOf(
 KanaDay(29,"Verbos de uso diario","Reconocer taberu, nomu, iku, kuru, suru y miru.",listOf(
  "Leer cada verbo en su forma de diccionario.",
  "Relacionar cada verbo con su significado.",
  "Identificar si el verbo expresa comer, beber, ir, venir, hacer o ver.",
  "Repasar vocabulario de días anteriores."
 )),
 KanaDay(30,"Forma masu: verbos ru","Transformar taberu y miru a su forma cortés.",listOf(
  "Practicar taberu → tabemasu.",
  "Practicar miru → mimasu.",
  "Leer ambas formas y reconocer el presente cortés.",
  "Escribir las dos transformaciones de memoria."
 )),
 KanaDay(31,"Forma masu: verbos u","Transformar nomu e iku a su forma cortés.",listOf(
  "Practicar nomu → nomimasu.",
  "Practicar iku → ikimasu.",
  "Comparar las terminaciones con los verbos del día anterior.",
  "Completar frases cortas en presente."
 )),
 KanaDay(32,"Verbos irregulares","Reconocer kuru → kimasu y suru → shimasu.",listOf(
  "Practicar kuru → kimasu.",
  "Practicar suru → shimasu.",
  "Recordar que son transformaciones irregulares.",
  "Escribir las seis formas masu estudiadas."
 )),
 KanaDay(33,"Presente afirmativo","Usar verbos en masu dentro de frases sencillas.",listOf(
  "Leer Mainichi gohan o tabemasu.",
  "Leer Gakkou ni ikimasu.",
  "Elegir el verbo que completa cada frase.",
  "Crear tres frases afirmativas."
 )),
 KanaDay(34,"Repaso de forma masu","Recuperar las seis conjugaciones sin mirar la tabla.",listOf(
  "Transformar seis verbos de diccionario a masu.",
  "Leer las frases de ejemplo.",
  "Corregir las conjugaciones confundidas.",
  "Practicar una ronda mezclada."
 )),
 KanaDay(35,"Evaluación de verbos","Prueba corta de verbos en forma masu.",listOf(
  "Completar la prueba semanal.",
  "Revisar cada respuesta incorrecta.",
  "Si los errores superan el 30%, repetir la semana con práctica adicional."
 )),
 KanaDay(36,"Pasado cortés","Usar ~mashita para hablar de acciones pasadas.",listOf(
  "Transformar tabemasu → tabemashita.",
  "Transformar ikimasu → ikimashita.",
  "Relacionar kinou con ayer.",
  "Leer frases en pasado."
 )),
 KanaDay(37,"Negativo cortés","Usar ~masen para negar una acción.",listOf(
  "Transformar nomimasu → nomimasen.",
  "Transformar tabemasu → tabemasen.",
  "Leer frases negativas en voz alta.",
  "Distinguir afirmativo y negativo."
 )),
 KanaDay(38,"Presente, pasado y negativo","Comparar las tres formas corteses.",listOf(
  "Comparar tabemasu, tabemashita y tabemasen.",
  "Comparar ikimasu, ikimashita e ikimasen.",
  "Elegir la forma que coincide con el momento indicado.",
  "Repasar kinou, kyou y ashita."
 )),
 KanaDay(39,"Marcadores de tiempo","Relacionar acciones con ayer, hoy y mañana.",listOf(
  "Reconocer kinou (ayer), kyou (hoy) y ashita (mañana).",
  "Elegir pasado o presente según la frase.",
  "Leer ejemplos breves.",
  "Escribir tres frases con marcadores temporales."
 )),
 KanaDay(40,"Frases negativas y afirmativas","Construir frases cortas con verbos masu.",listOf(
  "Completar frases afirmativas.",
  "Transformar frases a negativo cortés.",
  "Transformar frases al pasado cortés.",
  "Anotar las conjugaciones falladas."
 )),
 KanaDay(41,"Repaso de conjugaciones","Recuperar las formas masu, mashita y masen.",listOf(
  "Conjugar los seis verbos del bloque.",
  "Leer frases mezcladas sin ayuda.",
  "Explicar qué terminación indica pasado o negativo.",
  "Repetir los ejercicios incorrectos."
 )),
 KanaDay(42,"Evaluación de gramática N5","Prueba de presente, pasado y negativo de verbos masu.",listOf(
  "Completar la prueba semanal.",
  "Revisar las respuestas incorrectas.",
  "Si los errores superan el 30%, repetir la semana con práctica adicional."
 ))
)

internal val masuWeekAssessment = listOf(
 KanaQuizQuestion("Forma masu de taberu (comer)",listOf("tabemasu","tabenai","tabete","tabemashita"),0),
 KanaQuizQuestion("Forma masu de nomu (beber)",listOf("nomimasu","nomitai","nomimasen","nomimashita"),0),
 KanaQuizQuestion("Forma masu de iku (ir)",listOf("ikimasu","ikimasen","ikimashita","ikita"),0),
 KanaQuizQuestion("Forma masu de kuru (venir)",listOf("kimasu","kimasen","kita","kite"),0),
 KanaQuizQuestion("Forma masu de suru (hacer)",listOf("shimasu","mimasu","kimasu","ikimasu"),0),
 KanaQuizQuestion("Forma masu de miru (ver)",listOf("mimasu","shimasu","nomimasu","kimasu"),0),
 KanaQuizQuestion("¿Qué significa tabemasu?",listOf("Como","Bebo","Voy","Vengo"),0),
 KanaQuizQuestion("¿Qué significa ikimasu?",listOf("Ir","Venir","Hacer","Ver"),0),
 KanaQuizQuestion("Completá: Mainichi gohan o ___.",listOf("tabemasu","ikimasu","mimasu","kimasu"),0),
 KanaQuizQuestion("¿Qué significa nomimasu?",listOf("Beber","Comer","Dormir","Leer"),0)
)

internal val masuPastNegativeAssessment = listOf(
 KanaQuizQuestion("Pasado cortés de tabemasu",listOf("tabemashita","tabemasen","tabemasu","tabete"),0),
 KanaQuizQuestion("Negativo cortés de nomimasu",listOf("nomimasen","nomimashita","nomimasu","nomitai"),0),
 KanaQuizQuestion("¿Qué significa kinou?",listOf("Ayer","Hoy","Mañana","Ahora"),0),
 KanaQuizQuestion("¿Qué significa ikimashita?",listOf("Fui","Voy","No voy","Quiero ir"),0),
 KanaQuizQuestion("¿Qué terminación expresa pasado cortés?",listOf("mashita","masen","masu","tai"),0),
 KanaQuizQuestion("¿Qué terminación expresa negativo cortés?",listOf("masen","mashita","masu","te"),0),
 KanaQuizQuestion("Negativo cortés de tabemasu",listOf("tabemasen","tabemashita","tabemasu","tabete"),0),
 KanaQuizQuestion("Pasado cortés de ikimasu",listOf("ikimashita","ikimasen","ikimasu","ikitai"),0),
 KanaQuizQuestion("¿Qué significa ashita?",listOf("Mañana","Ayer","Hoy","Ahora"),0),
 KanaQuizQuestion("¿Qué significa kyou?",listOf("Hoy","Ayer","Mañana","Siempre"),0)
)

internal fun verbDaysForWeek(week:Int):List<KanaDay> = when(week){
 5->n5VerbWeeks.take(7)
 6->n5VerbWeeks.drop(7).take(7)
 else->emptyList()
}
