package com.project.skypass.data.source.network.model.seat


import com.google.gson.annotations.SerializedName

data class SeatItemResponse(
    @SerializedName("column")
    var column: String?,
    @SerializedName("flight_id")
    var flightId: String?,
    @SerializedName("is_available")
    var isAvailable: String?,
    @SerializedName("row")
    var row: Int?,
    @SerializedName("seat_class")
    var seatClass: String?,
    @SerializedName("seat_id")
    var seatId: String?,
    @SerializedName("seat_number")
    var seatNumber: String?
)