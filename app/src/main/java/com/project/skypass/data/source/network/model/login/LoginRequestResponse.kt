package com.project.skypass.data.source.network.model.login

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginRequestResponse(
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?,
)
