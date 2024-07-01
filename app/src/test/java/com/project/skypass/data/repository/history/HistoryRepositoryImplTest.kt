package com.project.skypass.data.repository.history

import com.project.skypass.data.datasource.history.HistoryDataSource
import com.project.skypass.data.model.History
import com.project.skypass.data.model.Response
import com.project.skypass.data.model.TicketHistory
import com.project.skypass.data.source.network.model.history.allhistory.AllHistoryItemResponse
import com.project.skypass.data.source.network.model.history.allhistory.Flight
import com.project.skypass.data.source.network.model.history.allhistory.Passenger
import com.project.skypass.data.source.network.model.history.allhistory.Seat
import com.project.skypass.data.source.network.model.history.allhistory.Ticket
import com.project.skypass.data.source.network.model.history.detailhistory.Airline
import com.project.skypass.data.source.network.model.history.detailhistory.ArrivingAirport
import com.project.skypass.data.source.network.model.history.detailhistory.DepartingAirport
import com.project.skypass.data.source.network.model.history.detailhistory.Detail
import com.project.skypass.data.source.network.model.history.detailhistory.DetailHistoryItemResponse
import com.project.skypass.data.source.network.model.history.detailhistory.DetailHistoryResponse
import com.project.skypass.data.source.network.model.history.detailhistory.FlightDescription
import com.project.skypass.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class HistoryRepositoryImplTest {
    @MockK
    lateinit var ds: HistoryDataSource
    private lateinit var repo: HistoryRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = HistoryRepositoryImpl(ds)
    }

    @Test
    fun getHistory_success() =
        runBlocking {
            val token = "token"
            val seat = Seat(seatClass = "Economy")
            val airline =
                Airline(
                    "airline_code",
                    "airline_id",
                    "airline_name",
                    "country",
                    "createdAt",
                    "updatedAt",
                )
            val passenger =
                Passenger(
                    firstName = "John",
                    lastName = "Doe",
                    passengerId = "passenger_id",
                )
            val ticket = Ticket(passenger = passenger, seat = seat, seatNumber = "seat_number")
            val flight =
                Flight(
                    arrivalAirport = "arrival_airport",
                    arrivalAirportId = "arrival_airport_id",
                    arrivalDate = "2022-01-01",
                    arrivalTime = "10:00",
                    arrivingAirport = ArrivingAirport("Jakarta"),
                    departingAirport = DepartingAirport("Bali"),
                    departureAirport = "departure_airport",
                    departureAirportId = "departure_airport_id",
                    departureDate = "2022-01-01",
                    departureTime = "08:00",
                    flightDuration = 2,
                    flightId = "flight_id",
                    flightStatus = "flight_status",
                    terminal = true,
                    flightCode = "flight_code",
                    flightDescription =
                        FlightDescription(
                            listOf(
                                Detail(
                                    description = "description",
                                    id = 1,
                                ),
                            ),
                        ),
                    airline = airline,
                )
            val ticketHistory =
                listOf(
                    TicketHistory(
                        id = "id",
                        passengerId = "passenger_id",
                        seatNumber = "seat_number",
                        seatClass = "Economy",
                        passengerFirstName = "John",
                        passengerLastName = "Doe",
                    ),
                )
            val allHistoryResponse: Response<List<AllHistoryItemResponse>?> =
                Response(
                    true,
                    "Success",
                    listOf(
                        AllHistoryItemResponse(
                            bookingCode = "booking_code",
                            bookingDate = "2022-01-01",
                            bookingId = "booking_id",
                            flight = flight,
                            flightId = "flight_id",
                            isRoundTrip = true,
                            noOfTicket = 2,
                            paymentId = "payment_id",
                            status = "status",
                            tickets = listOf(ticket),
                            totalPrice = "100000",
                            userId = "user_id",
                        ),
                    ),
                )
            val history: List<History>? =
                listOf(
                    History(
                        id = "id",
                        bookingCode = "booking_code",
                        bookingId = "booking_id",
                        userId = "user_id",
                        flightId = "flight_id",
                        paymentId = "payment_id",
                        bookingDate = "2022-01-01",
                        isRoundTrip = true,
                        noOfTickets = 2,
                        status = "status",
                        totalPrice = "100000",
                        flightDuration = 2,
                        flightStatus = "flight_status",
                        terminal = true,
                        departureAirport = "departure_airport",
                        arrivalAirport = "arrival_airport",
                        departureDate = "2022-01-01",
                        departureTime = "08:00",
                        arrivalDate = "2022-01-01",
                        arrivalTime = "10:00",
                        departureAirportId = "departure_airport_id",
                        arrivalAirportId = "arrival_airport_id",
                        departingAirport = "Bali",
                        arrivingAirport = "Jakarta",
                        airlineName = "airline_name",
                        airlineCode = "airline_code",
                        country = "country",
                        ticketIdentity = ticketHistory,
                    ),
                )
            coEvery { ds.getHistory("Bearer $token") } returns allHistoryResponse

            val result = repo.getHistory(token)

            result.collect { resultWrapper ->
                when (resultWrapper) {
                    is ResultWrapper.Success ->
                        assertEquals(
                            history?.map { it.copy(id = "id", ticketIdentity = ticketHistory) },
                            resultWrapper.payload?.map { it.copy(id = "id", ticketIdentity = ticketHistory) },
                        )

                    is ResultWrapper.Loading -> delay(100)
                    else -> fail("Expected Success result, but got ${result::class.simpleName}")
                }
            }
            coVerify { ds.getHistory("Bearer $token") }
        }

    @Test
    fun getDetailHistory_success() =
        runBlocking {
            val token = "token"
            val id = "id"
            val expectedHistory =
                listOf(
                    History(
                        bookingId = "booking_id",
                        bookingCode = "booking_code",
                        userId = "user_id",
                        flightId = "flight_id",
                        paymentId = "payment_id",
                        bookingDate = "2022-01-01",
                        isRoundTrip = true,
                        noOfTickets = 2,
                        status = "status",
                        totalPrice = "100000",
                        flightDuration = 2,
                        flightStatus = "flight_status",
                        terminal = true,
                        departureAirport = "departure_airport",
                        arrivalAirport = "arrival_airport",
                        departureDate = "2022-01-01",
                        departureTime = "08:00",
                        arrivalDate = "2022-01-01",
                        arrivalTime = "10:00",
                        departureAirportId = "departure_airport_id",
                        arrivalAirportId = "arrival_airport_id",
                        departingAirport = "Bali",
                        arrivingAirport = "Jakarta",
                        airlineName = "airline_name",
                        airlineCode = "airline_code",
                        country = "country",
                        ticketIdentity =
                            listOf(
                                TicketHistory(
                                    id = "id",
                                    passengerId = "passenger_id",
                                    seatNumber = "seat_number",
                                    seatClass = "Economy",
                                    passengerFirstName = "John",
                                    passengerLastName = "Doe",
                                ),
                            ),
                    ),
                )

            val detailHistoryResponse =
                DetailHistoryResponse(
                    200,
                    DetailHistoryItemResponse(
                        bookingCode = "booking_code",
                        bookingDate = "2022-01-01",
                        bookingId = "booking_id",
                        flight =
                            com.project.skypass.data.source.network.model.history.detailhistory.Flight(
                                airline =
                                    Airline(
                                        "airline_code",
                                        "airline_id",
                                        "airline_name",
                                        "country",
                                        "createdAt",
                                        "updatedAt",
                                    ),
                                arrivalAirport = "arrival_airport",
                                arrivalAirportId = "arrival_airport_id",
                                arrivalDate = "2022-01-01",
                                arrivalTime = "10:00",
                                arrivingAirport = ArrivingAirport("Jakarta"),
                                departingAirport = DepartingAirport("Bali"),
                                departureAirport = "departure_airport",
                                departureAirportId = "departure_airport_id",
                                departureDate = "2022-01-01",
                                departureTime = "08:00",
                                flightCode = "flight_code",
                                flightDescription = FlightDescription(listOf(Detail(description = "description", id = 1))),
                                flightDuration = 2,
                                flightId = "flight_id",
                                flightStatus = "flight_status",
                                terminal = true,
                            ),
                        flightId = "flight_id",
                        isRoundTrip = true,
                        noOfTicket = 2,
                        paymentId = "payment_id",
                        status = "status",
                        tickets =
                            listOf(
                                com.project.skypass.data.source.network.model.history.detailhistory.Ticket(
                                    passenger =
                                        com.project.skypass.data.source.network.model.history.detailhistory.Passenger(
                                            firstName = "John",
                                            lastName = "Doe",
                                            passengerId = "passenger_id",
                                            passengerType = "passenger_type",
                                        ),
                                    seat = com.project.skypass.data.source.network.model.history.detailhistory.Seat(seatClass = "Economy"),
                                    seatNumber = "seat_number",
                                ),
                            ),
                        totalPrice = "100000",
                        userId = "user_id",
                        createdAt = "2022-01-01",
                        totalAdult = 2,
                        totalBaby = 0,
                        totalChild = 0,
                        adultPrice = 100000,
                    ),
                    true,
                )

            coEvery { ds.getDetailHistory("Bearer $token", id) } returns detailHistoryResponse
            val result = repo.getDetailHistory(token, id)
            result.collect { resultWrapper: ResultWrapper<History> ->
                when (resultWrapper) {
                    is ResultWrapper.Success -> {
                        assertEquals(expectedHistory[0].bookingId, resultWrapper.payload?.bookingId)
                    }
                    is ResultWrapper.Loading -> delay(100)
                    else -> fail("Expected Success result, but got ${resultWrapper::class.simpleName}")
                }
            }
            coVerify { ds.getDetailHistory("Bearer $token", id) }
        }
}
