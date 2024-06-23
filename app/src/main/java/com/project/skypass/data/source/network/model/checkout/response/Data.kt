package com.project.skypass.data.source.network.model.checkout.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("bookingResult")
    var bookingResult: BookingResult?,
    @SerializedName("returnBookingResult")
    var returnBookingResult: ReturnBookingResult?
)