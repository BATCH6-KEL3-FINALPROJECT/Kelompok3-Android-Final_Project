package com.project.skypass.data.model

import java.util.UUID

data class History(
    var id: String = UUID.randomUUID().toString(),
    val bookingId: String,
    val timeDeparture: String,
    val dateDeparture: String,
    val terminalDeparture: String,
    val nameAirline: String,
    val classFlight: String,
    val codeAirline: String,
    val logoAirline: String,
    val namePassenger: String,
    val idPassenger: String,
    val timeArrival: String,
    val dateArrival: String,
    val terminalArrival: String,
    val qytAdult: Int,
    val qytChild: Int,
    val qytInfant: Int,
    val priceAdult: Double,
    val priceChild: Double,
    val priceInfant: Double,
    val tax: Double,
    val totalPrice: Double,
    val status: String
)
