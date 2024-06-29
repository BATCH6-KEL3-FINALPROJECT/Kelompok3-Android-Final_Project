package com.project.skypass.presentation.notification.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.R
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.ItemNotificationBinding
import com.project.skypass.presentation.customview.OnItemAdapterClickedListener
import com.project.skypass.utils.convertDateNotification
import com.project.skypass.utils.formatDateNotification

class NotificationAdapter (
    private val itemClick: OnItemAdapterClickedListener<Notification>
): RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private val data = mutableListOf<Notification>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<Notification>){
        this.data.clear()
        this.data.addAll(data)
        this.data.sortByDescending { it.createdAt }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClick
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class NotificationViewHolder(
        private val binding: ItemNotificationBinding,
        private val itemClick: OnItemAdapterClickedListener<Notification>
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notification) {
            binding.tvTitleItemNotification.text = item.notificationType
            binding.tvDateItemNotification.text = formatDateNotification(item.createdAt)
            binding.tvDetailItemNotification.text = item.message
            if (item.notificationType == "promo") {
                binding.ivIconNotification.setImageResource(R.drawable.ic_promo)
            }
            if (item.isRead) {
                binding.cardItemNotification.setBackgroundResource(R.color.md_theme_secondaryContainer_mediumContrast)
            } else {
                binding.cardItemNotification.setBackgroundResource(R.color.md_theme_background)
            }

            itemView.setOnClickListener {
                itemClick.onClicked(item)
            }
        }
    }

}