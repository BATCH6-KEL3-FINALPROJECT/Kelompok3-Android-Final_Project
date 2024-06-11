package com.project.skypass.data.datasource.flight

import com.project.skypass.data.source.network.model.flight.flightdata.FlightResponse

interface FlightDataSource {
    suspend fun getFlightData(
        departureCity: String,
        arrivalCity: String,
        departureTime: String,
        arrivalTime: String,
        flightDuration: String,
        seatsAvailable: String,
        flightStatus: String,
        seatClass: String,
        departureContinent: String,
        arrivalContinent: String,
        page: Int,
        limit: Int,
        departureDate: String
    ): FlightResponse
}