package com.project.skypass.data.source.network.model.notification.detail


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("notification")
    var notification: DetailNotificationItemResponse?
)