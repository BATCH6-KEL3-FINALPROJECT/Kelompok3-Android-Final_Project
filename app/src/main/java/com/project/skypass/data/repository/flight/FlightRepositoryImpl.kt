package com.project.skypass.data.repository.flight

import com.project.skypass.data.datasource.flight.FlightDataSource
import com.project.skypass.data.mapper.toFlightData
import com.project.skypass.data.model.Flight
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class FlightRepositoryImpl(private val dataSource: FlightDataSource) : FlightRepository {
    override fun getFlights(
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
    ): Flow<ResultWrapper<List<Flight>>> {
        return proceedFlow {
            dataSource.getFlightData(
                departureCity.orEmpty(),
                arrivalCity.orEmpty(),
                departureTime.orEmpty(),
                arrivalTime.orEmpty(),
                flightDuration.orEmpty(),
                seatsAvailable.orEmpty(),
                flightStatus.orEmpty(),
                seatClass.orEmpty(),
                departureContinent.orEmpty(),
                arrivalContinent.orEmpty(),
                page.hashCode(),
                limit.hashCode(),
                departureDate.orEmpty()
            ).data?.flights.toFlightData()
        }
    }
}