package com.project.skypass.data.source.network.model.history


import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("bookings")
    var bookings: List<Booking>?,
    @SerializedName("code")
    var code: Int?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?
)