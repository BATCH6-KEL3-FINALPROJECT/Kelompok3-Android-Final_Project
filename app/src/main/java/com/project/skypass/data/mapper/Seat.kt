package com.project.skypass.data.mapper

import com.project.skypass.data.model.Seat
import com.project.skypass.data.source.network.model.seat.SeatItemResponse

fun SeatItemResponse?.toSeat() =
    Seat(
        flightId = this?.flightId.orEmpty(),
        isAvailable = this?.isAvailable.orEmpty(),
        column = this?.column.orEmpty(),
        row = this?.row ?: 0,
        seatId = this?.seatId.orEmpty(),
        seatNumber = this?.seatNumber.orEmpty(),
        seatClass = this?.seatClass.orEmpty(),
    )

fun Collection<SeatItemResponse>?.toSeatList() = this?.map { it.toSeat() } ?: listOf()