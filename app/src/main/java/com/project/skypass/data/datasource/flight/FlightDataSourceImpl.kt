package com.project.skypass.data.datasource.flight

import com.project.skypass.data.model.FilterFlight
import com.project.skypass.data.source.network.model.flight.flightdata.GetAllFlightResponse
import com.project.skypass.data.source.network.service.ApiService

class FlightDataSourceImpl(private val service: ApiService) : FlightDataSource {
    override suspend fun getFlightData(
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
    ): GetAllFlightResponse {
        return service.getFlightData(
            departureCity,
            arrivalCity,
            seatsAvailable,
            flightStatus,
            seatClass,
            page,
            limit,
            departureDate,
            departureTime,
            price,
        )
    }

    override fun filterFlightData(): List<FilterFlight> {
        return listOf(
            FilterFlight(criteria = "Tidak ada filter"),
            FilterFlight(criteria = "Tercepat"),
            FilterFlight(criteria = "Terlama"),
            FilterFlight(criteria = "Termurah"),
            FilterFlight(criteria = "Termahal"),
        )
    }
}
