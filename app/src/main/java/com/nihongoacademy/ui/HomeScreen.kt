package com.nihongoacademy.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.nihongoacademy.R
import com.nihongoacademy.data.AuthRepository
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.StudyProgress
import com.nihongoacademy.ui.theme.AcademyColors

@Composable
fun HomeScreen(navController: NavController, languageCode: String = "ja") {
    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    val authRepository = remember { AuthRepository(context.applicationContext) }
    val languages = remember { LanguageCatalogFactory.buildSupportedLanguages() }
    val language = languages.firstOrNull { it.code.equals(languageCode, true) } ?: languages.first()
    var displayName by remember { mutableStateOf("") }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = languageCode.uppercase())) }
    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(languageCode, refreshing) {
        progress = progressStore.getProgress(languageCode).copy(languageCode = languageCode.uppercase())
        displayName = authRepository.currentUser()?.displayName.orEmpty()
        refreshing = false
    }
    val totalLessons = language.modules.sumOf { it.lessons.size }.coerceAtLeast(1)
    val progressPercent = (progress.completedLessons.size.toFloat() / totalLessons).coerceIn(0f, 1f)
    val recommended = language.modules.asSequence().flatMap { module -> module.lessons.asSequence().map { module to it } }.firstOrNull { pair -> pair.second.id !in progress.completedLessons }

    Column(Modifier.fillMaxSize().background(AcademyColors.Background).verticalScroll(rememberScrollState())
        .padding(horizontal = 20.dp, vertical = 18.dp), verticalArrangement = Arrangement.spacedBy(22.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("¡Hola${if (displayName.isBlank()) "" else ", $displayName"}! 👋", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.headlineSmall)
                Text("¿Listo para aprender hoy?", color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodyMedium)
            }
            Text("↻", color = AcademyColors.TextPrimary, fontSize = 25.sp,
                modifier = Modifier.clip(CircleShape).clickable { refreshing = true }.padding(8.dp))
        }
        Surface(onClick = { navController.navigate("language") }, color = AcademyColors.Surface, shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth()) { Text("${language.name} · ${language.code.uppercase()} · Nivel ${progress.currentLevel}   ›", color = AcademyColors.TextPrimary, modifier = Modifier.padding(14.dp), style = MaterialTheme.typography.titleSmall) }
        Box(Modifier.fillMaxWidth().height(220.dp).clip(RoundedCornerShape(24.dp)).background(AcademyColors.Surface)) {
            Image(painterResource(R.drawable.bg_japanese_landscape_placeholder), null,
                Modifier.fillMaxSize().alpha(0.65f), contentScale = ContentScale.Crop)
            Column(Modifier.align(Alignment.CenterStart).padding(20.dp).fillMaxWidth(0.78f),
                verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("${recommended?.second?.title ?: "Has completado las lecciones disponibles"}", color = AcademyColors.TextPrimary,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
                Button(onClick = { if (recommended != null) navController.navigate("lesson/${languageCode.lowercase()}/${recommended.first.id}/${recommended.second.id}") else navController.navigate("modules/${languageCode.lowercase()}") },
                    colors = ButtonDefaults.buttonColors(containerColor = AcademyColors.ButtonPrimary, contentColor = AcademyColors.ButtonPrimaryText),
                    shape = RoundedCornerShape(14.dp), contentPadding = PaddingValues(horizontal = 18.dp, vertical = 10.dp)) {
                    Text("${if (recommended != null) "Comenzar →" else "Ver cursos →"}", fontWeight = FontWeight.SemiBold)
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text("Accesos rápidos", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.titleLarge)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                HomeQuickAction("▤", "Cursos") { navController.navigate("modules/${languageCode.lowercase()}") }
                HomeQuickAction("▧", "Flashcards") { navController.navigate("flashcards/${languageCode.lowercase()}") }
                HomeQuickAction("✓", "Exámenes") { navController.navigate("exams") }
                HomeQuickAction("◎", "Comunidad") { navController.navigate("community") }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("Tu progreso", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
                Text("Ver todo", color = AcademyColors.Sakura, style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.clickable { navController.navigate("profile") }.padding(6.dp))
            }
            Card(colors = CardDefaults.cardColors(containerColor = AcademyColors.Surface), shape = RoundedCornerShape(22.dp)) {
                Row(Modifier.fillMaxWidth().padding(18.dp), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                    Box(Modifier.size(104.dp), contentAlignment = Alignment.Center) {
                        Canvas(Modifier.fillMaxSize()) {
                            drawArc(AcademyColors.Border, -90f, 360f, false, style = Stroke(9.dp.toPx(), cap = StrokeCap.Round),
                                size = Size(size.width, size.height))
                            drawArc(AcademyColors.Sakura, -90f, progressPercent * 360f, false, style = Stroke(9.dp.toPx(), cap = StrokeCap.Round),
                                size = Size(size.width, size.height))
                        }
                        Text("${(progressPercent * 100).toInt()}%", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    }
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                        if (progress.completedLessons.isEmpty()) Text("Tu camino empieza aquí. Completa tu primera lección para registrar tu progreso.", color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodySmall)
                        Text("Nivel ${progress.currentLevel} / Principiante", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.titleMedium)
                        Text("${progress.completedLessons.size} lecciones completadas", color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodySmall)
                        Text("${progress.totalXp} EXP acumulada", color = AcademyColors.Sakura, style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeQuickAction(icon: String, label: String, onClick: () -> Unit) {
    Column(Modifier.widthIn(min = 62.dp).clip(RoundedCornerShape(14.dp)).clickable(onClick = onClick).padding(vertical = 7.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(Modifier.size(52.dp).clip(RoundedCornerShape(16.dp)).background(AcademyColors.Surface), contentAlignment = Alignment.Center) {
            Text(icon, color = AcademyColors.Sakura, fontSize = 23.sp)
        }
        Text(label, color = AcademyColors.TextSecondary, style = MaterialTheme.typography.labelSmall)
    }
}
