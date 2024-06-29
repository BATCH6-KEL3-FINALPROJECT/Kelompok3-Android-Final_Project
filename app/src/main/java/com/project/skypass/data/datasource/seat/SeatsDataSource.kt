package com.project.skypass.data.datasource.seat

import com.project.skypass.data.source.network.model.seat.SeatResponse

interface SeatsDataSource {
    suspend fun getSeats(
        seatsClass: String,
        flightId: String,
        page: Int,
    ): SeatResponse
}
