package ru.nippyfox.javanavigator.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.jeziellago.compose.markdowntext.MarkdownText
import kotlinx.coroutines.launch
import ru.nippyfox.javanavigator.data.Lecture
import ru.nippyfox.javanavigator.db.AppDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LectureScreen(navController: NavHostController, lectureId: Int, database: AppDatabase) {
    val scope = rememberCoroutineScope()
    var lecture by remember { mutableStateOf<Lecture?>(null) }

    LaunchedEffect(lectureId) {
        scope.launch {
            lecture = database.lectureDao().getLectureById(lectureId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(lecture?.title ?: "Загрузка...") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    lecture?.let {
                        LazyColumn {
                            item {
                                MarkdownText(markdown = it.content)
                            }
                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                            item {
                                Button(
                                    onClick = { navController.navigate("test/$lectureId") },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Перейти к тесту по лекции")
                                }
                            }
                        }
                    } ?: run {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    )
}