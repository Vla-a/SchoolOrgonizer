package com.example.schoolorgonizer.lessonSchedule.database


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {

    @Query("SELECT * FROM lesson_table  ORDER BY id")
    fun getLessonList(): Flow<List<MessageEntity>>

    @Query("SELECT * FROM lesson_table WHERE date LIKE :date ORDER BY id")
    suspend fun getLessonList(date: String): List<MessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLesson(message: MessageEntity)

    @Delete
    suspend fun deleteLesson(message: MessageEntity)
}