package com.project.skypass.data.mapper

import com.project.skypass.data.model.Ticket
import com.project.skypass.data.source.network.model.ticket.TicketItemResponse

fun TicketItemResponse?.toTicket() =
    Ticket(
        ticketId = this?.ticket_id.orEmpty(),
        ticketCode = this?.ticket_code.orEmpty(),
        flightId = this?.flight_id.orEmpty(),
        seatId = this?.seat_id.orEmpty(),
        passengerId = this?.passenger_id.orEmpty(),
        bookingId = this?.booking_id.orEmpty(),
        seatNumber = this?.seat_number.orEmpty(),
        passengerName = this?.passenger_name.orEmpty(),
        terminal = this?.TERMINAL.orEmpty(),
        ticketStatus = this?.ticket_status.orEmpty(),
        createdAt = this?.createdAt.orEmpty(),
        updatedAt = this?.updatedAt.orEmpty()
    )

fun Collection<TicketItemResponse>?.toTicketList() = this?.map { it.toTicket() } ?: listOf()