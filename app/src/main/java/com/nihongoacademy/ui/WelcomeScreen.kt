package com.nihongoacademy.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

private val Ink = Color(0xFF0A0A0A)
private val Paper = Color(0xFFF9F5F0)

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Paper).verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.weight(1f))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.height(188.dp).fillMaxWidth()) {
            Canvas(Modifier.fillMaxSize()) {
                drawCircle(Ink.copy(alpha = 0.62f), size.minDimension * 0.39f,
                    Offset(size.width / 2f, size.height / 2f),
                    style = Stroke(1.dp.toPx(), pathEffect = PathEffect.dashPathEffect(floatArrayOf(7.dp.toPx(), 5.dp.toPx()), 1f)))
                drawCircle(Ink.copy(alpha = 0.24f), size.minDimension * 0.34f,
                    Offset(size.width / 2f, size.height / 2f), style = Stroke(0.7.dp.toPx()))
            }
            Text("日", color = Ink, fontSize = 58.sp, fontWeight = FontWeight.Light, fontFamily = FontFamily.Serif)
        }
        Spacer(Modifier.height(18.dp))
        Text("Nihongo Academy", color = Ink, style = MaterialTheme.typography.headlineLarge.copy(fontFamily = FontFamily.Serif, fontSize = 38.sp, lineHeight = 42.sp), textAlign = TextAlign.Center)
        Spacer(Modifier.height(10.dp))
        Text("日本語アカデミー", color = Ink.copy(alpha = 0.5f), style = MaterialTheme.typography.bodyMedium.copy(letterSpacing = 3.sp), textAlign = TextAlign.Center)
        Spacer(Modifier.height(26.dp))
        Text("Aprende. Practica. Conecta.", color = Ink, style = MaterialTheme.typography.bodyMedium.copy(letterSpacing = 1.4.sp), textAlign = TextAlign.Center)
        Spacer(Modifier.weight(1f))
        Button(onClick = { navController.navigate("login") }, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Ink, contentColor = Color.White)) { Text("Iniciar sesión", fontWeight = FontWeight.SemiBold, fontSize = 16.sp) }
        Spacer(Modifier.height(12.dp))
        OutlinedButton(onClick = { navController.navigate("register") }, modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(24.dp), border = BorderStroke(1.5.dp, Ink), colors = ButtonDefaults.outlinedButtonColors(contentColor = Ink)) {
            Text("Crear cuenta", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        }
        Spacer(Modifier.height(28.dp))
        Text("—  o continuar con  —", color = Ink.copy(alpha = 0.55f), style = MaterialTheme.typography.labelMedium, letterSpacing = 1.sp)
        Spacer(Modifier.height(14.dp))
        Text("Google   ·   Apple", color = Ink.copy(alpha = 0.45f), style = MaterialTheme.typography.labelSmall)
        Spacer(Modifier.height(22.dp))
        Text("15 IDIOMAS  /  UN SOLO ESPACIO", color = Ink.copy(alpha = 0.62f), style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp))
        Spacer(Modifier.weight(0.6f))
    }
}
