package com.project.skypass.data.source.network.model.notification.update

import com.google.gson.annotations.SerializedName

data class UpdateNotificationResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: Data?,
    @SerializedName("is_sucsess")
    var isSucsess: Boolean?,
    @SerializedName("message")
    var message: String?
)
