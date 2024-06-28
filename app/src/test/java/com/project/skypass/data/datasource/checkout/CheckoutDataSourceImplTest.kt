package com.project.skypass.data.datasource.checkout

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.booking.Airline
import com.project.skypass.data.source.network.model.booking.ArrivingAirport
import com.project.skypass.data.source.network.model.booking.DepartingAirport
import com.project.skypass.data.source.network.model.booking.Detail
import com.project.skypass.data.source.network.model.booking.FlightData
import com.project.skypass.data.source.network.model.booking.FlightDescription
import com.project.skypass.data.source.network.model.booking.GetBookingDataItemResponse
import com.project.skypass.data.source.network.model.booking.GetBookingDataResponse
import com.project.skypass.data.source.network.model.checkout.request.BuyerData
import com.project.skypass.data.source.network.model.checkout.request.CheckoutRequestResponse
import com.project.skypass.data.source.network.model.checkout.request.PassengerData
import com.project.skypass.data.source.network.model.checkout.response.BookingResult
import com.project.skypass.data.source.network.model.checkout.response.CheckoutResponse
import com.project.skypass.data.source.network.model.checkout.response.DataCheckout
import com.project.skypass.data.source.network.model.checkout.response.ReturnBookingResult
import com.project.skypass.data.source.network.model.payment.PaymentItemResponse
import com.project.skypass.data.source.network.model.payment.PaymentResponse
import com.project.skypass.data.source.network.service.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class CheckoutDataSourceImplTest {

    @MockK
    lateinit var service: ApiService
    private lateinit var ds: CheckoutDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = CheckoutDataSourceImpl(service)
    }

    @Test
    fun createBooking() = runBlocking {
        val token = "token"
        val buyerData = BuyerData("email", "familyName", "fullName", "phone")
        val passengerData = PassengerData("dateOfBirth", "departureSeats", "email", "firstName", "issuingCountry", "lastName", "nationality", "passengerType", "passportNo", "phoneNumber", "returnSeats", "title", "validUntil")
        val passengersData = listOf(passengerData)
        val bookingPayload = CheckoutRequestResponse(buyerData, "departureFlightId", 1, passengersData, "returnFlightId", 100)

        val bookingResult = BookingResult("bookingCode", "bookingDate", "bookingId", "createdAt", "flightId", true, 1, "paymentId", "status", "totalPrice", "updatedAt", "userId")
        val returnBookingResult = ReturnBookingResult("bookingCode", "bookingDate", "bookingId", "createdAt", "flightId", true, 1, "paymentId", "status", "totalPrice", "updatedAt", "userId")
        val data = DataCheckout(bookingResult, returnBookingResult)
        val response = Response(true, "success", data)

        coEvery { service.bookingTicket(token, bookingPayload) } returns response

        val result = ds.createBooking(token, bookingPayload)
        assertEquals(response, result)
    }

    @Test
    fun getBookingData() = runBlocking {
        val token = "token"
        val bookingId = "bookingId"

        val detail = Detail(
            description = "flight_description",
            id = 1
        )

        val flightDescription = FlightDescription(
            details = listOf(detail)
        )

        val departingAirport = DepartingAirport(
            city = "departing_city"
        )

        val arrivingAirport = ArrivingAirport(
            city = "arriving_city"
        )

        val airline = Airline(
            airlineCode = "airline_code",
            airlineId = "airline_id",
            airlineName = "airline_name",
            country = "country"
        )

        val flightData = FlightData(
            airline = airline,
            airlineId = "airline_id",
            arrivalAirport = "arrival_airport",
            arrivalAirportId = "arrival_airport_id",
            arrivalDate = "2022-01-01",
            arrivalTime = "10:00:00",
            arrivingAirport = arrivingAirport,
            createdAt = "2022-01-01T00:00:00.000Z",
            departingAirport = departingAirport,
            departureAirport = "departure_airport",
            departureAirportId = "departure_airport_id",
            departureDate = "2022-01-01",
            departureTime = "08:00:00",
            flightCode = "flight_code",
            flightDescription = flightDescription,
            flightDuration = 120,
            flightId = "flight_id",
            flightStatus = "flight_status",
            isPromo = true,
            planeType = "plane_type",
            seatsAvailable = 100,
            terminal = true,
            updatedAt = "2022-01-01T00:00:00.000Z"
        )

        val getBookingDataItemResponse = GetBookingDataItemResponse(
            adultPrice = 10,
            adultTotalPrice = 20,
            babyTotalPrice = 30,
            bookingCode = "booking_code",
            bookingDate = "2022-01-01",
            bookingId = "booking_id",
            childTotalPrice = 40,
            createdAt = "2022-01-01T00:00:00.000Z",
            flightData = flightData,
            flightId = "flight_id",
            isRoundTrip = true,
            noOfTicket = 2,
            paymentId = "payment_id",
            seatClass = "seat_class",
            status = "status",
            totalAdult = 1,
            totalBaby = 0,
            totalChild = 1,
            totalPrice = "100000",
            updatedAt = "2022-01-01T00:00:00.000Z",
            userId = "user_id"
        )

        val data = com.project.skypass.data.source.network.model.booking.Data(
            bookingData = listOf(getBookingDataItemResponse),
            totalPrice = 100000
        )

        val response = GetBookingDataResponse(
            code = 200,
            data = data,
            isSuccess = true,
            message = "success"
        )

        coEvery { service.getBooking(token, bookingId) } returns response

        val result = ds.getBookingData(token, bookingId)
        assertEquals(response, result)
    }

    @Test
    fun createPayment() = runBlocking {
        val token = "token"
        val paymentId = "paymentId"

        val paymentItemResponse = PaymentItemResponse(
            token = "payment_token",
            url = "payment_url"
        )

        val response = PaymentResponse(
            code = 200,
            data = paymentItemResponse,
            isSuccess = true,
            message = "success"
        )

        coEvery { service.createPayment(token, paymentId) } returns response

        val result = ds.createPayment(token, paymentId)
        assertEquals(response, result)
    }
}