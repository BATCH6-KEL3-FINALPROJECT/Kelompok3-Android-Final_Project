package com.project.skypass.data.model

import java.util.UUID

data class TicketHistory (
    var id: String = UUID.randomUUID().toString(),
    val seatNumber: String,
    val seatClass: String,
    val passengerFirstName: String,
    val passengerLastName: String
)