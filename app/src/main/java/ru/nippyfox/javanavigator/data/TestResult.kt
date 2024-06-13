package ru.nippyfox.javanavigator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_results")
data class TestResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val testId: Int,
    val correctAnswers: Int,
    val totalQuestions: Int
)