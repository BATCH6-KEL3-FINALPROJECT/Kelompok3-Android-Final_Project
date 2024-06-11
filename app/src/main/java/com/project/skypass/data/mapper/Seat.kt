package com.project.skypass.data.mapper

import com.project.skypass.data.model.Seat
import com.project.skypass.data.source.network.model.seat.SeatItemResponse

fun SeatItemResponse?.toSeat() =
    Seat(
        flightId = this?.flight_id.orEmpty(),
        isAvailable = this?.is_available.orEmpty(),
        column = this?.column.orEmpty(),
        row = this?.row ?: 0,
        seatId = this?.seat_id.orEmpty(),
        seatNumber = this?.seat_number.orEmpty(),
        seatClass = this?.seat_class.orEmpty(),
    )

fun Collection<SeatItemResponse>?.toSeatList() = this?.map { it.toSeat() } ?: listOf()