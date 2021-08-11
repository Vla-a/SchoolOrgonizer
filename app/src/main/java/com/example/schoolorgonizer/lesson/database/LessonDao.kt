package com.example.schoolorgonizer.lesson.database


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {

    @Query("SELECT * FROM lesson_table")
    fun getLessonList(): Flow<List<MessageEntity>>

    @Query("SELECT * FROM lesson_table WHERE date LIKE :date")
    suspend fun getLessonList(date: String): List<MessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLesson(message: MessageEntity)

    @Delete
    suspend fun deleteLesson(message: MessageEntity)
}