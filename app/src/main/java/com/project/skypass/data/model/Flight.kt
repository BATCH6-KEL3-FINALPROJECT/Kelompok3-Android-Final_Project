package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
import java.util.UUID

data class Flight(
    var id: String = UUID.randomUUID().toString(),
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
    val price: Int?,
    val priceForChild: Int?,
    val priceForInfant: Int?,
    val seatClass: String?,
    val seatsAvailable: Int?,
    val terminal: String?
) : Parcelable
