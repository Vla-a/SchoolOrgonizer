package com.example.schoolorgonizer.lesson.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolorgonizer.lesson.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditViewModel(

    private val editRepository: EditRepository
) : ViewModel() {

    val messageListLiveData: MutableLiveData<List<Message>> = MutableLiveData()



    fun getList(date: String) {
        viewModelScope.launch(Dispatchers.IO) {

                messageListLiveData.postValue(editRepository.getDayList(date))
        }
        }

    }
