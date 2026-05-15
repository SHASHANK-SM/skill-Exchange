package com.rural.skill_exchange.ui.chat

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rural.skill_exchange.R
import com.rural.skill_exchange.databinding.ItemMessageBinding
import com.rural.skill_exchange.model.ChatMessage

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    private val items = mutableListOf<ChatMessage>()

    fun submitList(messages: List<ChatMessage>) {
        items.clear()
        items.addAll(messages)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class MessageViewHolder(private val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatMessage) = with(binding) {
            messageRow.gravity = if (item.mine) Gravity.END else Gravity.START
            messageText.text = "${item.senderName}: ${item.body}"
            messageText.setBackgroundResource(if (item.mine) R.drawable.message_out else R.drawable.message_in)
            messageText.setTextColor(ContextCompat.getColor(messageText.context, if (item.mine) R.color.white else R.color.ink))
        }
    }
}
