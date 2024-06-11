package com.project.skypass.data.repository.flight

import com.project.skypass.data.model.Flight
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun getFlights(
        departureCity: String?,
        arrivalCity: String?,
        departureTime: String?,
        arrivalTime: String?,
        flightDuration: String?,
        seatsAvailable: String?,
        flightStatus: String?,
        seatClass: String?,
        departureContinent: String?,
        arrivalContinent: String?,
        page: Int?,
        limit: Int?,
        departureDate: String?
    ): Flow<ResultWrapper<List<Flight>>>
}