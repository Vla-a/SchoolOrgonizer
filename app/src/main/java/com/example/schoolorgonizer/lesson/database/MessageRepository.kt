package com.example.schoolorgonizer.lesson.database

import com.example.schoolorgonizer.lesson.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessageRepository(
    private val lessonDao: LessonDao
) {

    fun getLessonList(): Flow<List<Message>> =
        lessonDao.getLessonList().map { messageEntities ->
            messageEntities.map { messageEntities ->
                Message(messageEntities.name, messageEntities.date, messageEntities.id)
            }
        }

    suspend fun addLesson(lesson: MessageEntity) {
        lessonDao.addLesson(lesson)

    }

    suspend fun deleteLesson(lesson: Message) {

        lessonDao.deleteLesson(lesson.entity())
    }

    private fun Message.entity() = MessageEntity(this.name, this.date, this.id)
}