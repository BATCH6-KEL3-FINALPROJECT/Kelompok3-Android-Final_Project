package com.project.skypass.data.model

import java.util.UUID

data class BookingDataPassengers(
    var id: String = UUID.randomUUID().toString(),
    val bookingId: String,
    val bookingCode: String,
    val userId: String,
    val flightId: String,
    val paymentId: String,
    val bookingDate: String,
    val isRoundTrip: Boolean,
    val noOfTicket: Int,
    val status: String,
    val totalPrice: String,
    val createdAt: String,
    val updatedAt: String,
    val adult: Int,
    val child: Int,
)

data class FlightDataBooking(
    val flightId: String,
    val flightDuration: Int,
    val flightDescription: String?,
    val flightStatus: String,
    val flightCode: String,
    val planeType: String,
    val seatsAvailable: Int,
    val terminalDeparture: String,
    val terminalArrival: String,
    val isPromo: Boolean?,
    val departureAirport: String,
    val arrivalAirport: String,
    val departureDate: String,
    val departureTime: String,
    val arrivalDate: String,
    val arrivalTime: String,
    val departureAirportId: String,
    val arrivalAirportId: String,
    val airlineId: String,
    val airlineName: String,
    val airlineCode: String,
    val country: String
)
