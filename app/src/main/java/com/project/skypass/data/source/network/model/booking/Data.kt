package com.project.skypass.data.source.network.model.booking

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("bookingData")
    var bookingData: List<GetBookingDataItemResponse>?,
    @SerializedName("totalPrice")
    var totalPrice: Int?,
)
