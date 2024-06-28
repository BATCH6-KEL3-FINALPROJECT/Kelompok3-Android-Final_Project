package com.project.skypass.data.datasource.history

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.history.allhistory.AllHistoryItemResponse
import com.project.skypass.data.source.network.model.history.allhistory.AllHistoryResponse
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
import com.project.skypass.data.source.network.service.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HistoryDataSourceImplTest {

    @MockK
    lateinit var service: ApiService
    private lateinit var ds: HistoryDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = HistoryDataSourceImpl(service)
    }

    @Test
    fun getHistory() = runBlocking {
        val token = "token"
        val seat = Seat(seatClass = "Economy")
        val airline = Airline(
            "airline_code",
            "airline_id",
            "airline_name",
            "country",
            "createdAt",
            "updatedAt"
        )
        val passenger = Passenger(
            firstName = "John",
            lastName = "Doe",
            passengerId = "passenger_id"
        )
        val ticket = Ticket(passenger = passenger, seat = seat, seatNumber = "seat_number")
        val flight = Flight(
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
            flightDescription = FlightDescription(
                listOf(
                    Detail(
                        description = "description",
                        id = 1
                    )
                )
            ),
            airline = airline
        )
        val allHistoryItemResponse: List<AllHistoryItemResponse>? = listOf(
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
                userId = "user_id"
            )
        )
        val allHistoryResponse: Response<List<AllHistoryItemResponse>?> = Response(true, "Success", allHistoryItemResponse)
        coEvery { service.getAllHistory(token) } returns allHistoryResponse

        val result = ds.getHistory(token)

        coVerify { service.getAllHistory(token) }
        assertEquals(allHistoryResponse, result)
    }

    @Test
    fun getDetailHistory() = runBlocking {
        val token = "token"
        val bookingId = "booking_id"
        val detail = Detail(description = "description", id = 1)
        val airline = Airline(
            "airline_code",
            "airline_id",
            "airline_name",
            "country",
            "createdAt",
            "updatedAt"
        )
        val flightDescription = FlightDescription(listOf(detail))
        val seat =
            com.project.skypass.data.source.network.model.history.detailhistory.Seat(seatClass = "Economy")
        val passenger =
            com.project.skypass.data.source.network.model.history.detailhistory.Passenger(
                firstName = "John",
                lastName = "Doe",
                passengerId = "passenger_id",
                passengerType = "passenger_type",
            )
        val ticket = com.project.skypass.data.source.network.model.history.detailhistory.Ticket(
            passenger = passenger,
            seat = seat,
            seatNumber = "seat_number"
        )
        val flight = com.project.skypass.data.source.network.model.history.detailhistory.Flight(
            airline = airline,
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
            flightDescription = flightDescription,
            flightDuration = 2,
            flightId = "flight_id",
            flightStatus = "flight_status",
            terminal = true,
        )
        val detailHistoryItemResponse = DetailHistoryItemResponse(
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
            createdAt = "2022-01-01",
            totalAdult = 2,
            totalBaby = 0,
            totalChild = 0,
            adultPrice = 100000
        )
        val detailHistoryResponse = DetailHistoryResponse(
            code = 200,
            data = detailHistoryItemResponse,
            isSuccess = true
        )
        coEvery { service.getDetailHistory(token, bookingId) } returns detailHistoryResponse

        val result = ds.getDetailHistory(token, bookingId)

        coVerify { service.getDetailHistory(token, bookingId) }
        assertEquals(detailHistoryResponse, result)
    }
}