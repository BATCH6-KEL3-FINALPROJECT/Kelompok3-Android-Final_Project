package com.project.skypass.presentation.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.Notification
import com.project.skypass.data.repository.notification.NotificationRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class NotificationViewModel(
    private val prefRepository: PrefRepository,
    private val notificationRepository: NotificationRepository,
) : ViewModel() {
    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun getNotification(token: String) = notificationRepository.getNotifications(token).asLiveData(Dispatchers.IO)

    fun updateNotification(id: String): LiveData<ResultWrapper<Notification>> {
        return notificationRepository.updateNotification(id).asLiveData(Dispatchers.IO)
    }
}
