package com.project.skypass.presentation.notification

import androidx.lifecycle.ViewModel
import com.project.skypass.data.datasource.notification.DataSourceNotification
import com.project.skypass.data.datasource.notification.DataSourceNotificationImpl
import kotlinx.coroutines.Dispatchers

class NotificationViewModel : ViewModel() {
    private val dataSourceNotification: DataSourceNotification by lazy { DataSourceNotificationImpl() }
    fun getNotificationData() = dataSourceNotification.getNotificationItem()
}