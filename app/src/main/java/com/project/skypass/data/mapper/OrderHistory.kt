package com.project.skypass.data.mapper

import com.project.skypass.data.model.OrderUser

import com.project.skypass.data.source.local.database.entity.OrderHistoryEntity


fun OrderUser?.toOrderHistoryEntity() = OrderHistoryEntity(
    id = this?.id,
    orderId = this?.flightId,
    from = this?.departureCity,
    to = this?.arrivalCity,
    departureDate = this?.departureDate,
    returnDate = this?.arrivalDate,
    passengers = this?.passengersTotal,
    seatClass = this?.seatClass,
    orderDate = this?.orderDate
)

fun OrderHistoryEntity?.toOrderHistory() = OrderUser(
    id = this?.id,
    departureCity = this?.from,
    arrivalCity = this?.to,
    departureDate = this?.departureDate,
    arrivalDate = this?.returnDate,
    passengersTotal = this?.passengers,
    seatClass = this?.seatClass,
    orderDate = this?.orderDate,
    airlineCode = "",
    airlineName = "",
    arrivalAirportName = "",
    arrivalIATACode = "",
    arrivalTime = "",
    departureAirportName = "",
    departureIATACode = "",
    departureTime = "",
    flightCode = "",
    flightDuration = null,
    flightId = "",
    flightSeat="",
    flightStatus = "",
    flightDescription = "",
    planeType = "",
    priceAdult = null,
    priceChild = null,
    priceBaby = null,
    priceTotal = null,
    seatsAvailable = null,
    terminal = "",
    passengersBaby = null,
    passengersChild = null,
    passengersAdult = null,
)

fun List<OrderHistoryEntity?>.toOrderHistoryList() = this.map { it.toOrderHistory() }