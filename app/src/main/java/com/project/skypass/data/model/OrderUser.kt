package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class OrderUser (
    // common data
    var id: Int? = null,
    val arrivalCity: String?,
    val arrivalDate: String?,
    val departureCity: String?,
    val departureDate: String?,
    val passengersTotal: String?,
    val passengersAdult: String?,
    val passengersChild: String?,
    val passengersBaby: String?,
    val seatClass: String?,
    val isRoundTrip: Boolean?,
    val supportRoundTrip: Boolean?,
    val orderDate: String?,

    // one way

    val airlineCode: String?,
    val airlineName: String?,
    val arrivalAirportName: String?,
    val departureAirportName: String?,
    val departureIATACode: String?,
    val departureTime: String?,
    val arrivalIATACode: String?,
    val arrivalTime: String?,
    val flightCode: String?,
    val flightDescription: String?,
    val flightDuration: Int?,
    val flightDurationFormat: String?,
    val flightId: String?,
    val flightStatus: String?,
    val flightArrivalDate: String?,
    val flightDepartureDate: String?,
    val planeType: String?,
    val seatsAvailable: Int?,
    val terminal: String?,
    val flightSeat: String?,
    val priceAdult: Int?,
    val priceBaby: Int?,
    val priceChild: Int?,
    val priceTotal: Int?,
    val paymentPrice: Int?,


    // Round Trip
    val airlineCodeRoundTrip: String?,
    val airlineNameRoundTrip: String?,
    val arrivalAirportNameRoundTrip: String?,
    val departureAirportNameRoundTrip: String?,
    val departureIATACodeRoundTrip: String?,
    val departureTimeRoundTrip: String?,
    val arrivalIATACodeRoundTrip: String?,
    val arrivalTimeRoundTrip: String?,
    val flightCodeRoundTrip: String?,
    val flightDescriptionRoundTrip: String?,
    val flightDurationRoundTrip: Int?,
    val flightDurationFormatRoundTrip: String?,
    val flightIdRoundTrip: String?,
    val flightStatusRoundTrip: String?,
    val flightArrivalDateRoundTrip: String?,
    val flightDepartureDateRoundTrip: String?,
    val planeTypeRoundTrip: String?,
    val seatsAvailableRoundTrip: Int?,
    val terminalRoundTrip: String?,
    val flightSeatRoundTrip: String?,
    val priceAdultRoundTrip: Int?,
    val priceBabyRoundTrip: Int?,
    val priceChildRoundTrip: Int?,
    val priceTotalRoundTrip: Int?,
    val paymentPriceRoundTrip: Int?,



): Parcelable