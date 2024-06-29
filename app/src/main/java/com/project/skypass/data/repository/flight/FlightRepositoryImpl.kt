package com.project.skypass.data.repository.flight

import com.project.skypass.data.datasource.flight.FlightDataSource
import com.project.skypass.data.mapper.toFlightData
import com.project.skypass.data.model.FilterFlight
import com.project.skypass.data.model.Flight
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class FlightRepositoryImpl(private val dataSource: FlightDataSource) : FlightRepository {
    override fun getFlights(
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
    ): Flow<ResultWrapper<List<Flight>>> {
        return proceedFlow {
            dataSource.getFlightData(
                departureCity.orEmpty(),
                arrivalCity.orEmpty(),
                seatsAvailable.orEmpty(),
                flightStatus.orEmpty(),
                seatClass.orEmpty(),
                page.hashCode(),
                limit.hashCode(),
                departureDate.orEmpty(),
                departureTime.orEmpty(),
                price.orEmpty(),
            ).data?.flights.toFlightData()
        }
    }

    override fun filterFlights(): List<FilterFlight> {
        return dataSource.filterFlightData()
    }
}
