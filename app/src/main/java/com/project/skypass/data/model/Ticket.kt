package com.project.skypass.data.model

import java.util.UUID

data class Ticket(
    var id: String = UUID.randomUUID().toString(),
    val ticketId: String,
    val ticketCode: String,
    val flightId: String,
    val seatId: String,
    val passengerId: String,
    val bookingId: String,
    val seatNumber: String,
    val passengerName: String,
    val terminal: String,
    val ticketStatus: String,
    val createdAt: String,
    val updatedAt: String
)