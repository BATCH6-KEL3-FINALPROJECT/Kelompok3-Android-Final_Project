package com.project.skypass.data.model

import java.util.UUID

data class Seat(
    var id: String = UUID.randomUUID().toString(),
    val flightId: String,
    val isAvailable: String,
    val column: String,
    val row: Int,
    val seatId: String,
    val seatNumber: String,
    val seatClass: String
)
