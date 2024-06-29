package com.project.skypass.data.source.network.model.otp

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResendOtpRequestResponse(
    @SerializedName("email")
    var email: String?,
)
