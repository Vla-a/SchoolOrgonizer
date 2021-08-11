package com.example.schoolorgonizer.lesson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolorgonizer.databinding.ItemSheduleListBinding


class MessageAdapter(
    private val clickListener: (Message) -> Unit
) : ListAdapter<Message, MessageAdapter.MessageViewHolder>(DiffUtilItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
        MessageViewHolder(
            ItemSheduleListBinding.inflate(LayoutInflater.from(parent.context)), clickListener
        )

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MessageViewHolder(
        private val bindingView: ItemSheduleListBinding,
        private val clickListener: (Message) -> Unit
    ) :
        RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: Message) {
            bindingView.tvText.text = item.name
            bindingView.tvDay.text = item.date
            bindingView.tvDate.text = item.id.toString()

            itemView.setOnLongClickListener {
                clickListener(item)
                true
            }
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