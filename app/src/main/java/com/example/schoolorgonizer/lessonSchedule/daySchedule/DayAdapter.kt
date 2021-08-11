package com.example.schoolorgonizer.lessonSchedule.daySchedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolorgonizer.databinding.ItemDayBinding
import com.example.schoolorgonizer.lessonSchedule.Lessons

class DayAdapter() : ListAdapter<Lessons, DayAdapter.DayViewHolder>(DiffUtilItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder =
        DayViewHolder(
            ItemDayBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DayViewHolder(
        private val bindingView: ItemDayBinding
    ) : RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: Lessons) {
            bindingView.tvText.text = item.name
            bindingView.tvDate.text = item.id.toString()
        }
    }
}

class DiffUtilItemCallback : DiffUtil.ItemCallback<Lessons>() {
    override fun areItemsTheSame(oldItem: Lessons, newItem: Lessons): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Lessons, newItem: Lessons): Boolean {
        return oldItem.date == newItem.date && oldItem.name == newItem.name
    }


}