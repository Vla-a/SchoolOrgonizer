package com.example.schoolorgonizer.lessonSchedule.database


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {

    @Query("SELECT * FROM lesson_table  ORDER BY id")
    fun getLessonList(): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lesson_table WHERE date LIKE :date ORDER BY id")
    suspend fun getLessonList(date: String): List<LessonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLesson(lesson: LessonEntity)

    @Delete
    suspend fun deleteLesson(lesson: LessonEntity)
}