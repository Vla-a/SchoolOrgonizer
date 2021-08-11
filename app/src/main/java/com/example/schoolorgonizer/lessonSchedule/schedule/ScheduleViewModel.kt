package com.example.schoolorgonizer.lessonSchedule.schedule


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.schoolorgonizer.lessonSchedule.Lessons
import com.example.schoolorgonizer.lessonSchedule.database.MessageEntity
import com.example.schoolorgonizer.lessonSchedule.database.MessageRepository
import kotlinx.coroutines.launch

class ScheduleViewModel(
   private val messageRepository: MessageRepository
) : ViewModel() {

    val lessonsListLiveData: LiveData<List<Lessons>> =
        messageRepository.getLessonList().asLiveData()

    fun addMessageToDatabase(message: String, data: String, id: Int) {

        val newLesson = MessageEntity(message, data, id)

        viewModelScope.launch {
            messageRepository.addLesson(newLesson)
        }
    }

    fun deleteMessage(lesson: Lessons) {
        viewModelScope.launch {
            messageRepository.deleteLesson(lesson)
        }
    }
}
