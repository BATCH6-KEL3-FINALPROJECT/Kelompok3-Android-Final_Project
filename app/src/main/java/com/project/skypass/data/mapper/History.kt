package com.project.skypass.data.mapper

import com.project.skypass.data.model.History
import com.project.skypass.data.model.TicketHistory
import com.project.skypass.data.source.network.model.history.allhistory.AllHistoryItemResponse
import com.project.skypass.data.source.network.model.history.detailhistory.DetailHistoryItemResponse
import com.project.skypass.data.source.network.model.history.detailhistory.Ticket
import com.project.skypass.data.source.network.model.history.userhistory.UserHistoryItemResponse

fun AllHistoryItemResponse?.toHistory() =
    History(
        bookingId = this?.bookingId.orEmpty(),
        userId = this?.userId.orEmpty(),
        flightId = this?.flightId.orEmpty(),
        paymentId = this?.paymentId.orEmpty(),
        bookingDate = this?.bookingDate.orEmpty(),
        bookingCode = this?.bookingCode.orEmpty(),
        isRoundTrip = this?.isRoundTrip ?: false,
        noOfTickets = this?.noOfTicket ?: 0,
        totalPrice = this?.totalPrice.orEmpty(),
        status = this?.status.orEmpty(),
        flightDuration = this?.flight?.flightDuration ?: 0,
        flightStatus = this?.flight?.flightStatus.orEmpty(),
        terminal = this?.flight?.terminal ?: false,
        departureAirport = this?.flight?.departureAirport.orEmpty(),
        arrivalAirport = this?.flight?.arrivalAirport.orEmpty(),
        departureDate = this?.flight?.departureDate.orEmpty(),
        departureTime = this?.flight?.departureTime.orEmpty(),
        arrivalDate = this?.flight?.arrivalDate.orEmpty(),
        arrivalTime = this?.flight?.arrivalTime.orEmpty(),
        departureAirportId = this?.flight?.departureAirportId.orEmpty(),
        arrivalAirportId = this?.flight?.arrivalAirportId.orEmpty(),
        departingAirport = this?.flight?.departingAirport?.city.orEmpty(),
        arrivingAirport = this?.flight?.arrivingAirport?.city.orEmpty(),
        ticketIdentity = null,
        airlineName = this?.flight?.airline?.airlineName.orEmpty(),
        airlineCode = this?.flight?.airline?.airlineCode.orEmpty(),
        country = this?.flight?.airline?.country.orEmpty(),
    )

fun UserHistoryItemResponse?.toBookingHistory() =
    History(
        bookingId = this?.bookingId.orEmpty(),
        userId = this?.userId.orEmpty(),
        flightId = this?.flightId.orEmpty(),
        paymentId = this?.paymentId.orEmpty(),
        bookingDate = this?.bookingDate.orEmpty(),
        bookingCode = this?.bookingCode.orEmpty(),
        isRoundTrip = this?.isRoundTrip ?: false,
        noOfTickets = this?.noOfTicket ?: 0,
        totalPrice = this?.totalPrice.orEmpty(),
        status = this?.status.orEmpty(),
        flightDuration = this?.flight?.flightDuration ?: 0,
        flightStatus = this?.flight?.flightStatus.orEmpty(),
        terminal = this?.flight?.terminal ?: false,
        departureAirport = this?.flight?.departureAirport.orEmpty(),
        arrivalAirport = this?.flight?.arrivalAirport.orEmpty(),
        departureDate = this?.flight?.departureDate.orEmpty(),
        departureTime = this?.flight?.departureTime.orEmpty(),
        arrivalDate = this?.flight?.arrivalDate.orEmpty(),
        arrivalTime = this?.flight?.arrivalTime.orEmpty(),
        departureAirportId = this?.flight?.departureAirportId.orEmpty(),
        arrivalAirportId = this?.flight?.arrivalAirportId.orEmpty(),
        departingAirport = this?.flight?.departingAirport?.city.orEmpty(),
        arrivingAirport = this?.flight?.arrivingAirport?.city.orEmpty(),
        ticketIdentity = null,
        airlineName = this?.flight?.airline?.airlineName.orEmpty(),
        airlineCode = this?.flight?.airline?.airlineCode.orEmpty(),
        country = this?.flight?.airline?.country.orEmpty(),
    )

fun DetailHistoryItemResponse?.toDetailHistory() =
    History(
        bookingId = this?.bookingId.orEmpty(),
        userId = this?.userId.orEmpty(),
        flightId = this?.flightId.orEmpty(),
        paymentId = this?.paymentId.orEmpty(),
        bookingDate = this?.bookingDate.orEmpty(),
        isRoundTrip = this?.isRoundTrip ?: false,
        noOfTickets = this?.noOfTicket ?: 0,
        totalPrice = this?.totalPrice.orEmpty(),
        status = this?.status.orEmpty(),
        flightDuration = this?.flight?.flightDuration ?: 0,
        flightStatus = this?.flight?.flightStatus.orEmpty(),
        terminal = this?.flight?.terminal ?: false,
        departureAirport = this?.flight?.departureAirport.orEmpty(),
        arrivalAirport = this?.flight?.arrivalAirport.orEmpty(),
        departureDate = this?.flight?.departureDate.orEmpty(),
        departureTime = this?.flight?.departureTime.orEmpty(),
        arrivalDate = this?.flight?.arrivalDate.orEmpty(),
        arrivalTime = this?.flight?.arrivalTime.orEmpty(),
        departureAirportId = this?.flight?.departureAirportId.orEmpty(),
        arrivalAirportId = this?.flight?.arrivalAirportId.orEmpty(),
        departingAirport = this?.flight?.departingAirport?.city.orEmpty(),
        arrivingAirport = this?.flight?.arrivingAirport?.city.orEmpty(),
        airlineName = this?.flight?.airline?.airlineName.orEmpty(),
        airlineCode = this?.flight?.airline?.airlineCode.orEmpty(),
        country = this?.flight?.airline?.country.orEmpty(),
        bookingCode = this?.bookingCode.orEmpty(),
        ticketIdentity = this?.tickets?.toHistoryTicketIdentity()
    )

fun Ticket?.toHistoryTicket() =
    TicketHistory(
        passengerFirstName = this?.passenger?.firstName.orEmpty(),
        passengerLastName = this?.passenger?.lastName.orEmpty(),
        seatClass = this?.seat?.seatClass.orEmpty(),
        seatNumber = this?.seatNumber.orEmpty(),
        passengerId = this?.passenger?.passengerId.orEmpty()
    )

fun Collection<AllHistoryItemResponse>?.toAllHistory() = this?.map { it.toHistory() } ?: listOf()
fun Collection<UserHistoryItemResponse>?.toBookingHistory() = this?.map { it.toBookingHistory() } ?: listOf()
fun Collection<DetailHistoryItemResponse>?.toDetailAllHistory() = this?.map { it.toDetailHistory() } ?: listOf()
fun Collection<Ticket>?.toHistoryTicketIdentity() = this?.map { it.toHistoryTicket() } ?: listOf()