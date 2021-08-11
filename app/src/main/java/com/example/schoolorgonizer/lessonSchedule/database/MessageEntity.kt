package com.example.schoolorgonizer.lessonSchedule.database

import androidx.room.Entity

@Entity(tableName = "lesson_table", primaryKeys = ["id", "date"] )
class MessageEntity(
 val name: String,
   val date: String,
 val id: Int
)