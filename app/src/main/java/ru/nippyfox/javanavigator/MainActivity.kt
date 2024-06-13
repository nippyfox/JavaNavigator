package ru.nippyfox.javanavigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.nippyfox.javanavigator.db.AppDatabase
import ru.nippyfox.javanavigator.db.populateDatabase
import ru.nippyfox.javanavigator.ui.screens.LectureScreen
import ru.nippyfox.javanavigator.ui.screens.MainScreen
import ru.nippyfox.javanavigator.ui.screens.SplashScreen
import ru.nippyfox.javanavigator.ui.screens.TestScreen
import ru.nippyfox.javanavigator.ui.theme.JavaNavigatorTheme

class MainActivity : ComponentActivity() {
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "java-navigator-db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    populateDatabase(database, applicationContext)
                }
            })
            .build()

        setContent {
            JavaNavigatorTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("main") { MainScreen(navController, database) }
                    composable("lecture/{lectureId}") { backStackEntry ->
                        val lectureId =
                            backStackEntry.arguments?.getString("lectureId")?.toInt() ?: 0
                        LectureScreen(
                            navController = navController,
                            lectureId = lectureId,
                            database = database
                        )
                    }
                    composable("test/{testId}") { backStackEntry ->
                        val testId = backStackEntry.arguments?.getString("testId")?.toInt() ?: 0
                        TestScreen(
                            navController = navController,
                            testId = testId,
                            database = database
                        )
                    }
                }
            }
        }
    }
}