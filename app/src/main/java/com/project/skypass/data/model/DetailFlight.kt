package com.project.skypass.data.model

import java.util.UUID

data class DetailFlight(
    var id: String = UUID.randomUUID().toString(),
    val flightId: String?,
    val airlineId: String?,
    val airlineCode: String?,
    val airlineName: String?,
    val arrivalTime: String?,
    val arrivalDate: String?,
    val arrivalCity: String?,
    val arrivalContinent: String?,
    val arrivalAirportName: String?,
    val arrivalAirportId: String?,
    val departureTime: String?,
    val departureDate: String?,
    val departureAirportName: String?,
    val departureCity: String?,
    val departureContinent: String?,
    val departureAirportId: String?,
    val isPromo: Boolean?,
    val seatAvailable: Int?,
    val terminal: Boolean?,
    val flightCode: String?,
    val flightStatus: String?,
    val flightDuration: Int?,
    val flightDescription: String?,
    val planeType: String?,
    val createdAt: String?,
    val updatedAt: String?
)
