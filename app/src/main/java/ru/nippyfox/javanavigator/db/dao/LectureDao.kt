package ru.nippyfox.javanavigator.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.nippyfox.javanavigator.data.Lecture

@Dao
interface LectureDao {
    @Query("SELECT * FROM lectures")
    suspend fun getAllLectures(): List<Lecture>

    @Query("SELECT * FROM lectures WHERE id = :lectureId")
    suspend fun getLectureById(lectureId: Int): Lecture

    @Insert
    suspend fun insertLecture(lecture: Lecture)
}