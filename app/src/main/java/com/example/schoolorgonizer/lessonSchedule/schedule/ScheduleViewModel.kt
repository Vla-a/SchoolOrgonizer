package com.example.schoolorgonizer.lessonSchedule.schedule


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.schoolorgonizer.lessonSchedule.Lessons
import com.example.schoolorgonizer.lessonSchedule.database.LessonEntity
import com.example.schoolorgonizer.lessonSchedule.database.LessonRepository
import kotlinx.coroutines.launch

class ScheduleViewModel(
   private val lessonRepository: LessonRepository
) : ViewModel() {

    val lessonsListLiveData: LiveData<List<Lessons>> =
        lessonRepository.getLessonList().asLiveData()

    fun addMessageToDatabase(message: String, data: String, id: Int) {

        val newLesson = LessonEntity(message, data, id)

        viewModelScope.launch {
            lessonRepository.addLesson(newLesson)
        }
    }

    fun deleteMessage(lesson: Lessons) {
        viewModelScope.launch {
            lessonRepository.deleteLesson(lesson)
        }
    }
}
