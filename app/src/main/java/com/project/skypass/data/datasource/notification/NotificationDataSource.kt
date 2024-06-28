package com.project.skypass.data.datasource.notification

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.notification.all.DataNotification
import com.project.skypass.data.source.network.model.notification.all.NotificationResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationResponse
import com.project.skypass.data.source.network.model.notification.update.UpdateNotificationResponse

interface NotificationDataSource {
    suspend fun getNotifications(token: String): Response<DataNotification>
    suspend fun getDetailNotification(token: String, id: String): DetailNotificationResponse
    suspend fun updateNotification(id: String): UpdateNotificationResponse
}