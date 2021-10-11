package com.example.schoolorgonizer.notes

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.schoolorgonizer.databinding.FragmentAddNoteBinding
import com.example.schoolorgonizer.notes.notification.NoteService
import com.example.schoolorgonizer.notes.notification.NotificationReceiver
import org.koin.android.ext.android.inject
import java.util.*


class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null
    private var alarmDate: Calendar = Calendar.getInstance().apply { time = Date() }
    private val alarmManager: AlarmManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnReturn.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding!!.confirm.setOnClickListener {

            if (binding!!.etNote.text.isNotBlank()) {


                with(binding?.calendarView) {
                    this?.year?.let { it1 -> alarmDate.set(Calendar.YEAR, it1) }
                    this?.month?.let { it1 -> alarmDate.set(Calendar.MONTH, it1) }
                    this?.dayOfMonth?.let { it1 -> alarmDate.set(Calendar.DAY_OF_MONTH, it1) }
                }

                it.findNavController().popBackStack()

                setFragmentResult("TEST1", Bundle().apply {
                    val note = binding!!.etNote.text
                    val dat =
                        "Дата: ${binding!!.calendarView.dayOfMonth}.${binding!!.calendarView.month}.${binding!!.calendarView.year}" +
                                "  ${binding!!.timePicker.hour}:${binding!!.timePicker.minute}"
                    putString("KEY5", note.toString())
                    putString("KEY6", dat)
                    if (binding!!.alarmSwitch.isChecked) {
                        startAlarmService(note.toString(), dat)
                    }

                })
            } else {
                Toast.makeText(requireContext(), " Нет текста заметки!", Toast.LENGTH_LONG)
                    .show()
            }

        }
    }

    fun startAlarmService(note: String, date: String) {

        val notifuBpouk = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(NotificationReceiver.NOTIFICATION_KEY_NOTE_TEXT, note)
            putExtra(NotificationReceiver.NOTIFICATION_KEY_NOTE_OWNER, date)
        }
        val pendingIntent1 = PendingIntent.getBroadcast(
            context?.applicationContext,
            1,
            notifuBpouk,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmDate.timeInMillis, pendingIntent1)

    }
}