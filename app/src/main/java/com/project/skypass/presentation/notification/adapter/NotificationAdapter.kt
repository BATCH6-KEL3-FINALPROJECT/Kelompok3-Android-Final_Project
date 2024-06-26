package com.project.skypass.presentation.notification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.R
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.ItemNotificationBinding
import com.project.skypass.utils.convertDateNotification

class NotificationAdapter(
    private val listener: OnItemCLickedListener<Notification>,
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    private val asyncDataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<Notification>() {
                override fun areItemsTheSame(
                    oldItem: Notification,
                    newItem: Notification,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Notification,
                    newItem: Notification,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    fun submitData(items: List<Notification>) {
        asyncDataDiffer.submitList(items)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NotificationViewHolder {
        return NotificationViewHolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            listener,
        )
    }

    override fun getItemCount(): Int = asyncDataDiffer.currentList.size

    override fun onBindViewHolder(
        holder: NotificationViewHolder,
        position: Int,
    ) {
        holder.bind(asyncDataDiffer.currentList[position])
    }

    class NotificationViewHolder(
        private val binding: ItemNotificationBinding,
        private val listener: OnItemCLickedListener<Notification>,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notification) {
            binding.tvTitleItemNotification.text = item.notificationType
            binding.tvDateItemNotification.text = convertDateNotification(item.createdAt)
            binding.tvDetailItemNotification.text = item.message
            if (item.notificationType == "promo"){
                binding.ivIconNotification.setImageResource(R.drawable.ic_promo)
            }
            if (item.isRead) {
                binding.cardItemNotification.setBackgroundResource(R.color.color_item_read_notification)
            }
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }
}

interface OnItemCLickedListener<T> {
    fun onItemClicked(item: T)
}
