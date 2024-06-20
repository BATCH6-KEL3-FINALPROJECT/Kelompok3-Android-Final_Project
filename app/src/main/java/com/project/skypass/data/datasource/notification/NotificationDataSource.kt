package com.project.skypass.data.datasource.notification

import com.project.skypass.data.source.network.model.notification.all.NotificationResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationResponse

interface NotificationDataSource {
    suspend fun getNotifications(token: String): NotificationResponse
    suspend fun getDetailNotification(token: String, id: String): DetailNotificationResponse
}