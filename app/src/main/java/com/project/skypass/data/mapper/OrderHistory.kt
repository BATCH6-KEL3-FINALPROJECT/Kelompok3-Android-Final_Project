package com.project.skypass.data.mapper

import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.source.local.database.entity.OrderHistoryEntity

fun OrderUser?.toOrderHistoryEntity() =
    OrderHistoryEntity(
        orderId = this?.id,
        from = this?.departureCity,
        to = this?.arrivalCity,
        departureDate = this?.departureDate,
        arrivalDate = this?.arrivalDate,
        passengers = this?.passengersTotal,
        seatClass = this?.seatClass,
        orderDate = this?.orderDate,
    )

fun OrderHistoryEntity?.toOrderHistory() =
    OrderUser(
        // send to database
        id = this?.orderId,
        departureCity = this?.from,
        arrivalCity = this?.to,
        departureDate = this?.departureDate,
        arrivalDate = this?.arrivalDate,
        passengersTotal = this?.passengers,
        seatClass = this?.seatClass,
        orderDate = this?.orderDate,
        // Home Data
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
        terminalRoundTrip = "",
    )

fun List<OrderHistoryEntity?>.toOrderHistoryList() = this.map { it.toOrderHistory() }
