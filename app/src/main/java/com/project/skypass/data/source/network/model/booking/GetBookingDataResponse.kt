package com.project.skypass.data.source.network.model.booking


import com.google.gson.annotations.SerializedName

data class GetBookingDataResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: Data?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?
)