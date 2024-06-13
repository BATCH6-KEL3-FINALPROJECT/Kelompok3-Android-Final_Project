package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class OrderUser (
    // common data
    var id: Int? = null,
    var arrivalCity: String?,
    var arrivalDate: String?,
    var departureCity: String?,
    var departureDate: String?,
    var passengersTotal: String?,
    var passengersAdult: Int?,
    var passengersChild: Int?,
    var passengersBaby: Int?,
    var seatClass: String?,
    var isRoundTrip: Boolean?,
    var supportRoundTrip: Boolean?,
    var orderDate: String?,

    // one way

    var airlineCode: String?,
    var airlineName: String?,
    var arrivalAirportName: String?,
    var departureAirportName: String?,
    var departureIATACode: String?,
    var departureTime: String?,
    var arrivalIATACode: String?,
    var arrivalTime: String?,
    var flightCode: String?,
    var flightDescription: String?,
    var flightDuration: Int?,
    var flightDurationFormat: String?,
    var flightId: String?,
    var flightStatus: String?,
    var flightArrivalDate: String?,
    var flightDepartureDate: String?,
    var planeType: String?,
    var seatsAvailable: Int?,
    var terminal: String?,
    var flightSeat: String?,
    var priceAdult: Int?,
    var priceBaby: Int?,
    var priceChild: Int?,
    var priceTotal: Int?,
    var paymentPrice: Int?,


    // Round Trip
    var airlineCodeRoundTrip: String?,
    var airlineNameRoundTrip: String?,
    var arrivalAirportNameRoundTrip: String?,
    var departureAirportNameRoundTrip: String?,
    var departureIATACodeRoundTrip: String?,
    var departureTimeRoundTrip: String?,
    var arrivalIATACodeRoundTrip: String?,
    var arrivalTimeRoundTrip: String?,
    var flightCodeRoundTrip: String?,
    var flightDescriptionRoundTrip: String?,
    var flightDurationRoundTrip: Int?,
    var flightDurationFormatRoundTrip: String?,
    var flightIdRoundTrip: String?,
    var flightStatusRoundTrip: String?,
    var flightArrivalDateRoundTrip: String?,
    var flightDepartureDateRoundTrip: String?,
    var planeTypeRoundTrip: String?,
    var seatsAvailableRoundTrip: Int?,
    var terminalRoundTrip: String?,
    var flightSeatRoundTrip: String?,
    var priceAdultRoundTrip: Int?,
    var priceBabyRoundTrip: Int?,
    var priceChildRoundTrip: Int?,
    var priceTotalRoundTrip: Int?,
    var paymentPriceRoundTrip: Int?,



): Parcelable