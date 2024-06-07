package com.project.skypass.presentation.notification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.ItemNotificationBinding


class NotificationAdapter: RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    private val data = mutableListOf<Notification>()

    fun submitData(items : List<Notification>){
        data.addAll(items)
    }

    class NotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notification) {
            binding.tvTitleItemNotification.text = item.title
            binding.tvDateItemNotification.text = item.date
            binding.tvDetailItemNotification.text = item.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
