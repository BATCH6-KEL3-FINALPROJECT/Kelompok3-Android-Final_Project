package com.project.skypass.data.source.network.model.notification.all


import com.google.gson.annotations.SerializedName

data class DataNotification(
    @SerializedName("notification")
    var notification: List<NotificationItemResponse>?
)