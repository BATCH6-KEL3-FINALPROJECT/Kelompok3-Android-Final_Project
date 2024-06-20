package com.project.skypass.data.repository.notification

import com.project.skypass.data.datasource.notification.NotificationDataSource
import com.project.skypass.data.mapper.toDetailNotification
import com.project.skypass.data.mapper.toNotificationItems
import com.project.skypass.data.model.Notification
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class NotificationRepositoryImpl(private val dataSource: NotificationDataSource): NotificationRepository {
    override fun getNotifications(token: String): Flow<ResultWrapper<List<Notification>>> {
        return proceedFlow {
            dataSource.getNotifications(token).data?.notification.toNotificationItems()
        }
    }

    override fun getDetailNotification(
        token: String,
        id: String
    ): Flow<ResultWrapper<Notification>> {
        return proceedFlow {
            dataSource.getDetailNotification(token, id).data?.notification.toDetailNotification()
        }
    }

}