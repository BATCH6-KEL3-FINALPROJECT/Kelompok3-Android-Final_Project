package com.project.skypass.data.datasource.flight

import com.project.skypass.data.source.network.model.flight.flightdata.Data
import com.project.skypass.data.source.network.model.flight.flightdata.Detail
import com.project.skypass.data.source.network.model.flight.flightdata.FlightDescription
import com.project.skypass.data.source.network.model.flight.flightdata.GetAllFlightItemResponse
import com.project.skypass.data.source.network.model.flight.flightdata.GetAllFlightResponse
import com.project.skypass.data.source.network.model.flight.flightdata.Pagination
import com.project.skypass.data.source.network.service.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FlightDataSourceImplTest {

    @MockK
    lateinit var service: ApiService
    private lateinit var ds: FlightDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = FlightDataSourceImpl(service)
    }

    @Test
    fun getFlightData() = runBlocking {
        val departureCity = "CityA"
        val arrivalCity = "CityB"
        val seatsAvailable = "100"
        val flightStatus = "On Time"
        val seatClass = "Economy"
        val page = 1
        val limit = 10
        val departureDate = "2023-06-19"
        val departureTime = "10:00"
        val price = "100"

        val flights = listOf(
            GetAllFlightItemResponse(
                airlineCode = "AA",
                airlineName = "Airline A",
                arrivalAirportName = "Airport B",
                arrivalCity = "CityB",
                arrivalDate = "2023-06-19",
                arrivalIataCode = "BIA",
                arrivalTime = "12:00",
                departureAirportName = "Airport A",
                departureCity = "CityA",
                departureDate = "2023-06-19",
                departureIataCode = "AIA",
                departureTime = "10:00",
                flightCode = "AA123",
                flightDescription = FlightDescription(details = List<Detail>(1) {
                    Detail(
                        description = "Flight Description",
                        id = 1
                    )
                }),
                flightDuration = 120,
                flightId = "12345",
                flightStatus = "On Time",
                planeType = "Boeing 737",
                price = 200,
                priceForChild = 150,
                priceForInfant = 100,
                seatClass = "Economy",
                seatsAvailable = 100,
                terminal = true
            )
        )
        val pagination = Pagination(100, 10, 10, 10)
        val data = Data(flights = flights, pagination = pagination)
        val expectedResponse = GetAllFlightResponse(
            code = 200,
            data = data,
            isSuccess = true,
            message = "Success"
        )

        coEvery {
            service.getFlightData(
                departureCity,
                arrivalCity,
                seatsAvailable,
                flightStatus,
                seatClass,
                page,
                limit,
                departureDate,
                departureTime,
                price
            )
        } returns expectedResponse

        val result = ds.getFlightData(
            departureCity,
            arrivalCity,
            seatsAvailable,
            flightStatus,
            seatClass,
            page,
            limit,
            departureDate,
            departureTime,
            price
        )

        assertEquals(expectedResponse, result)
        coVerify {
            service.getFlightData(
                departureCity,
                arrivalCity,
                seatsAvailable,
                flightStatus,
                seatClass,
                page,
                limit,
                departureDate,
                departureTime,
                price
            )
        }
    }
}