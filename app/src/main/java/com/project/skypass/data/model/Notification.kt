package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID


@Parcelize
data class Notification (
    var id: String? = UUID.randomUUID().toString(),
    val notificationId: String,
    val userId: String,
    val flightId: String,
    val bookingId: String,
    val promotionId: String,
    val notificationType: String,
    val message: String,
    val isRead: Boolean,
    val createdAt: String,
    val updatedAt: String
): Parcelable
