package com.example.schoolorgonizer.lesson


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.schoolorgonizer.lesson.database.MessageEntity
import com.example.schoolorgonizer.lesson.database.MessageRepository
import kotlinx.coroutines.launch

class SchedueViewModel(
   private val messageRepository: MessageRepository
) : ViewModel() {

    val messageListLiveData: LiveData<List<Message>> =
        messageRepository.getLessonList().asLiveData()

    fun addMessageToDatabase(message: String, data: String, id: Int) {

        val newLesson = MessageEntity(message, data, id)

        viewModelScope.launch {
            messageRepository.addLesson(newLesson)
        }
    }

    fun deleteMessage(lesson: Message) {
        viewModelScope.launch {
            messageRepository.deleteLesson(lesson)
        }
    }
}
