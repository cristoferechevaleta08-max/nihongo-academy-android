package com.nihongo.academy

/**
 * Selección pedagógica de 370 kanji para el nivel N3.
 *
 * La lista de referencia procede de JLPT Sensei (https://jlptsensei.com/jlpt-n3-kanji-list/).
 * El JLPT no publica una lista oficial y definitiva de kanji por nivel.
 * Por eso este catálogo es una selección de estudio, no una lista oficial.
 *
 * Se excluyen 28 caracteres ya presentes en los catálogos N4/N5 de la app
 * y se sustituyen por 28 caracteres de nivel intermedio para conservar 370
 * entradas únicas sin modificar los niveles anteriores.
 */
internal val n3KanjiReferenceCharacters = "政議民連対部合市内相定回選米実関決全表戦経最現調化当約首法性的要制治務成期取都和機平加受続進数記初指権支産点報済活原共得解交資予向際勝面告反判認参利組信在件側任引求所次昨論官増係感情投示変打直両式確果容必演歳争談能位置流格疑過局放常状球職与供役構割身費付由説難優夫収断石違消神番規術備宅害配警育席訪乗残想声助労例然限追商葉伝働形景落好退頭負渡失差末守若種美命福望非観察段横深申様財港識呼達良阪候程満敗値突光路科積他処太客否師登易速存飛殺号単座破除完降責捕危給苦迎園具辞因馬愛富彼未舞亡冷適婦寄込顔類余王返妻背熱宿薬険頼覚船途許抜便留罪努精散静婚喜浮絶幸押倒等老曲払庭徒勤遅居雑招困欠更刻賛抱犯恐息遠戻願絵越欲痛笑互束似列探逃遊迷夢君閉緒折草暮酒悲晴掛到寝暗盗吸陽御歯忘雪吹娘誤洗慣礼窓昔貧怒泳祖杯疲皆鳴腹煙眠怖耳頂箱晩寒髪忙才靴恥偶偉猫幾誰".map { it.toString() }

internal val n3KanjiExcludedForLowerLevelOverlap = setOf(
    "遠", "暗", "閉", "働", "好", "合", "市", "回", "首", "引", "声", "頭", "光", "太", "顔", "都", "進", "所", "乗", "薬", "寒", "民", "産", "説", "便", "洗", "申", "耳"
)

internal val n3KanjiCuratedSubstitutions = listOf(
    "圧", "囲", "移", "営", "衛", "液", "応", "往", "桜", "恩", "価", "河", "快", "額", "幹", "眼", "基", "技", "義", "逆", "救", "境", "禁", "検", "減", "個", "講", "査"
)

internal val n3KanjiCharacters: List<String> =
    n3KanjiReferenceCharacters
        .filterNot { it in n3KanjiExcludedForLowerLevelOverlap } +
        n3KanjiCuratedSubstitutions

/** Tres bloques consecutivos: 123 + 123 + 124 kanji. */
internal val n3KanjiBlocks: List<List<String>> = listOf(
    n3KanjiCharacters.take(123),
    n3KanjiCharacters.drop(123).take(123),
    n3KanjiCharacters.drop(246)
)
