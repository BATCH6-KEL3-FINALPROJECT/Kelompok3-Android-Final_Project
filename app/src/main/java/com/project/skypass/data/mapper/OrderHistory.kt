package com.project.skypass.data.mapper

import com.project.skypass.data.model.OrderUser

import com.project.skypass.data.source.local.database.entity.OrderHistoryEntity


fun OrderUser?.toOrderHistoryEntity() = OrderHistoryEntity(
    id = this?.id,
    from = this?.departureCity.toString(),
    to = this?.arrivalCity.toString(),
    departureDate = this?.departureDate.toString(),
    arrivalDate = this?.arrivalDate.toString(),
    passengers = this?.passengersTotal.toString(),
    seatClass = this?.seatClass.toString(),
    orderDate = this?.orderDate.toString()
)

fun OrderHistoryEntity?.toOrderHistory() = OrderUser(
    // send to database
    id = this?.id,
    departureCity = this?.from,
    arrivalCity = this?.to,
    departureDate = this?.departureDate,
    arrivalDate = this?.arrivalDate,
    passengersTotal = this?.passengers,
    seatClass = this?.seatClass,
    orderDate = this?.orderDate,

    //Home Data
    isRoundTrip = null,
    supportRoundTrip = null,
    passengersAdult = null,
    passengersChild = null,
    passengersBaby = null,

    // Flight Data (One Way)
    airlineCode = "",
    airlineName = "",
    arrivalAirportName = "",
    arrivalIATACode = "",
    arrivalTime = "",
    departureAirportName = "",
    departureIATACode = "",
    departureTime = "",
    flightCode = "",
    flightDescription = "",
    flightDuration = null,
    flightDurationFormat = "",
    flightId = "",
    flightStatus = "",
    flightSeat = "",
    flightArrivalDate = "",
    flightDepartureDate = "",
    planeType = "",
    priceAdult = null,
    priceBaby = null,
    priceChild = null,
    priceTotal = null,
    paymentPrice = null,
    seatsAvailable = null,
    terminal = "",

    // Flight Data (Round Trip)
    airlineCodeRoundTrip = "",
    airlineNameRoundTrip = "",
    arrivalAirportNameRoundTrip = "",
    arrivalIATACodeRoundTrip = "",
    arrivalTimeRoundTrip = "",
    departureAirportNameRoundTrip = "",
    departureIATACodeRoundTrip = "",
    departureTimeRoundTrip = "",
    flightCodeRoundTrip = "",
    flightDescriptionRoundTrip = "",
    flightDurationRoundTrip = null,
    flightDurationFormatRoundTrip = "",
    flightIdRoundTrip = "",
    flightStatusRoundTrip = "",
    flightSeatRoundTrip = "",
    flightArrivalDateRoundTrip = "",
    flightDepartureDateRoundTrip = "",
    planeTypeRoundTrip = "",
    priceAdultRoundTrip = null,
    priceBabyRoundTrip = null,
    priceChildRoundTrip = null,
    priceTotalRoundTrip = null,
    paymentPriceRoundTrip = null,
    seatsAvailableRoundTrip = null,
    terminalRoundTrip = ""
)

fun List<OrderHistoryEntity?>.toOrderHistoryList() = this.map { it.toOrderHistory() }