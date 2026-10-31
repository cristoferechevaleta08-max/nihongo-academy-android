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

private data class ExamQuestion(
    val title: String,
    val prompt: String,
    val type: QuestionType,
    val options: List<String> = emptyList(),
    val correctAnswer: String,
    val hint: String
)

private enum class QuestionType {
    MULTIPLE_CHOICE,
    READING,
    WRITING
}

@Composable
fun ExamScreen(navController: NavController) {
    val questions = remember {
        listOf(
            ExamQuestion(
                title = "Pregunta 1",
                prompt = "Lee la frase: 「ありがとうございます。」 ¿Qué significa en español?",
                type = QuestionType.READING,
                options = listOf("Gracias", "Hola", "Agua", "Hasta luego"),
                correctAnswer = "Gracias",
                hint = "Se usa para agradecer algo."
            ),
            ExamQuestion(
                title = "Pregunta 2",
                prompt = "Completa la frase: 今日は ___ です。",
                type = QuestionType.MULTIPLE_CHOICE,
                options = listOf("bien", "buena", "feliz", "claro"),
                correctAnswer = "bien",
                hint = "La frase se usa para decir: 'Hoy está bien'."
            ),
            ExamQuestion(
                title = "Pregunta 3",
                prompt = "Escribe la traducción de: これは本です。",
                type = QuestionType.WRITING,
                correctAnswer = "Esto es un libro",
                hint = "La estructura es: esto / es / libro."
            )
        )
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf("") }
    var writtenAnswer by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }

    val currentQuestion = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("Examen de nivel", style = MaterialTheme.typography.headlineMedium)
        Text("Lectura + escritura · bloque de aplicación práctica", style = MaterialTheme.typography.bodyLarge)

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(currentQuestion.title, style = MaterialTheme.typography.labelLarge)
                Text(currentQuestion.prompt, style = MaterialTheme.typography.titleLarge)

                if (currentQuestion.type == QuestionType.MULTIPLE_CHOICE || currentQuestion.type == QuestionType.READING) {
                    currentQuestion.options.forEach { option ->
                        val selected = selectedAnswer == option
                        OutlinedButton(
                            onClick = { selectedAnswer = option },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(option)
                        }
                    }
                }

                if (currentQuestion.type == QuestionType.WRITING) {
                    OutlinedTextField(
                        value = writtenAnswer,
                        onValueChange = { writtenAnswer = it },
                        label = { Text("Escribe tu respuesta") },
                        modifier = Modifier.fillMaxWidth()
                    )
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
                    val currentAnswer = if (currentQuestion.type == QuestionType.WRITING) writtenAnswer else selectedAnswer
                    val isCorrect = currentAnswer.equals(currentQuestion.correctAnswer, ignoreCase = true)

                    if (isCorrect) {
                        score += 1
                        feedback = "Correcto. Has respondido bien a esta pregunta."
                    } else {
                        feedback = "No es correcto. La respuesta esperada era: ${currentQuestion.correctAnswer}."
                    }

                    if (currentIndex < questions.lastIndex) {
                        currentIndex += 1
                        selectedAnswer = ""
                        writtenAnswer = ""
                    } else {
                        finished = true
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (currentIndex < questions.lastIndex) "Siguiente" else "Finalizar")
            }

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Salir")
            }
        }

        if (finished) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Resultado final", style = MaterialTheme.typography.titleMedium)
                    Text("Has obtenido $score de ${questions.size} puntos.", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        when {
                            score >= 2 -> "Muy buen nivel. Estás aplicando bien lo aprendido."
                            score == 1 -> "Buen avance. Repasa el bloque para reforzar la lectura y la escritura."
                            else -> "Necesitas más práctica. Repite la lección y vuelve a intentarlo."
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

























































































































































































































































































































































































































































