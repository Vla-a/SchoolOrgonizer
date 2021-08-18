package com.example.schoolorgonizer.lessonSchedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [MessageEntity::class], version = 1)
abstract class MessageDatabase : RoomDatabase() {

    abstract fun messageDao():LessonDao
}

object DataConstructor {
    fun create(context: Context): MessageDatabase =
        Room.databaseBuilder(
            context,
            MessageDatabase::class.java,
            "lesson_database"
        ).build()
}