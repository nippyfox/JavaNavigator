package ru.nippyfox.javanavigator.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.nippyfox.javanavigator.data.TestResult

@Dao
interface TestResultDao {
    @Query("SELECT * FROM test_results")
    suspend fun getAllTestResults(): List<TestResult>

    @Query("SELECT * FROM test_results WHERE testId = :testId")
    suspend fun getTestResultByTestId(testId: Int): TestResult?

    @Insert
    suspend fun insertTestResult(testResult: TestResult)

    @Update
    suspend fun updateTestResult(testResult: TestResult)
}