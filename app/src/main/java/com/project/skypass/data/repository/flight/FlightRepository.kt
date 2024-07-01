package com.project.skypass.data.repository.flight

import com.project.skypass.data.model.FilterFlight
import com.project.skypass.data.model.Flight
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun getFlights(
        departureCity: String?,
        arrivalCity: String?,
        seatsAvailable: String?,
        flightStatus: String?,
        seatClass: String?,
        page: Int?,
        limit: Int?,
        departureDate: String?,
        departureTime: String?,
        price: String?,
    ): Flow<ResultWrapper<List<Flight>>>

    fun filterFlights(): List<FilterFlight>
}
