package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.nihongoacademy.model.ExamDifficulty
import com.nihongoacademy.model.ExamModuleType
import com.nihongoacademy.model.ExamQuestion
import com.nihongoacademy.data.SessionStore
import com.nihongoacademy.data.StudyProgressStore
import com.nihongoacademy.ui.theme.AcademyColors
import com.nihongoacademy.ui.theme.NihongoTheme
import kotlinx.coroutines.launch

@Composable
fun ExamScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionStore = remember { SessionStore(context.applicationContext) }
    val progressStore = remember { StudyProgressStore(context.applicationContext) }
    val scope = rememberCoroutineScope()
    var languageCode by remember { mutableStateOf("ja") }
    var savedResults by remember { mutableStateOf(emptyList<com.nihongoacademy.model.StudyResult>()) }
    var savingResult by remember { mutableStateOf(false) }
    var saveError by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        languageCode = sessionStore.readLanguage() ?: "ja"
        savedResults = progressStore.getProgress(languageCode).recentResults.filter { it.isExam }.asReversed()
    }
    val questionBank = remember {
        listOf(
            ExamQuestion(
                id = "read_1",
                module = ExamModuleType.READING,
                difficulty = ExamDifficulty.BASIC,
                prompt = "Lee la frase: 「ありがとうございます。」 ¿Qué significa en español?",
                options = listOf("Gracias", "Hola", "Agua", "Hasta luego"),
                correctAnswer = "Gracias",
                feedback = "Correcto. ありがとうございます se usa para expresar agradecimiento.",
                hint = "Se usa cuando alguien hace algo por ti."
            ),
            ExamQuestion(
                id = "read_2",
                module = ExamModuleType.READING,
                difficulty = ExamDifficulty.INTERMEDIATE,
                prompt = "Lee la oración: 今日はとても忙しいです。 ¿Qué expresa la frase?",
                options = listOf("Hoy estoy muy ocupado", "Hoy hace buen tiempo", "Hoy voy a estudiar", "Hoy no quiero salir"),
                correctAnswer = "Hoy estoy muy ocupado",
                feedback = "Correcto. 忙しい significa ocupado o muy cargado.",
                hint = "Relaciona la palabra 忙しい con la idea de mucho trabajo."
            ),
            ExamQuestion(
                id = "write_1",
                module = ExamModuleType.WRITING,
                difficulty = ExamDifficulty.BASIC,
                prompt = "Escribe la traducción de: これは本です。",
                options = emptyList(),
                correctAnswer = "Esto es un libro",
                feedback = "Correcto. これは significa esto y 本 significa libro.",
                hint = "La frase se puede traducir como: esto es un libro."
            ),
            ExamQuestion(
                id = "write_2",
                module = ExamModuleType.WRITING,
                difficulty = ExamDifficulty.INTERMEDIATE,
                prompt = "Completa la traducción: 彼は学生です。",
                options = emptyList(),
                correctAnswer = "El es estudiante",
                feedback = "Correcto. 彼 significa él y 学生 significa estudiante.",
                hint = "La estructura es: él / es / estudiante."
            ),
            ExamQuestion(
                id = "listen_1",
                module = ExamModuleType.LISTENING,
                difficulty = ExamDifficulty.BASIC,
                prompt = "Escucha la expresión: 'おはようございます' y elige la respuesta correcta.",
                options = listOf("Buenos días", "Buenas tardes", "Buenas noches", "Adiós"),
                correctAnswer = "Buenos días",
                feedback = "Correcto. おはようございます es una forma educada de decir buenos días.",
                hint = "Se usa al empezar el día."
            ),
            ExamQuestion(
                id = "listen_2",
                module = ExamModuleType.LISTENING,
                difficulty = ExamDifficulty.ADVANCED,
                prompt = "Escucha la idea: 'もう行きます。' ¿Cuál es la mejor interpretación?",
                options = listOf("Me voy ya", "Estoy cansado", "Quiero comer", "Voy a descansar"),
                correctAnswer = "Me voy ya",
                feedback = "Correcto. もう行きます indica que te vas de inmediato.",
                hint = "Piensa en una acción que se hace enseguida."
            )
        )
    }


    var showQuestions by remember { mutableStateOf(false) }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf("") }
    var writtenAnswer by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }


    if (!showQuestions) {
        Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            Column(
                Modifier.fillMaxSize().padding(horizontal = 22.dp).padding(bottom = 22.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(Modifier.fillMaxWidth().height(54.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Exámenes", modifier = Modifier.weight(1f), style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración", tint = MaterialTheme.colorScheme.onBackground)
                    }
                }
                Spacer(Modifier.height(12.dp))
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    color = AcademyColors.ButtonPrimary,
                    shadowElevation = 2.dp
                ) {
                    Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Box(
                            Modifier.size(76.dp).background(AcademyColors.Background, RoundedCornerShape(18.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("JLPT\nN5", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, lineHeight = 24.sp)
                        }
                        Text("Examen de Nivel N5", style = MaterialTheme.typography.headlineSmall, color = AcademyColors.ButtonPrimaryText)
                        Text("Evalúa tus conocimientos", style = MaterialTheme.typography.bodyLarge, color = AcademyColors.ButtonPrimaryText.copy(alpha = .72f))
                        listOf("Comprensión de lectura", "Comprensión auditiva", "Vocabulario y gramática").forEach { item ->
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Checkbox(checked = true, onCheckedChange = null, colors = CheckboxDefaults.colors(checkedColor = AcademyColors.Sakura, checkmarkColor = AcademyColors.Background))
                                Text(item, style = MaterialTheme.typography.bodyMedium, color = AcademyColors.ButtonPrimaryText)
                            }
                        }
                        Button(
                            onClick = { showQuestions = true },
                            modifier = Modifier.fillMaxWidth().height(54.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = AcademyColors.Background, contentColor = AcademyColors.TextPrimary)
                        ) { Text("Iniciar examen →", style = MaterialTheme.typography.titleMedium) }
                    }
                }
                Spacer(Modifier.weight(1f))
            }
            // Landscape artwork remains represented by the app's existing vector resource, when available.
            Text("鳥居     富士山     五重塔", modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp), color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .22f), style = MaterialTheme.typography.headlineSmall)
        }
    } else {
    val currentQuestion = questionBank[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("Examen por módulos", style = MaterialTheme.typography.headlineMedium)
        Text("Lectura · Escritura · Comprensión auditiva", style = MaterialTheme.typography.bodyLarge)

        if (!finished) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(currentQuestion.module.label, style = MaterialTheme.typography.labelLarge)
                    Text(currentQuestion.difficulty.label, style = MaterialTheme.typography.bodyMedium)
                    Text(currentQuestion.prompt, style = MaterialTheme.typography.titleLarge)

                    if (currentQuestion.module == ExamModuleType.WRITING) {
                        OutlinedTextField(
                            value = writtenAnswer,
                            onValueChange = { writtenAnswer = it },
                            label = { Text("Escribe tu respuesta") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        currentQuestion.options.forEach { option ->
                            val isSelected = selectedAnswer == option
                            OutlinedButton(
                                onClick = { selectedAnswer = option },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(option)
                            }
                        }
                    }

                    Text(currentQuestion.hint, style = MaterialTheme.typography.bodyMedium)
                }
            }

            feedback?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (it.contains("Correcto")) MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                            else MaterialTheme.colorScheme.error.copy(alpha = 0.08f),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(14.dp)
                ) {
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    enabled = if (currentQuestion.module == ExamModuleType.WRITING) writtenAnswer.isNotBlank() else selectedAnswer.isNotBlank(),
                    onClick = {
                        val answer = if (currentQuestion.module == ExamModuleType.WRITING) writtenAnswer else selectedAnswer
                        val isCorrect = answer.equals(currentQuestion.correctAnswer, ignoreCase = true)

                        if (isCorrect) {
                            score += 1
                            feedback = "Correcto. ${currentQuestion.feedback}"
                        } else {
                            feedback = "No es correcto. La respuesta esperada era: ${currentQuestion.correctAnswer}."
                        }

                        if (currentQuestionIndex < questionBank.lastIndex) {
                            currentQuestionIndex += 1
                            selectedAnswer = ""
                            writtenAnswer = ""
                        } else {
                            finished = true
                            savingResult = true
                            saveError = null
                            scope.launch {
                                runCatching {
                                    val updated = progressStore.recordExamResult(
                                        languageCode = languageCode,
                                        moduleId = "general_n5",
                                        examId = "general_n5",
                                        correctAnswers = score,
                                        totalQuestions = questionBank.size
                                    )
                                    savedResults = updated.recentResults.filter { it.isExam }.asReversed()
                                }.onFailure {
                                    saveError = "No se pudo guardar este resultado. Revisa tu conexión de almacenamiento e inténtalo de nuevo."
                                }
                                savingResult = false
                            }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (currentQuestionIndex < questionBank.lastIndex) "Siguiente" else "Finalizar")
                }

                Button(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Salir")
                }
            }
        }

        if (finished) {
            val percent = (score * 100) / questionBank.size
            val recommendation = when {
                percent >= 80 -> "Excelente. Has consolidado bien el bloque. El siguiente paso es avanzar a un nivel superior con frases más complejas."
                percent >= 60 -> "Buen avance. Repite las secciones de lectura y escritura para reforzar el uso práctico de cada estructura."
                else -> "Necesitas reforzar la base. Vuelve a la lección, repite vocabulario y vuelve a hacer el examen con más calma."
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Resultado final", style = MaterialTheme.typography.titleMedium)
                    Text("Puntuación: $score / ${questionBank.size} · $percent%", style = MaterialTheme.typography.headlineMedium)
                    Text(recommendation, style = MaterialTheme.typography.bodyLarge)
                    Text("Recomendaciones de estudio:", style = MaterialTheme.typography.titleMedium)
                    Text("• Repite los bloques de saludos y frases útiles\n• Practica la escritura de oraciones cortas\n• Vuelve a evaluar la lectura con contexto real", style = MaterialTheme.typography.bodyMedium)
                }
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Historial de rendimiento", style = MaterialTheme.typography.titleMedium)
                    if (savedResults.isEmpty()) {
                        Text(
                            "Todavía no hay resultados anteriores guardados.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        savedResults.take(5).forEach { result ->
                            Text(
                                "${result.correctAnswers} de ${result.totalQuestions} respuestas · ${if (result.totalQuestions > 0) result.correctAnswers * 100 / result.totalQuestions else 0}%",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            saveError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            Button(
                onClick = { navController.navigateUp() },
                enabled = !savingResult,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (savingResult) "Guardando resultado…" else "Volver al panel")
            }
        }
    }
}

}

@Composable private fun ExamPreviewContent() { ExamScreen(rememberNavController()) }
@Preview(name = "Exámenes 360dp", widthDp = 360, heightDp = 800, showBackground = true)
@Composable private fun ExamPreview360() { NihongoTheme { ExamPreviewContent() } }
@Preview(name = "Exámenes 411dp", widthDp = 411, heightDp = 900, showBackground = true)
@Composable private fun ExamPreview411() { NihongoTheme { ExamPreviewContent() } }
@Preview(name = "Exámenes 600dp", widthDp = 600, heightDp = 960, showBackground = true)
@Composable private fun ExamPreview600() { NihongoTheme { ExamPreviewContent() } }

