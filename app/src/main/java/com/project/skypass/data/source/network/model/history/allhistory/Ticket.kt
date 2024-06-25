package com.project.skypass.data.source.network.model.history.allhistory


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("Passenger")
    var passenger: Passenger?,
    @SerializedName("Seat")
    var seat: Seat?,
    @SerializedName("seat_number")
    var seatNumber: String?
)