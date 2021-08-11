package com.example.schoolorgonizer.lesson.edit

import com.example.schoolorgonizer.lesson.Message
import com.example.schoolorgonizer.lesson.database.LessonDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class EditRepository(
    private val lessonDao: LessonDao
) {

    suspend fun getDayList(date: String): List<Message> =
        lessonDao.getLessonList(date).map {
            Message(it.name, it.date, it.id)
        }
//            it.map { messageEntities ->
//                Message(messageEntities.name, messageEntities.date, messageEntities.id)


}