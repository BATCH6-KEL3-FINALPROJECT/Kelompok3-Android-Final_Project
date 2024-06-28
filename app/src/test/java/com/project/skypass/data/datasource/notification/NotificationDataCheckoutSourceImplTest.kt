package com.project.skypass.data.datasource.notification

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.notification.all.DataNotification
import com.project.skypass.data.source.network.model.notification.all.NotificationItemResponse
import com.project.skypass.data.source.network.model.notification.all.NotificationResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationItemResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationResponse
import com.project.skypass.data.source.network.service.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class NotificationDataSourceImplTest {

    @MockK
    lateinit var service: ApiService
    private lateinit var ds: NotificationDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = NotificationDataSourceImpl(service)
    }

    @Test
    fun getNotifications() = runBlocking {
        val token = "token"
        val notificationItemResponse = NotificationItemResponse(
            bookingId = "booking_id",
            createdAt = "2022-01-01T00:00:00.000Z",
            flightId = "flight_id",
            isRead = false,
            message = "Some message",
            notificationId = "notification_id",
            notificationType = "INFO",
            promotionId = "promotion_id",
            updatedAt = "2022-01-01T00:00:00.000Z",
            userId = "user_id"
        )
        val data = DataNotification(listOf(notificationItemResponse))
        val notificationResponse = Response(true, "Success", data)
        coEvery { service.getAllNotification(token) } returns notificationResponse

        val result = ds.getNotifications(token)

        assert(result == notificationResponse)
    }

    @Test
    fun getDetailNotification() = runBlocking {
        val token = "some_token"
        val id = "some_id"
        val detailNotificationItemResponse = DetailNotificationItemResponse(
            bookingId = "booking_id",
            createdAt = "2022-01-01T00:00:00.000Z",
            flightId = "flight_id",
            isRead = false,
            message = "Some message",
            notificationId = "notification_id",
            notificationType = "INFO",
            promotionId = "promotion_id",
            updatedAt = "2022-01-01T00:00:00.000Z",
            userId = "user_id"
        )
        val data = com.project.skypass.data.source.network.model.notification.detail.Data(detailNotificationItemResponse)
        val detailNotificationResponse = DetailNotificationResponse(data, "success")
        coEvery { service.getDetailNotification(token, id) } returns detailNotificationResponse

        val result = ds.getDetailNotification(token, id)

        assert(result == detailNotificationResponse)
    }
}