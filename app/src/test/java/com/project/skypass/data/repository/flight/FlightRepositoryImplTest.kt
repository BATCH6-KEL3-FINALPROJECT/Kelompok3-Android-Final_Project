package com.project.skypass.data.repository.flight

import com.project.skypass.data.datasource.flight.FlightDataSource
import com.project.skypass.data.source.network.model.flight.flightdata.Data
import com.project.skypass.data.source.network.model.flight.flightdata.Detail
import com.project.skypass.data.source.network.model.flight.flightdata.FlightDescription
import com.project.skypass.data.source.network.model.flight.flightdata.GetAllFlightItemResponse
import com.project.skypass.data.source.network.model.flight.flightdata.GetAllFlightResponse
import com.project.skypass.data.source.network.model.flight.flightdata.Pagination
import com.project.skypass.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FlightRepositoryImplTest {
    @MockK
    lateinit var ds: FlightDataSource
    private lateinit var repo: FlightRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = FlightRepositoryImpl(ds)
    }

    @Test
    fun `getFlights success`() =
        runBlocking {
            val departureCity = "Jakarta"
            val arrivalCity = "Bali"
            val seatsAvailable = 100
            val flightStatus = "On Time"
            val seatClass = "Economy"
            val page = 1
            val limit = 10
            val departureDate = "2023-03-01"
            val price = 100
            val departureTime = "10:00"

            val flights =
                listOf(
                    GetAllFlightItemResponse(
                        airlineCode = "AA",
                        airlineName = "Airline A",
                        arrivalAirportName = "Airport B",
                        arrivalCity = "Bali",
                        arrivalDate = "2023-06-19",
                        arrivalIataCode = "BIA",
                        arrivalTime = "12:00",
                        departureAirportName = "Airport A",
                        departureCity = "Jakarta",
                        departureDate = "2023-06-19",
                        departureIataCode = "AIA",
                        departureTime = "10:00",
                        flightCode = "AA123",
                        flightDescription =
                            FlightDescription(
                                details =
                                    List<Detail>(1) {
                                        Detail(
                                            description = "Flight Description",
                                            id = 1,
                                        )
                                    },
                            ),
                        flightDuration = 120,
                        flightId = "12345",
                        flightStatus = "On Time",
                        planeType = "Boeing 737",
                        price = 200,
                        priceForChild = 150,
                        priceForInfant = 100,
                        seatClass = "Economy",
                        seatsAvailable = 100,
                        terminal = true,
                    ),
                )
            val pagination = Pagination(100, 10, 10, 10)
            val data = Data(flights = flights, pagination = pagination)
            val expectedResponse =
                GetAllFlightResponse(
                    code = 200,
                    data = data,
                    isSuccess = true,
                    message = "Success",
                )

            coEvery {
                ds.getFlightData(
                    departureCity,
                    arrivalCity,
                    seatsAvailable.toString(),
                    flightStatus,
                    seatClass,
                    page.hashCode(),
                    limit.hashCode(),
                    departureDate,
                    price.toString(),
                    departureTime,
                )
            } returns expectedResponse

            val flow =
                repo.getFlights(
                    departureCity,
                    arrivalCity,
                    seatsAvailable.toString(),
                    flightStatus,
                    seatClass,
                    page,
                    limit,
                    departureDate,
                    price.toString(),
                    departureTime,
                )
            flow.collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        assertNotNull(result.payload)
                        assertEquals(1, result.payload?.size)
                        assertEquals(departureCity, result.payload?.get(0)?.departureCity)
                        assertEquals(arrivalCity, result.payload?.get(0)?.arrivalCity)
                        assertEquals(seatsAvailable.toInt(), result.payload?.get(0)?.seatsAvailable)
                        assertEquals(flightStatus, result.payload?.get(0)?.flightStatus)
                        assertEquals(seatClass, result.payload?.get(0)?.seatClass)
                    }
                    is ResultWrapper.Loading -> delay(100)
                    else -> fail("Expected Success result, but got $result")
                }
            }
            coVerify {
                ds.getFlightData(
                    departureCity,
                    arrivalCity,
                    seatsAvailable.toString(),
                    flightStatus,
                    seatClass,
                    page.hashCode(),
                    limit.hashCode(),
                    departureDate,
                    price.toString(),
                    departureTime,
                )
            }
        }
}
