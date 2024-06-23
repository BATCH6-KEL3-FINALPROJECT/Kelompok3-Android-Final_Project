package com.project.skypass.data.mapper

import com.project.skypass.data.model.BookingDataPassengers
import com.project.skypass.data.model.FlightDataBooking
import com.project.skypass.data.source.network.model.booking.GetBookingFlightDataItemResponse
import com.project.skypass.data.source.network.model.booking.GetBookingIdentityItemResponse

fun GetBookingIdentityItemResponse?.toBookingIdentity() =
    BookingDataPassengers(
        bookingId = this?.bookingId.orEmpty(),
        bookingCode = this?.bookingCode.orEmpty(),
        userId = this?.userId.orEmpty(),
        flightId = this?.flightId.orEmpty(),
        paymentId = this?.paymentId.orEmpty(),
        bookingDate = this?.bookingDate.orEmpty(),
        isRoundTrip = this?.isRoundTrip ?: false,
        noOfTicket = this?.noOfTicket ?: 0,
        status = this?.status.orEmpty(),
        totalPrice = this?.totalPrice.orEmpty(),
        createdAt = this?.createdAt.orEmpty(),
        updatedAt = this?.updatedAt.orEmpty(),
        adult = this?.adult ?: 0,
        child = this?.child ?: 0
    )

fun GetBookingFlightDataItemResponse?.toBookingFlightData() =
    FlightDataBooking(
        flightId = this?.flightId.orEmpty(),
        flightDuration = this?.flightDuration ?: 0,
        flightDescription = this?.flightDescription?.details?.joinToString { it.description ?:""},
        flightStatus = this?.flightStatus.orEmpty(),
        flightCode = this?.flightCode.orEmpty(),
        planeType = this?.planeType.orEmpty(),
        seatsAvailable = this?.seatsAvailable ?: 0,
        terminalDeparture = this?.terminal?.departureAirport.orEmpty(),
        terminalArrival = this?.terminal?.destinationAirport.orEmpty(),
        isPromo = this?.isPromo ?: false,
        departureAirport = this?.departureAirport.orEmpty(),
        arrivalAirport = this?.arrivalAirport.orEmpty(),
        departureDate = this?.departureDate.orEmpty(),
        departureTime = this?.departureTime.orEmpty(),
        arrivalDate = this?.arrivalDate.orEmpty(),
        arrivalTime = this?.arrivalTime.orEmpty(),
        departureAirportId = this?.departureAirportId.orEmpty(),
        arrivalAirportId = this?.arrivalAirportId.orEmpty(),
        airlineId = this?.airline?.airlineId.orEmpty(),
        airlineName = this?.airline?.airlineName.orEmpty(),
        airlineCode = this?.airline?.airlineCode.orEmpty(),
        country = this?.airline?.country.orEmpty(),
    )

fun Collection<GetBookingIdentityItemResponse>?.toBookingIdentityList() = this?.map { it.toBookingIdentity() } ?: listOf()
fun Collection<GetBookingFlightDataItemResponse>?.toBookingFlightDataList() = this?.map { it.toBookingFlightData() } ?: listOf()