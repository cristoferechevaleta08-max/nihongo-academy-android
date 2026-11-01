package com.nihongoacademy.ui

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.nihongoacademy.data.FlashcardReviewStore
import com.nihongoacademy.data.FlashcardSchedule
import com.nihongoacademy.data.ReviewRating
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.ModuleLesson
import com.nihongoacademy.ui.theme.AcademyColors
import kotlinx.coroutines.launch

@Composable
fun FlashcardsScreen(navController: NavController, languageCode: String) {
    val languages = remember { LanguageCatalogFactory.buildSupportedLanguages() }
    val language = languages.firstOrNull { it.code.equals(languageCode, ignoreCase = true) } ?: languages.first()
    val cards = remember(language.code) {
        language.modules.flatMap { module -> module.lessons.map { lesson -> Triple(module.id, lesson.id, lesson) } }
    }
    val context = LocalContext.current
    val store = remember { FlashcardReviewStore(context.applicationContext) }
    val scope = rememberCoroutineScope()
    var schedules by remember(language.code) { mutableStateOf<Map<String, FlashcardSchedule>>(emptyMap()) }
    var currentCard by remember(language.code) { mutableStateOf<Triple<String, String, ModuleLesson>?>(null) }
    var answerVisible by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(true) }
    var saving by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun cardKey(card: Triple<String, String, ModuleLesson>) = "${card.first}:${card.second}"
    fun selectNextCard() {
        val now = System.currentTimeMillis()
        currentCard = cards.firstOrNull { card -> (schedules[cardKey(card)]?.dueAt ?: 0L) <= now }
        answerVisible = false
    }
    LaunchedEffect(language.code) {
        schedules = store.getSchedules(language.code)
        selectNextCard()
        loading = false
    }
    fun review(rating: ReviewRating) {
        val card = currentCard ?: return
        saving = true
        errorMessage = null
        scope.launch {
            runCatching { store.recordReview(language.code, cardKey(card), rating) }
                .onSuccess { saved ->
                    schedules = schedules + (cardKey(card) to saved)
                    selectNextCard()
                }
                .onFailure { errorMessage = "No se pudo guardar el repaso. Inténtalo otra vez." }
            saving = false
        }
    }

    val selected = currentCard
    val currentIndex = selected?.let { cards.indexOf(it) + 1 } ?: 0
    val total = cards.size.coerceAtLeast(1)
    val dueCount = cards.count { (schedules[cardKey(it)]?.dueAt ?: 0L) <= System.currentTimeMillis() }

    Column(
        Modifier.fillMaxSize().background(AcademyColors.Background).verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Flashcards", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.weight(1f))
            Text("⚙", color = AcademyColors.TextPrimary, fontSize = 23.sp,
                modifier = Modifier.clip(CircleShape).clickable { navController.navigate("settings") }.padding(8.dp))
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(if (selected == null) "0 / $total" else "$currentIndex / $total",
                color = AcademyColors.TextPrimary, style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.weight(1f))
            Text("$dueCount pendientes", color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodySmall)
        }
        LinearProgressIndicator(
            progress = { if (selected == null) 0f else currentIndex.toFloat() / total },
            modifier = Modifier.fillMaxWidth().height(5.dp).clip(CircleShape),
            color = AcademyColors.Sakura, trackColor = AcademyColors.Border
        )

        if (loading) {
            Box(Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = AcademyColors.Sakura)
            }
        } else if (selected == null) {
            Card(Modifier.fillMaxWidth().heightIn(min = 300.dp),
                colors = CardDefaults.cardColors(containerColor = AcademyColors.ButtonPrimary),
                shape = RoundedCornerShape(24.dp)) {
                Column(Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text("✓", color = AcademyColors.Sakura, fontSize = 42.sp)
                    Text("¡Todo repasado por ahora!", color = AcademyColors.ButtonPrimaryText, style = MaterialTheme.typography.titleLarge)
                    Text("Las tarjetas volverán cuando llegue su próximo horario de repaso.",
                        color = AcademyColors.ButtonPrimaryText.copy(alpha = 0.65f))
                }
            }
        } else {
            val lesson = selected.third
            Card(
                Modifier.fillMaxWidth().heightIn(min = 300.dp),
                colors = CardDefaults.cardColors(containerColor = AcademyColors.ButtonPrimary),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(lesson.prompt, color = AcademyColors.ButtonPrimaryText,
                        style = MaterialTheme.typography.displayLarge.copy(fontSize = 42.sp, lineHeight = 50.sp),
                        fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(18.dp))
                    if (answerVisible) {
                        Text(lesson.correctAnswer, color = AcademyColors.ButtonPrimaryText, style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.height(8.dp))
                        Text(lesson.explanation, color = AcademyColors.ButtonPrimaryText.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodyMedium)
                    } else {
                        Text("Piensa en la respuesta antes de revelarla.", color = AcademyColors.ButtonPrimaryText.copy(alpha = 0.62f),
                            style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(Modifier.height(14.dp))
                    Text("◖))", color = AcademyColors.ButtonPrimaryText, fontSize = 22.sp,
                        modifier = Modifier.clip(CircleShape).clickable { answerVisible = true }.padding(8.dp))
                }
            }
            if (!answerVisible) {
                Button(onClick = { answerVisible = true }, modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AcademyColors.ButtonPrimary, contentColor = AcademyColors.ButtonPrimaryText),
                    shape = RoundedCornerShape(14.dp)) { Text("Mostrar respuesta") }
            } else {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = { review(ReviewRating.GOOD) }, enabled = !saving, modifier = Modifier.weight(1f).height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = AcademyColors.ButtonPrimary, contentColor = AcademyColors.ButtonPrimaryText),
                        shape = RoundedCornerShape(14.dp)) { Text("✓ Conocer") }
                    OutlinedButton(onClick = { review(ReviewRating.AGAIN) }, enabled = !saving, modifier = Modifier.weight(1f).height(52.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, AcademyColors.TextPrimary),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = AcademyColors.TextPrimary),
                        shape = RoundedCornerShape(14.dp)) { Text("↻ Dudar") }
                }
            }
        }
        errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        if (saving) Text("Guardando repaso…", color = AcademyColors.TextSecondary)
    }
}
