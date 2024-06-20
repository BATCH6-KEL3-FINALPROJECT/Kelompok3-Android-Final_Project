package com.project.skypass.data.datasource.notification

import com.project.skypass.data.source.network.model.notification.all.NotificationResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationResponse
import com.project.skypass.data.source.network.service.ApiService

class NotificationDataSourceImpl(private val service: ApiService): NotificationDataSource {
    override suspend fun getNotifications(token: String): NotificationResponse {
        return service.getAllNotification(token)
    }

    override suspend fun getDetailNotification(token: String, id: String): DetailNotificationResponse {
        return service.getDetailNotification(token, id)
    }
}