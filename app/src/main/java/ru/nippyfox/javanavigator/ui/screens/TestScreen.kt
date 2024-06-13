package ru.nippyfox.javanavigator.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.nippyfox.javanavigator.data.Question
import ru.nippyfox.javanavigator.data.TestResult
import ru.nippyfox.javanavigator.db.AppDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(navController: NavHostController, testId: Int, database: AppDatabase) {
    val scope = rememberCoroutineScope()
    var questions by remember { mutableStateOf(listOf<Question>()) }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var correctAnswersCount by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    LaunchedEffect(testId) {
        scope.launch {
            questions = database.questionDao().getQuestionsForTest(testId)
        }
    }

    val currentQuestion = questions.getOrNull(currentQuestionIndex)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Тест") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        },
        content = {it ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                if (showResult) {
                    Text(
                        text = "Вы ответили на $correctAnswersCount из ${questions.size} вопросов правильно!",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Ваш результат: ${correctAnswersCount * 100 / questions.size}%",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            scope.launch {
                                val existingResult = database.testResultDao().getTestResultByTestId(testId)
                                if (existingResult == null || correctAnswersCount > existingResult.correctAnswers) {
                                    val testResult = TestResult(
                                        id = existingResult?.id ?: 0,
                                        testId = testId,
                                        correctAnswers = correctAnswersCount,
                                        totalQuestions = questions.size
                                    )
                                    if (existingResult == null) {
                                        database.testResultDao().insertTestResult(testResult)
                                    } else {
                                        database.testResultDao().updateTestResult(testResult)
                                    }
                                }
                                navController.navigate("main") {
                                    popUpTo("main") { inclusive = true }
                                }
                            }
                        },
                    ) {
                        Text("Вернуться в личный кабинет")
                    }
                } else if (currentQuestion != null) {
                    Text(
                        text = currentQuestion.question,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    )
                    currentQuestion.options.forEachIndexed { index, option ->
                        Button(
                            onClick = {
                                if (index == currentQuestion.correctAnswerIndex) {
                                    correctAnswersCount++
                                }
                                if (currentQuestionIndex < questions.size - 1) {
                                    currentQuestionIndex++
                                } else {
                                    showResult = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp, horizontal = 16.dp)
                        ) {
                            Text(text = option,
                                textAlign = TextAlign.Center
                                )
                        }
                    }
                }
                else {
                    CircularProgressIndicator()
                }
            }
        }
    )
}