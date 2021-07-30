package com.example.schoolorgonizer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schoolorgonizer.alarmсlock.AlarmClockFragment
import com.example.schoolorgonizer.callSchedule.CallScheduleFragment
import com.example.schoolorgonizer.databinding.ActivityMainBinding
import com.example.schoolorgonizer.notes.NoteFragment
import com.example.schoolorgonizer.weather.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.liveData.observe(this, {
            // Погода
            update()
        })
        // Расписание уроков
        binding.btnCallSchedule.setOnClickListener {

            supportFragmentManager.beginTransaction()
                .replace(binding.cFragmentCall.id, CallScheduleFragment(), CallScheduleFragment.TAG)
                .addToBackStack(null)
                .commit()
        }

        // Новости школы
        binding.btnNews.setOnClickListener {
            binding.btnNews.setOnClickListener {
                val url =
                    "https://yandex.by/search/?text=onliner.by+%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F+%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0&lr=157&src=suggest_B"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
        //Будила
        binding.btnAlarmClock.setOnClickListener {
//            val intent = Intent(this, AlarmClockActivity::class.java)
//            startActivity(intent)
                supportFragmentManager.beginTransaction()
                    .replace(binding.cFragmentCall.id, AlarmClockFragment(), AlarmClockFragment.TAG)
                    .addToBackStack(null)
                    .commit()
        }
        //Заметки
        binding.btnNotes.setOnClickListener {
//            val intent = Intent(this, HomeWork15Activity::class.java)
//            startActivity(intent)
            supportFragmentManager.beginTransaction()
                .replace(binding.cFragmentCall.id, NoteFragment(), NoteFragment.TAG)
                .addToBackStack(null)
                .commit()
        }
    }
    private fun update() {
        val resultWeather = viewModel.liveData.value

        binding.tvCity.text = resultWeather?.name
        binding.tvTemp.text = "${resultWeather?.temp?.toInt()} °C"
        binding.tvCloud.text = resultWeather?.description
//            ?.replace("[", "")
//            ?.replace("]", "")
//        val url = "https://openweathermap.org/img/wn/${resultWeather?.iconId}@2x.png"//тут падает
//            .replace("[", "")
//            .replace("]", "")
//
//        Glide
//            .with(binding.root)
//            .load(url)
////            .placeholder(R.drawable.ic_meteocast_sun_and_cloud)
//            .into(binding.ivIcon)
    }
}

