package com.project.skypass.data.repository.notification

import com.project.skypass.data.model.Notification
import com.project.skypass.data.source.network.model.notification.all.NotificationResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationResponse
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getNotifications(token: String): Flow<ResultWrapper<List<Notification>>>
    fun getDetailNotification(token: String, id: String): Flow<ResultWrapper<Notification>>
}