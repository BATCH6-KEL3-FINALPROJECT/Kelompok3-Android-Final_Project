package com.project.skypass.data.datasource.seat

import com.project.skypass.data.source.network.model.seat.SeatResponse
import com.project.skypass.data.source.network.service.ApiService

class SeatsDataSourceImpl(private val service: ApiService) : SeatsDataSource {
    override suspend fun getSeats(
        seatsClass: String,
        flightId: String,
        page: Int,
    ): SeatResponse {
        return service.getSeatData(seatsClass, flightId, page)
    }
}
