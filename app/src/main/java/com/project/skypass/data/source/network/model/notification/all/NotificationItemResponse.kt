package com.project.skypass.data.source.network.model.notification.all


import com.google.gson.annotations.SerializedName

data class NotificationItemResponse(
    @SerializedName("booking_id")
    var bookingId: String?,
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("flight_id")
    var flightId: String?,
    @SerializedName("is_read")
    var isRead: Boolean?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("notification_id")
    var notificationId: String?,
    @SerializedName("notification_type")
    var notificationType: String?,
    @SerializedName("promotion_id")
    var promotionId: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: String?
)