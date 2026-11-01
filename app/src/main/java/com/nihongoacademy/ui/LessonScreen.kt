package com.nihongoacademy.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.data.PreferencesStore
import com.nihongoacademy.data.StudySettings
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.StudyProgress
import com.nihongoacademy.ui.theme.AcademyColors
import com.nihongoacademy.ui.theme.NihongoTheme
import kotlinx.coroutines.launch

@Composable
fun LessonScreen(
    navController: NavController,
    languageCode: String = "ja",
    moduleId: String = "module_1",
    lessonId: String = "m1_l1"
) {
    val languages = remember { LanguageCatalogFactory.buildSupportedLanguages() }
    val language = languages.firstOrNull { it.code.equals(languageCode, ignoreCase = true) } ?: languages.first()
    val module = language.modules.firstOrNull { it.id == moduleId } ?: language.modules.first()
    val lesson = module.lessons.firstOrNull { it.id == lessonId } ?: module.lessons.first()
    val context = LocalContext.current
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    val preferencesStore = remember { PreferencesStore(context.applicationContext) }
    val settingsFlow = remember(preferencesStore) { preferencesStore.studySettingsFlow() }
    val settings by settingsFlow.collectAsState(initial = StudySettings())
    val scope = rememberCoroutineScope()
    var selectedAnswer by remember(lesson.id) { mutableStateOf("") }
    var checked by remember(lesson.id) { mutableStateOf(false) }
    var saving by remember { mutableStateOf(false) }
    var saveError by remember { mutableStateOf<String?>(null) }
    var progress by remember { mutableStateOf(StudyProgress(languageCode = language.code)) }

    LaunchedEffect(language.code) {
        progress = progressStore.getProgress(languageCode).copy(languageCode = language.code)
    }
    val isCorrect = selectedAnswer == lesson.correctAnswer

    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 22.dp).padding(bottom = 104.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(Modifier.fillMaxWidth().height(48.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = MaterialTheme.colorScheme.onBackground)
                }
                Text("Lección", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
            }

            Box(Modifier.fillMaxWidth().padding(horizontal = 12.dp).padding(bottom = 26.dp), contentAlignment = Alignment.BottomCenter) {
                Surface(
                    modifier = Modifier.fillMaxWidth().heightIn(min = 230.dp).aspectRatio(1.18f),
                    shape = RoundedCornerShape(26.dp),
                    color = AcademyColors.ButtonPrimary,
                    shadowElevation = 2.dp
                ) {
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text("あ", fontSize = 104.sp, lineHeight = 112.sp, fontWeight = FontWeight.Normal, color = AcademyColors.ButtonPrimaryText)
                        Text("a", style = MaterialTheme.typography.titleLarge, color = AcademyColors.ButtonPrimaryText)
                    }
                }
                FilledIconButton(
                    onClick = { if (settings.showPronunciation && lesson.speechText.isNotBlank()) Unit },
                    modifier = Modifier.size(56.dp).offset(y = 26.dp),
                    shape = CircleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = AcademyColors.Sakura, contentColor = AcademyColors.Background)
                ) {
                    Icon(Icons.Default.VolumeUp, contentDescription = "Audio")
                }
                if (settings.showPronunciation && lesson.speechText.isNotBlank()) {
                    // The existing pronunciation control remains available without changing speech logic.
                    PronunciationButton(lesson.speechText, language.code)
                }
            }

            Text("Trazo del carácter", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
            Surface(
                modifier = Modifier.fillMaxWidth().height(188.dp),
                shape = RoundedCornerShape(18.dp),
                color = AcademyColors.ButtonPrimary
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Canvas(Modifier.fillMaxSize()) {
                        val guide = Color(0xFFD5D5D8)
                        drawLine(guide, androidx.compose.ui.geometry.Offset(size.width / 2f, 0f), androidx.compose.ui.geometry.Offset(size.width / 2f, size.height), 1.2f)
                        drawLine(guide, androidx.compose.ui.geometry.Offset(0f, size.height / 2f), androidx.compose.ui.geometry.Offset(size.width, size.height / 2f), 1.2f)
                        drawLine(guide.copy(alpha = .55f), androidx.compose.ui.geometry.Offset(0f, 0f), androidx.compose.ui.geometry.Offset(size.width, size.height), 1f)
                        drawLine(guide.copy(alpha = .55f), androidx.compose.ui.geometry.Offset(size.width, 0f), androidx.compose.ui.geometry.Offset(0f, size.height), 1f)
                    }
                    Text("あ", fontSize = 112.sp, color = Color(0xFFD8D8DA), fontWeight = FontWeight.Light)
                }
            }

            Text("Comprueba lo aprendido", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
            Text(lesson.prompt, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
            lesson.options.forEach { option ->
                OutlinedButton(
                    onClick = { if (!checked) selectedAnswer = option },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !checked,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onBackground),
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (selectedAnswer == option) AcademyColors.Sakura else MaterialTheme.colorScheme.outline)
                ) { Text(if (selectedAnswer == option) "✓  $option" else option) }
            }
            if (checked) {
                Text(if (isCorrect) "¡Respuesta correcta!" else "Vamos a reforzarlo", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium)
                Text("Respuesta: ${lesson.correctAnswer}\n${lesson.explanation}", color = MaterialTheme.colorScheme.onBackground)
            }
            saveError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            Text("Progreso guardado · ${progress.totalXp} XP · Nivel ${progress.currentLevel}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Button(
            onClick = {
                if (!checked && selectedAnswer.isNotBlank()) {
                    checked = true
                    saving = true
                    saveError = null
                    scope.launch {
                        runCatching {
                            progress = progressStore.recordLessonResult(
                                languageCode = language.code, moduleId = module.id, lessonId = lesson.id,
                                correctAnswers = if (isCorrect) 1 else 0, totalQuestions = 1
                            )
                        }.onFailure {
                            checked = false
                            saveError = "No se pudo guardar el progreso. Vuelve a comprobar la respuesta."
                        }
                        saving = false
                    }
                } else if (checked && !saving) {
                    val nextLesson = module.lessons.firstOrNull { it.id != lesson.id }
                    if (nextLesson != null) navController.navigate("lesson/${languageCode.lowercase()}/${module.id}/${nextLesson.id}")
                    else navController.navigateUp()
                }
            },
            enabled = if (checked) !saving else selectedAnswer.isNotBlank(),
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(horizontal = 22.dp, vertical = 16.dp).height(54.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AcademyColors.ButtonPrimary, contentColor = AcademyColors.ButtonPrimaryText),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(when { saving -> "Guardando..."; checked -> "Continuar →"; else -> "Continuar →" }, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun LessonPreviewContent() { LessonScreen(rememberNavController()) }

@Preview(name = "Lección 360dp", widthDp = 360, heightDp = 800, showBackground = true)
@Composable private fun LessonPreview360() { NihongoTheme { LessonPreviewContent() } }
@Preview(name = "Lección 411dp", widthDp = 411, heightDp = 900, showBackground = true)
@Composable private fun LessonPreview411() { NihongoTheme { LessonPreviewContent() } }
@Preview(name = "Lección 600dp", widthDp = 600, heightDp = 960, showBackground = true)
@Composable private fun LessonPreview600() { NihongoTheme { LessonPreviewContent() } }
