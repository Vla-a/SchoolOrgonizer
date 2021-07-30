package com.example.schoolorgonizer

import android.app.AlarmManager
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.schoolorgonizer.notes.NoteFragmentViewModel
import com.example.schoolorgonizer.notes.database.DatabaseConstructor
import com.example.schoolorgonizer.notes.database.NoteDao
import com.example.schoolorgonizer.notes.database.NoteDatabase
import com.example.schoolorgonizer.notes.database.NoteRepository
import com.example.schoolorgonizer.weather.WeatherViewModel
import com.example.schoolorgonizer.weather.restApi.ApiRepository
import com.example.schoolorgonizer.weather.restApi.WeatherApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module

@KoinApiExtension
class MySuperApp : Application(), KoinComponent              {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@MySuperApp)
            modules(listOf(viewModels, repository, api, systemModule, storageModule))
        }
    }

    private val viewModels = module {
        viewModel { WeatherViewModel(get()) }
        viewModel { NoteFragmentViewModel(get()) }
    }

    private val repository = module {
        factory { ApiRepository(get()) }
        factory { NoteRepository(get()) }
    }

    private val api = module {
        single { WeatherApi.get() }
    }
    private val systemModule = module {
        factory { get<Context>().getSystemService(ALARM_SERVICE) as AlarmManager }
    }
    private val storageModule = module {
        single { DatabaseConstructor.create(get()) }  //создаем синглтон базы данных
        factory { get<NoteDatabase>().NoteDao() } //предоставляем доступ для конкретной Dao (в нашем случае NotesDao)
      }

}