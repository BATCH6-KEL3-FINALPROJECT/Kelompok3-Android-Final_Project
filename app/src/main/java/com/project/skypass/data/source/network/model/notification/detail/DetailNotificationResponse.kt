package com.project.skypass.data.source.network.model.notification.detail


import com.google.gson.annotations.SerializedName

data class DetailNotificationResponse(
    @SerializedName("data")
    var `data`: Data?,
    @SerializedName("status")
    var status: String?
)