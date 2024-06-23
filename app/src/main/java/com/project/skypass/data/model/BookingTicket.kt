package com.project.skypass.data.model

import java.util.UUID

data class BookingTicket(
    var id: String = UUID.randomUUID().toString(),
    val totalAmount: Int,
    val departureFlightId: String,
    val returnFlightId: String?,
    val fullName: String,
    val familyName: String,
    val email: String,
    val phone: String,
    val passenger: List<PassengersData>
)
