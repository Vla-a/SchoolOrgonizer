package com.example.schoolorgonizer.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolorgonizer.databinding.ItemNoteBinding


class NoteAdapter(
    private val clickListener: (Notes) -> Unit
) : ListAdapter<Notes, NoteAdapter.MessageViewHolder>(DiffUtilItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
        MessageViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context)), clickListener
        )

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MessageViewHolder(
        private val bindingView: ItemNoteBinding,
        private val clickListener: (Notes) -> Unit
    ) :
        RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: Notes) {
            bindingView.tvText.text = item.message
            bindingView.tvDate.text = item.date

            bindingView.ivRemove.setOnClickListener {
                clickListener(item)
            }
        }
    }

}
class DiffUtilItemCallback: DiffUtil.ItemCallback<Notes>(){
    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
      return  oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
      return  oldItem.date == newItem.date && oldItem.message == newItem.message
    }


}