package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nihongoacademy.model.ExamDifficulty
import com.nihongoacademy.model.ExamHistoryEntry
import com.nihongoacademy.model.ExamModuleType
import com.nihongoacademy.model.ExamQuestion

@Composable
fun ExamScreen(navController: NavController) {
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

    val history = remember {
        listOf(
            ExamHistoryEntry("Saludos", 86, 12, "Hace 2 días"),
            ExamHistoryEntry("Vocabulario", 74, 9, "Hace 4 días"),
            ExamHistoryEntry("Frases útiles", 91, 15, "Hace 1 semana"),
            ExamHistoryEntry("Lectura", 68, 7, "Hace 5 días")
        )
    }

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf("") }
    var writtenAnswer by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }

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
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (currentQuestionIndex < questionBank.lastIndex) "Siguiente" else "Finalizar")
                }

                Button(
                    onClick = { navController.navigate("home") },
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
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Historial de rendimiento", style = MaterialTheme.typography.titleMedium)
                    history.forEach { entry ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(entry.topic)
                            Text("${entry.score}% · ${entry.attempts} intentos")
                        }
                    }
                }
            }

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al panel")
            }
        }
    }
}
