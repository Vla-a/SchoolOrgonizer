package com.example.schoolorgonizer.notes.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "massage_table")
class NoteEntity(

     @PrimaryKey val message: String,
     val date: String
)