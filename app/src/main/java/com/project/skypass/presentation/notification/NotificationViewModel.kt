package com.project.skypass.presentation.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.notification.NotificationRepository
import com.project.skypass.data.repository.pref.PrefRepository
import kotlinx.coroutines.Dispatchers

class NotificationViewModel(
    private val prefRepository: PrefRepository,
    private val notificationRepository: NotificationRepository
): ViewModel() {
    /*private val dataSourceNotification: DataSourceNotification by lazy { DataSourceNotificationImpl() }
    fun getNotificationData() = dataSourceNotification.getNotificationItem()*/

    fun getToken(): String {
        return prefRepository.getToken()
    }
    fun getNotification(token: String) = notificationRepository.getNotifications(token).asLiveData(Dispatchers.IO)
}