package com.project.skypass.data.source.network.model.booking


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("flightData")
    var flightData: List<FlightData>?,
    @SerializedName("newBooking")
    var newBooking: List<NewBooking>?,
    @SerializedName("totalPrice")
    var totalPrice: Int?
)