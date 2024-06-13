package ru.nippyfox.javanavigator.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.nippyfox.javanavigator.data.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE testId = :testId")
    suspend fun getQuestionsForTest(testId: Int): List<Question>

    @Insert
    suspend fun insertQuestion(question: Question)
}