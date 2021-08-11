package com.example.schoolorgonizer.lessonSchedule.daySchedule

import com.example.schoolorgonizer.lessonSchedule.Lessons
import com.example.schoolorgonizer.lessonSchedule.database.LessonDao

class DayScheduleRepository(
    private val lessonDao: LessonDao
) {

    suspend fun getDayList(date: String): List<Lessons> =
        lessonDao.getLessonList(date).map {
            Lessons(it.name, it.date, it.id)
        }
//            it.map { messageEntities ->
//                Message(messageEntities.name, messageEntities.date, messageEntities.id)


}