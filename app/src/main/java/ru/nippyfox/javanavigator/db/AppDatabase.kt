package ru.nippyfox.javanavigator.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.nippyfox.javanavigator.data.Lecture
import ru.nippyfox.javanavigator.data.Question
import ru.nippyfox.javanavigator.data.Test
import ru.nippyfox.javanavigator.data.TestResult
import ru.nippyfox.javanavigator.db.dao.LectureDao
import ru.nippyfox.javanavigator.db.dao.QuestionDao
import ru.nippyfox.javanavigator.db.dao.TestDao
import ru.nippyfox.javanavigator.db.dao.TestResultDao

@Database(entities = [Lecture::class, Question::class, Test::class, TestResult::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lectureDao(): LectureDao
    abstract fun questionDao(): QuestionDao
    abstract fun testDao(): TestDao
    abstract fun testResultDao(): TestResultDao
}