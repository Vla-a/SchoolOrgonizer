package com.example.schoolorgonizer.lessonSchedule.database

import com.example.schoolorgonizer.lessonSchedule.Lessons
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessageRepository(
    private val lessonDao: LessonDao
) {

    fun getLessonList(): Flow<List<Lessons>> =
        lessonDao.getLessonList().map { messageEntities ->
            messageEntities.map { messageEntities ->
                Lessons(messageEntities.name, messageEntities.date, messageEntities.id)
            }
        }

    suspend fun addLesson(lesson: MessageEntity) {
        lessonDao.addLesson(lesson)

    }

    suspend fun deleteLesson(lesson: Lessons) {

        lessonDao.deleteLesson(lesson.entity())
    }

    private fun Lessons.entity() = MessageEntity(this.name, this.date, this.id)
}