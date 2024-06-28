package com.project.skypass.data.source.network.model.notification.all


import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: DataNotification?,
    @SerializedName("is_sucsess")
    var isSucsess: Boolean?,
    @SerializedName("message")
    var message: String?
)