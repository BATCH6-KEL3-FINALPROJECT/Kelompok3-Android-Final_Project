package com.project.skypass.data.source.network.model.history


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("Seat")
    var seat: Seat?,
    @SerializedName("seat_number")
    var seatNumber: String?
)