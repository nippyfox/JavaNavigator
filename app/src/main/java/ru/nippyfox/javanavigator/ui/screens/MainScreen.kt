package ru.nippyfox.javanavigator.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import ru.nippyfox.javanavigator.data.Lecture
import ru.nippyfox.javanavigator.data.Test
import ru.nippyfox.javanavigator.data.TestResult
import ru.nippyfox.javanavigator.db.AppDatabase
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, database: AppDatabase) {
    Log.d("MainScreen", "MainScreen")

    val scope = rememberCoroutineScope()
    var lectures by remember { mutableStateOf(listOf<Lecture>()) }
    var tests by remember { mutableStateOf(listOf<Test>()) }
    var testResults by remember { mutableStateOf(listOf<TestResult>()) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        scope.launch {
            lectures = database.lectureDao().getAllLectures()
            tests = database.testDao().getAllTests()
            testResults = database.testResultDao().getAllTestResults()
            if (lectures.isEmpty()) {
                showDialog = true
            }
        }
    }

    if (showDialog) {
        Scaffold {it ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {}
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    scope.launch {
                        lectures = database.lectureDao().getAllLectures()
                        tests = database.testDao().getAllTests()
                        testResults = database.testResultDao().getAllTestResults()
                    }
                },
                title = { Text(text = "Java Navigator") },
                text = { Text("Добро пожаловать! Спасибо за установку приложения!" +
                        "Желаем Вам приятного изучения языка Java!") },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            scope.launch {
                                lectures = database.lectureDao().getAllLectures()
                                tests = database.testDao().getAllTests()
                                testResults = database.testResultDao().getAllTestResults()
                            }
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }

    val completedTestIds = testResults.map { it.testId }.toSet()
    val totalTestsTaken = testResults.size
    val totalCorrectAnswers = testResults.sumOf { it.correctAnswers }
    val totalQuestionsAnswered = testResults.sumOf { it.totalQuestions }
    val successRate = if (totalQuestionsAnswered > 0) {
        (totalCorrectAnswers.toDouble() / totalQuestionsAnswered * 100).roundToInt()
    } else {
        0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Java Navigator") }
            )
        },
        content = { it ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                item {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Лекции",
                            style = MaterialTheme.typography.headlineSmall
                        ) // Заголовок для секции "Лекции"
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                // Список лекций
                items(lectures) { lecture ->
                    MainListButton(
                        navController,
                        "lecture/${lecture.id}",
                        lecture.title,
                        completedTestIds.contains(lecture.id)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Column {
                            Text(
                                "Тесты",
                                style = MaterialTheme.typography.headlineSmall
                            ) // Заголовок для секции "Тесты"
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                StatColumn("Пройдено тестов", "$totalTestsTaken")
                                StatColumn("Процент правильных ответов", "$successRate%")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Список тестов
                items(tests) { test ->
                    MainListButton(
                        navController,
                        "test/${test.id}",
                        test.title,
                        completedTestIds.contains(test.id)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

@Composable
private fun StatColumn(text: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun MainListButton(
    navController: NavHostController,
    route: String,
    title: String,
    isCompleted: Boolean = false
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Button(
            onClick = {
                navController.navigate(route)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = if (isCompleted) ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer) else ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.primary
            ),
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center
            )
        }
    }
}