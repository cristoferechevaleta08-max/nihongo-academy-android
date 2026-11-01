package com.nihongoacademy.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.nihongoacademy.R
import com.nihongoacademy.data.AuthRepository
import com.nihongoacademy.data.SessionStore
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.model.StudyProgress
import com.nihongoacademy.ui.theme.AcademyColors
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val progressStore = remember { StudyProgressStore(context) }
    val scope = rememberCoroutineScope()
    var displayName by remember { mutableStateOf("UsuarioKun") }
    var email by remember { mutableStateOf("") }
    var languageCode by remember { mutableStateOf("ja") }
    var progress by remember { mutableStateOf(StudyProgress()) }

    LaunchedEffect(Unit) {
        val user = authRepository.currentUser()
        displayName = user?.displayName?.takeIf { it.isNotBlank() } ?: "UsuarioKun"
        email = user?.email.orEmpty()
        languageCode = SessionStore(context).readLanguage() ?: "ja"
        progress = progressStore.getProgress(languageCode)
    }
    val correct = progress.recentResults.sumOf { it.correctAnswers }
    val expInLevel = progress.totalXp % 500

    Column(
        Modifier.fillMaxSize().background(AcademyColors.Background)
            .verticalScroll(rememberScrollState()).padding(horizontal = 22.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Perfil", style = MaterialTheme.typography.headlineLarge, color = AcademyColors.TextPrimary)
            Spacer(Modifier.weight(1f))
            Text("⚙", color = AcademyColors.TextPrimary, fontSize = 24.sp,
                modifier = Modifier.clip(CircleShape).clickable { navController.navigate("settings") }.padding(8.dp))
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.size(112.dp), contentAlignment = Alignment.BottomEnd) {
                Image(painterResource(R.drawable.ic_kitsune_placeholder), "Avatar Kitsune",
                    Modifier.size(104.dp).clip(CircleShape).background(AcademyColors.Surface).border(1.dp, AcademyColors.Border, CircleShape).padding(10.dp))
                Box(Modifier.size(30.dp).clip(CircleShape).background(AcademyColors.Sakura), contentAlignment = Alignment.Center) {
                    Text("✎", color = AcademyColors.Background, fontSize = 15.sp)
                }
            }
            Spacer(Modifier.height(12.dp))
            Text(displayName, color = AcademyColors.TextPrimary, style = MaterialTheme.typography.headlineSmall)
            if (email.isNotBlank()) Text(email, color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodySmall)
            Text("Nivel ${progress.currentLevel} • Principiante", color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("EXP", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.labelLarge)
                Text("$expInLevel / 500 EXP", color = AcademyColors.Sakura, style = MaterialTheme.typography.labelLarge)
            }
            Spacer(Modifier.height(7.dp))
            LinearProgressIndicator(progress = expInLevel / 500f, modifier = Modifier.fillMaxWidth().height(7.dp).clip(CircleShape),
                color = AcademyColors.Sakura, trackColor = AcademyColors.Border)
        }
        Card(colors = CardDefaults.cardColors(containerColor = AcademyColors.Surface), shape = RoundedCornerShape(22.dp)) {
            Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Text("Tu progreso", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.titleLarge)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    ProfileMetric("▣", progress.completedLessons.size.toString(), "Lecciones")
                    ProfileMetric("✓", correct.toString(), "Aciertos")
                    ProfileMetric("♨", progress.streakDays.toString(), "Racha")
                }
            }
        }
        Column {
            ProfileMenuRow("Mi perfil", "◎") { }
            ProfileMenuRow("Estadísticas", "▥") { navController.navigate("study") }
            ProfileMenuRow("Configuración", "⚙") { navController.navigate("settings") }
            ProfileMenuRow("Ayuda y soporte", "?") { navController.navigate("community") }
            ProfileMenuRow("Cerrar sesión", "↪", destructive = true) {
                scope.launch {
                    authRepository.logout()
                    navController.navigate("welcome") { popUpTo(0) { inclusive = true } }
                }
            }
        }
    }
}

@Composable
private fun ProfileMetric(icon: String, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(icon, color = AcademyColors.Sakura, fontSize = 20.sp)
        Text(value, color = AcademyColors.TextPrimary, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(label, color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun ProfileMenuRow(title: String, icon: String, destructive: Boolean = false, onClick: () -> Unit) {
    Column {
        Row(Modifier.fillMaxWidth().height(58.dp).clickable(onClick = onClick).padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(icon, color = if (destructive) AcademyColors.JapaneseRed else AcademyColors.TextSecondary, fontSize = 19.sp)
            Text(title, Modifier.padding(start = 14.dp).weight(1f), color = if (destructive) AcademyColors.JapaneseRed else AcademyColors.TextPrimary,
                style = MaterialTheme.typography.bodyLarge)
            Text("›", color = AcademyColors.TextSecondary, fontSize = 24.sp)
        }
        HorizontalDivider(color = AcademyColors.Border, thickness = 1.dp)
    }
}
