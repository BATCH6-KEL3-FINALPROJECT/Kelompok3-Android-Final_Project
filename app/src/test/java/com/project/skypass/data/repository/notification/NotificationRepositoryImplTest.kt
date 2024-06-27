package com.project.skypass.data.repository.notification

import com.project.skypass.data.datasource.notification.NotificationDataSource
import com.project.skypass.data.source.network.model.notification.all.Data
import com.project.skypass.data.source.network.model.notification.all.NotificationItemResponse
import com.project.skypass.data.source.network.model.notification.all.NotificationResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationItemResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationResponse
import com.project.skypass.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class NotificationRepositoryImplTest {

    @MockK
    lateinit var ds: NotificationDataSource
    private lateinit var repo: NotificationRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = NotificationRepositoryImpl(ds)
    }

    @Test
    fun getNotifications() = runBlocking {
        val token = "some_token"
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
        val data = Data(listOf(notificationItemResponse))
        val notificationResponse = NotificationResponse(200, data, true, "Success")
        coEvery { ds.getNotifications("Bearer $token") } returns notificationResponse

        val result = repo.getNotifications(token)
        result.collect { resultWrapper ->
            when (resultWrapper) {
                is ResultWrapper.Success -> {
                    val notifications = resultWrapper.payload
                    assertNotNull(notifications)
                    assertEquals(1, notifications?.size)
                    val notification = notifications?.first()
                    assertNotNull(notification)
                    assertEquals("booking_id", notification?.bookingId)
                    assertEquals("2022-01-01T00:00:00.000Z", notification?.createdAt)
                    assertEquals("flight_id", notification?.flightId)
                    assertFalse(notification?.isRead!!)
                    assertEquals("Some message", notification.message)
                    assertEquals("notification_id", notification.notificationId)
                    assertEquals("INFO", notification.notificationType)
                    assertEquals("promotion_id", notification.promotionId)
                    assertEquals("2022-01-01T00:00:00.000Z", notification.updatedAt)
                    assertEquals("user_id", notification.userId)
                }
                is ResultWrapper.Loading -> delay(100)
                else -> fail("Expected Success result, but got ${result::class.simpleName}")
            }
        }
        coVerify { ds.getNotifications("Bearer $token") }
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
        coEvery { ds.getDetailNotification("Bearer $token", id) } returns detailNotificationResponse

        val result = repo.getDetailNotification(token, id)

        result.collect { resultWrapper ->
            when (resultWrapper) {
                is ResultWrapper.Success -> {
                    val notification = resultWrapper.payload
                    assertNotNull(notification)
                    assertEquals("booking_id", notification?.bookingId)
                    assertEquals("2022-01-01T00:00:00.000Z", notification?.createdAt)
                    assertEquals("flight_id", notification?.flightId)
                    assertFalse(notification?.isRead!!)
                    assertEquals("Some message", notification.message)
                    assertEquals("notification_id", notification.notificationId)
                    assertEquals("INFO", notification.notificationType)
                    assertEquals("promotion_id", notification.promotionId)
                    assertEquals("2022-01-01T00:00:00.000Z", notification.updatedAt)
                    assertEquals("user_id", notification.userId)
                }
                is ResultWrapper.Loading -> delay(100)
                else -> fail("Expected Success result, but got ${result::class.simpleName}")
            }
        }
        coVerify { ds.getDetailNotification("Bearer $token", id) }
    }
}