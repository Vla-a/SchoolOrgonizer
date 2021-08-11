package com.example.schoolorgonizer.lesson.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolorgonizer.databinding.ItemDayBinding
import com.example.schoolorgonizer.lesson.Message

class DayAdapter() : ListAdapter<Message, DayAdapter.DayViewHolder>(DiffUtilItemCallback()) {


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

        fun bind(item: Message) {
            bindingView.tvText.text = item.name
            bindingView.tvDate.text = item.id.toString()
        }
    }
}

class DiffUtilItemCallback : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.date == newItem.date && oldItem.name == newItem.name
    }


}