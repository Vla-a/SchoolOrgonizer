package com.example.schoolorgonizer.lessonSchedule.database

import com.example.schoolorgonizer.lessonSchedule.Lessons
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LessonRepository(
    private val lessonDao: LessonDao
) {

    fun getLessonList(): Flow<List<Lessons>> =
        lessonDao.getLessonList().map { lessonEntities ->
            lessonEntities.map { messageEntities ->
                Lessons(messageEntities.name, messageEntities.date, messageEntities.id)
            }
        }

    suspend fun getDayList(date: String): List<Lessons> =
        lessonDao.getLessonList(date).map {
            Lessons(it.name, it.date, it.id)
        }

    suspend fun addLesson(lesson: LessonEntity) {
        lessonDao.addLesson(lesson)

    }

    suspend fun deleteLesson(lesson: Lessons) {

        lessonDao.deleteLesson(lesson.entity())
    }

    private fun Lessons.entity() = LessonEntity(this.name, this.date, this.id)
}