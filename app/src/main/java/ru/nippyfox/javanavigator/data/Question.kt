package ru.nippyfox.javanavigator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val testId: Int,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)