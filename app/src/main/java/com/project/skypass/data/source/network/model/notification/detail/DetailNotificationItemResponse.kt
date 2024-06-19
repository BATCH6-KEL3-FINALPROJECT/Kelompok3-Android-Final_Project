package com.project.skypass.data.source.network.model.notification.detail


import com.google.gson.annotations.SerializedName

data class DetailNotificationItemResponse(
    @SerializedName("booking_id")
    var bookingId: Any?,
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("flight_id")
    var flightId: Any?,
    @SerializedName("is_read")
    var isRead: Boolean?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("notification_id")
    var notificationId: String?,
    @SerializedName("notification_type")
    var notificationType: String?,
    @SerializedName("promotion_id")
    var promotionId: Any?,
    @SerializedName("updatedAt")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: Any?
)