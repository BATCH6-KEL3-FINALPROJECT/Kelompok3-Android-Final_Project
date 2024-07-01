package com.project.skypass.data.datasource.flight

import com.project.skypass.data.model.FilterFlight
import com.project.skypass.data.source.network.model.flight.flightdata.GetAllFlightResponse

interface FlightDataSource {
    suspend fun getFlightData(
        departureCity: String,
        arrivalCity: String,
        seatsAvailable: String,
        flightStatus: String,
        seatClass: String,
        page: Int,
        limit: Int,
        departureDate: String,
        departureTime: String,
        price: String,
    ): GetAllFlightResponse

    fun filterFlightData(): List<FilterFlight>
}
