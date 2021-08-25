package com.example.schoolorgonizer.lessonSchedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [LessonEntity::class], version = 1)
abstract class LessonDatabase : RoomDatabase() {

    abstract fun lessonDao():LessonDao
}

object DataConstructor {
    fun create(context: Context): LessonDatabase =
        Room.databaseBuilder(
            context,
            LessonDatabase::class.java,
            "lesson_database"
        ).build()
}