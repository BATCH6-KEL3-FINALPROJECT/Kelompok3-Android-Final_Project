package com.project.skypass.data.source.network.model.otp

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResendOtpResponse(
    @SerializedName("status")
    var status: String?,
    @SerializedName("message")
    var message: String?,
)
