package com.example.schoolorgonizer.notes.database


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM massage_table")
    fun getMessageList(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMessage(note: NoteEntity)

    @Delete
    suspend fun deleteMessage(note: NoteEntity)
}