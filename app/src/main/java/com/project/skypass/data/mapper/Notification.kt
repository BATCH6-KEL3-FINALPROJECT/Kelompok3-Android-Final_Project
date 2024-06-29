package com.project.skypass.data.mapper

import com.project.skypass.data.model.Notification
import com.project.skypass.data.source.network.model.notification.all.NotificationItemResponse
import com.project.skypass.data.source.network.model.notification.detail.DetailNotificationItemResponse
import com.project.skypass.data.source.network.model.notification.update.UpdateNotificationItemResponse

fun NotificationItemResponse?.toNotificationItem() =
    Notification(
        notificationId = this?.notificationId.orEmpty(),
        userId = this?.userId.orEmpty(),
        flightId = this?.flightId.orEmpty(),
        bookingId = this?.bookingId.orEmpty(),
        promotionId = this?.promotionId.orEmpty(),
        notificationType = this?.notificationType.orEmpty(),
        message = this?.message.orEmpty(),
        isRead = this?.isRead ?: false,
        createdAt = this?.createdAt.orEmpty(),
        updatedAt = this?.updatedAt.orEmpty(),
    )

fun DetailNotificationItemResponse?.toDetailNotification() =
    Notification(
        notificationId = this?.notificationId.orEmpty(),
        userId = this?.userId.orEmpty(),
        flightId = this?.flightId.orEmpty(),
        bookingId = this?.bookingId.orEmpty(),
        promotionId = this?.promotionId.orEmpty(),
        notificationType = this?.notificationType.orEmpty(),
        message = this?.message.orEmpty(),
        isRead = this?.isRead ?: false,
        createdAt = this?.createdAt.orEmpty(),
        updatedAt = this?.updatedAt.orEmpty(),
    )

fun UpdateNotificationItemResponse?.toUpdateNotification() =
    Notification(
        notificationId = this?.notificationId.orEmpty(),
        userId = this?.userId.orEmpty(),
        flightId = this?.flightId.orEmpty(),
        bookingId = this?.bookingId.orEmpty(),
        promotionId = this?.promotionId.orEmpty(),
        notificationType = this?.notificationType.orEmpty(),
        message = this?.message.orEmpty(),
        isRead = this?.isRead ?: true,
        createdAt = this?.createdAt.orEmpty(),
        updatedAt = this?.updatedAt.orEmpty(),
    )

fun Collection<NotificationItemResponse>?.toNotificationItems() = this?.map { it.toNotificationItem() } ?: listOf()
