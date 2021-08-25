package com.example.schoolorgonizer.lessonSchedule.daySchedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolorgonizer.lessonSchedule.Lessons
import com.example.schoolorgonizer.lessonSchedule.database.LessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DayScheduleViewModel(

    private val lessonRepository: LessonRepository
) : ViewModel() {

    val lessonsListLiveData: MutableLiveData<List<Lessons>> = MutableLiveData()

    fun getList(date: String) {
        viewModelScope.launch(Dispatchers.IO) {

                lessonsListLiveData.postValue(lessonRepository.getDayList(date))
        }

    }

    }
