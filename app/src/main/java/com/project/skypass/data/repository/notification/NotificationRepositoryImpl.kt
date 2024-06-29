package com.project.skypass.data.repository.notification

import com.project.skypass.data.datasource.notification.NotificationDataSource
import com.project.skypass.data.mapper.toDetailNotification
import com.project.skypass.data.mapper.toNotificationItems
import com.project.skypass.data.mapper.toUpdateNotification
import com.project.skypass.data.model.Notification
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class NotificationRepositoryImpl(private val dataSource: NotificationDataSource) : NotificationRepository {
    override fun getNotifications(token: String): Flow<ResultWrapper<List<Notification>>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            dataSource.getNotifications(tokenBearer).data?.notification.toNotificationItems()
        }
    }

    override fun getDetailNotification(
        token: String,
        id: String,
    ): Flow<ResultWrapper<Notification>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            dataSource.getDetailNotification(
                tokenBearer,
                id,
            ).data?.notification.toDetailNotification()
        }
    }

    override fun updateNotification(id: String): Flow<ResultWrapper<Notification>> {
        return proceedFlow { dataSource.updateNotification(id).data?.notification.toUpdateNotification() }
    }

}
