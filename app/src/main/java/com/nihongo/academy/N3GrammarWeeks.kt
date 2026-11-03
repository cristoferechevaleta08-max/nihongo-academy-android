package com.nihongo.academy

/**
 * Primera unidad gramatical N3: voz pasiva.
 * El contenido es material didáctico original organizado para el curso.
 */
internal val n3PassiveVoiceDays = listOf(
    KanaDay(1, "Qué expresa la voz pasiva", "Identificar quién realiza la acción y quién la recibe.",
        listOf(
            "Reconocer la estructura: persona afectada は agente に verbo en pasiva.",
            "Distinguir la persona que realiza la acción de la persona o cosa afectada.",
            "Comparar una frase activa con su equivalente pasiva.",
            "Leer en voz alta わたしは先生にほめられました。"
        )),
    KanaDay(2, "Pasiva de verbos del grupo 1", "Transformar verbos godan cambiando la última sílaba de la fila u a la fila a y añadiendo れる.",
        listOf(
            "Practicar 書く → 書かれる (kaku → kakareru).",
            "Practicar 読む → 読まれる (yomu → yomareru).",
            "Practicar 話す → 話される (hanasu → hanasareru).",
            "Recordar que う cambia a わ antes de れる: 買う → 買われる."
        )),
    KanaDay(3, "Pasiva de verbos del grupo 2", "Formar la pasiva de verbos ichidan sustituyendo る por られる.",
        listOf(
            "Practicar 食べる → 食べられる (taberu → taberareru).",
            "Practicar 見る → 見られる (miru → mirareru).",
            "Identificar la terminación られる en contexto pasivo.",
            "No confundir la forma pasiva con otros usos posibles de られる."
        )),
    KanaDay(4, "Pasiva de verbos irregulares", "Dominar las dos conjugaciones irregulares principales.",
        listOf(
            "Aprender する → される (suru → sareru).",
            "Aprender 来る → 来られる (kuru → korareru).",
            "Practicar las dos formas dentro de frases completas.",
            "Recordar que estas formas no siguen las reglas regulares."
        )),
    KanaDay(5, "Partícula に en la pasiva", "Marcar con に a la persona o entidad que realiza la acción.",
        listOf(
            "Leer わたしは先生にほめられました。 (El profesor me elogió).",
            "Identificar quién recibe la acción y quién la realiza.",
            "Practicar frases con 友だちに y 母に.",
            "Evitar traducir に siempre como una única preposición española."
        )),
    KanaDay(6, "Pasiva de perjuicio y experiencia", "Comprender situaciones en las que el hablante expresa que una acción ajena le afectó.",
        listOf(
            "Reconocer 雨に降られました como una experiencia desagradable causada por la lluvia.",
            "Distinguir una pasiva directa de una pasiva de perjuicio.",
            "Identificar a la persona afectada aunque no sea el objeto directo de la frase activa.",
            "Practicar ejemplos breves sin asumir que toda pasiva expresa algo negativo."
        )),
    KanaDay(7, "Repaso y evaluación de voz pasiva", "Consolidar formación, partículas y comprensión del significado.",
        listOf(
            "Conjugar verbos de los tres grupos y los dos irregulares.",
            "Transformar frases activas sencillas a pasivas.",
            "Identificar agente y persona afectada.",
            "Completar la evaluación semanal y revisar los errores."
        ))
)

internal val n3PassiveVoiceAssessment = listOf(
    KanaQuizQuestion("¿Cuál es la forma pasiva de 書く (kaku)?", listOf("書かれる (kakareru)", "書ける (kakeru)", "書かせる (kakaseru)", "書いた (kaita)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de 読む (yomu)?", listOf("読まれる (yomareru)", "読める (yomeru)", "読ませる (yomaseru)", "読んだ (yonda)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de 買う (kau)?", listOf("買われる (kawareru)", "買える (kaeru)", "買わせる (kawaseru)", "買った (katta)"), 0),
    KanaQuizQuestion("¿Cómo se forma normalmente la pasiva de 食べる (taberu)?", listOf("食べられる (taberareru)", "食べれる (taberu)", "食べさせる (tabesaseru)", "食べた (tabeta)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de 見る (miru)?", listOf("見られる (mirareru)", "見させる (misaseru)", "見える (mieru)", "見た (mita)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de する (suru)?", listOf("される (sareru)", "させる (saseru)", "できる (dekiru)", "した (shita)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de 来る (kuru)?", listOf("来られる (korareru)", "来させる (kosaseru)", "来れる (koreru)", "来た (kita)"), 0),
    KanaQuizQuestion("En わたしは先生にほめられました, ¿quién realizó la acción?", listOf("El profesor", "Yo", "Un amigo", "Nadie"), 0),
    KanaQuizQuestion("En una pasiva típica, ¿qué función suele marcar に?", listOf("El agente que realiza la acción", "El objeto directo", "El lugar de destino", "El tiempo"), 0),
    KanaQuizQuestion("¿Qué expresa 雨に降られました en un contexto de pasiva de perjuicio?", listOf("Me afectó que lloviera / me agarró la lluvia", "Hice que lloviera", "Dejé de llover", "Voy a hacer que llueva"), 0),
    KanaQuizQuestion("¿Toda oración pasiva japonesa expresa necesariamente algo negativo?", listOf("No, depende del contexto", "Sí, siempre", "Solo si aparece に", "Solo en pasado"), 0),
    KanaQuizQuestion("¿Qué elemento identifica a la persona afectada en わたしは先生にほめられました?", listOf("わたしは", "先生に", "ほめ", "ました"), 0)
)

internal fun n3GrammarDaysForWeek(week: Int): List<KanaDay> =
    if (week == 1) n3PassiveVoiceDays else emptyList()

internal fun n3GrammarAssessmentForWeek(week: Int): List<KanaQuizQuestion> =
    if (week == 1) n3PassiveVoiceAssessment else emptyList()
