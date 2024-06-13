package ru.nippyfox.javanavigator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tests")
data class Test(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String
)