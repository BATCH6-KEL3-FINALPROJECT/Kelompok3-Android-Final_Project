package com.project.skypass.data.model

import android.os.Parcelable
import java.util.UUID

@Parcelize
data class History(
    var id: String = UUID.randomUUID().toString(),
    val bookingId: String,
    val userId: String,
    val flightId: String,
    val paymentId: String,
    val bookingDate: String,
    val isRoundTrip: Boolean,
    val noOfTickets: Int,
    val status: String,
    val totalPrice: String,
    val flightDuration: Int,
    val flightStatus: String,
    val terminal: Boolean,
    val departureAirport: String,
    val arrivalAirport: String,
    val departureDate: String,
    val departureTime: String,
    val arrivalDate: String,
    val arrivalTime: String,
    val departureAirportId: String,
    val arrivalAirportId: String,
    val departingAirport: String,
    val arrivingAirport: String,
    val airlineName: String?,
    val airlineCode: String?,
    val country: String?,
    val ticketIdentity: List<TicketHistory>?
): Parcelable
