package com.example.schoolorgonizer.lessonSchedule.daySchedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolorgonizer.lessonSchedule.Lessons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DayScheduleViewModel(

    private val dayScheduleRepository: DayScheduleRepository
) : ViewModel() {

    val lessonsListLiveData: MutableLiveData<List<Lessons>> = MutableLiveData()



    fun getList(date: String) {
        viewModelScope.launch(Dispatchers.IO) {

                lessonsListLiveData.postValue(dayScheduleRepository.getDayList(date))
            delay(4000)
        }

    }

    }
