package com.project.skypass.data.source.network.model.register


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RegisterRequestResponse(
    @SerializedName("name")
    var name: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("phone_number")
    var phoneNumber: String?,
    @SerializedName("password")
    var password: String?
)