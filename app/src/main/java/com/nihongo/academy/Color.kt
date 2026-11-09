package com.nihongo.academy

import androidx.compose.ui.graphics.Color

/** Paleta monocromática central. Las pantallas deben usar MaterialTheme.colorScheme. */
internal object AcademyColors {
    val Black = Color(0xFF000000)
    val White = Color(0xFFFFFFFF)
    val Gray900 = Color(0xFF171717)
    val Gray700 = Color(0xFF525252)
    val Gray500 = Color(0xFF737373)
    val Gray300 = Color(0xFFD4D4D4)
    val Gray100 = Color(0xFFF3F0F6)
    val Lavender = Color(0xFFE6E0EA)
    val Ink = Color(0xFF111111)
    val InkMuted = Color(0xFF6B6573)
    val Outline = Color(0xFFD4CEDB)
}

/** Alias temporales para conservar compatibilidad durante la migración de pantallas. */
internal object SakuraNightTokens {
    val Background = AcademyColors.White
    val Surface = AcademyColors.White
    val SurfaceVariant = AcademyColors.Gray100
    val SurfaceElevated = AcademyColors.White
    val Outline = AcademyColors.Gray300
    val Primary = AcademyColors.Black
    val PrimaryLight = AcademyColors.Gray300
    val PrimaryDeep = AcademyColors.Black
    val OnPrimary = AcademyColors.White
    val SecondaryContainer = AcademyColors.Gray100
    val OnSurface = AcademyColors.Black
    val OnSurfaceVariant = AcademyColors.Gray700
    val Success = AcademyColors.Gray700
    val Error = AcademyColors.Gray900
    val ScarfRed = AcademyColors.Black
    val FoxFur = AcademyColors.Gray700
    val FoxMuzzle = AcademyColors.White
    val White = AcademyColors.White
    val LightBackground = AcademyColors.White
    val LightSurface = AcademyColors.White
    val LightSurfaceVariant = AcademyColors.Gray100
    val LightOnSurface = AcademyColors.Black
    val LightOnSurfaceVariant = AcademyColors.Gray700
    val LightOutline = AcademyColors.Gray300
}