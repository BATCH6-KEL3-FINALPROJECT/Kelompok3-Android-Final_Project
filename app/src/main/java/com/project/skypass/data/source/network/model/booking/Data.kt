package com.project.skypass.data.source.network.model.booking


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("flightData")
    var flightData: List<GetBookingFlightDataItemResponse>?,
    @SerializedName("newBooking")
    var newBooking: List<GetBookingIdentityItemResponse>?,
    @SerializedName("totalPrice")
    var totalPrice: Int?
)