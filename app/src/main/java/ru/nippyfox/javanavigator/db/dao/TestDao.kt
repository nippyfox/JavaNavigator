package ru.nippyfox.javanavigator.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.nippyfox.javanavigator.data.Test

@Dao
interface TestDao {
    @Query("SELECT * FROM tests")
    suspend fun getAllTests(): List<Test>

    @Insert
    suspend fun insertTest(test: Test): Long
}
