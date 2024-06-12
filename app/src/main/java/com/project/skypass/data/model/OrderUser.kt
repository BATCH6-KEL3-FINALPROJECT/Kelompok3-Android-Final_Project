package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class OrderUser (
    var id: Int? = null,
    val airlineCode: String?,
    val airlineName: String?,
    val arrivalAirportName: String?,
    val arrivalCity: String?,
    val arrivalDate: String?,
    val arrivalIATACode: String?,
    val arrivalTime: String?,
    val departureAirportName: String?,
    val departureCity: String?,
    val departureDate: String?,
    val departureIATACode: String?,
    val departureTime: String?,
    val flightCode: String?,
    val flightDescription: String?,
    val flightDuration: Int?,
    val flightId: String?,
    val flightStatus: String?,
    val planeType: String?,
    val priceAdult: Int?,
    val priceBaby: Int?,
    val priceChild: Int?,
    val priceTotal: Int?,
    val seatClass: String?,
    val seatsAvailable: Int?,
    val terminal: String?,
    val orderDate: String?,
    val passengersTotal: String?,
    val passengersAdult: String?,
    val passengersChild: String?,
    val passengersBaby: String?,
    val flightSeat: String?,
): Parcelable