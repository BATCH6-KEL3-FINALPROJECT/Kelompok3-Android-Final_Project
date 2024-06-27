package com.project.skypass.data.repository.checkout

import com.project.skypass.data.datasource.checkout.CheckoutDataSource
import com.project.skypass.data.model.Booking
import com.project.skypass.data.model.PassengersData
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
import com.project.skypass.data.source.network.model.checkout.response.Data
import com.project.skypass.data.source.network.model.checkout.response.ReturnBookingResult
import com.project.skypass.data.source.network.model.payment.PaymentItemResponse
import com.project.skypass.data.source.network.model.payment.PaymentResponse
import com.project.skypass.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class CheckoutRepositoryImplTest {

    @MockK
    lateinit var ds: CheckoutDataSource
    private lateinit var repo: CheckoutRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = CheckoutRepositoryImpl(ds)
    }

    @Test
    fun createBooking() = runBlocking {
        val token = "token"
        val totalAmount = 100000
        val departureFlightId = "departureFlightId"
        val returnFlightId = "returnFlightId"
        val fullName = "full name"
        val familyName = "family name"
        val email = "email@example.com"
        val phone = "081234567890"
        val passenger = listOf(
            PassengersData(
                dateOfBirth = "1990-01-01",
                seatsDepartureId = "seatsDepartureId",
                email = "email@example.com",
                firstName = "first name",
                issuingCountry = "issuing country",
                lastName = "last name",
                nationality = "nationality",
                passengerType = "passenger type",
                passportNo = "passport no",
                phoneNumber = "081234567890",
                seatsArrivalId = "seatsArrivalId",
                title = "title",
                validUntil = "2030-01-01"
            )
        )

        val buyerData = BuyerData(
            email = email,
            familyName = familyName,
            fullName = fullName,
            phone = phone
        )
        val request = CheckoutRequestResponse(
            buyerData = buyerData,
            departureFlightId = departureFlightId,
            noOfPassenger = passenger.size,
            passengersData = passenger.map {
                PassengerData(
                    dateOfBirth = it.dateOfBirth,
                    departureSeats = it.seatsDepartureId,
                    email = it.email,
                    firstName = it.firstName,
                    issuingCountry = it.issuingCountry,
                    lastName = it.lastName,
                    nationality = it.nationality,
                    passengerType = it.passengerType,
                    passportNo = it.passportNo,
                    phoneNumber = it.phoneNumber,
                    returnSeats = it.seatsArrivalId,
                    title = it.title,
                    validUntil = it.validUntil
                )
            },
            returnFlightId = returnFlightId,
            totalAmount = totalAmount
        )

        val tokenBearer = "Bearer $token"
        val bookingResult = BookingResult("bookingCode", "bookingDate", "bookingId", "createdAt", "flightId", true, 1, "paymentId", "status", "totalPrice", "updatedAt", "userId")
        val returnBookingResult = ReturnBookingResult("bookingCode", "bookingDate", "bookingId", "createdAt", "flightId", true, 1, "paymentId", "status", "totalPrice", "updatedAt", "userId")
        val data = Data(bookingResult, returnBookingResult)
        val response = CheckoutResponse(200, data, true, "success")

        coEvery { ds.createBooking(tokenBearer, request) } returns response

        repo.createBooking(
            token,
            totalAmount,
            departureFlightId,
            returnFlightId,
            fullName,
            familyName,
            email,
            phone,
            passenger
        ).collect { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    assertEquals(response, result.payload?.let { ds.createBooking(tokenBearer, request) })
                }
                is ResultWrapper.Loading -> delay(100)
                else -> fail("Expected Success result, but got ${result::class.simpleName}")
            }
        }
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

        val response = ResultWrapper.Success(data)

        coEvery { ds.getBookingData("Bearer $token", bookingId) } returns GetBookingDataResponse(
            code = 200,
            data = data,
            isSuccess = true,
            message = "success"
        )

        val result = repo.getBookingData(token, bookingId)
        result.collect { resultWrapper ->
            when (resultWrapper) {
                is ResultWrapper.Success -> {
                    val expectedData = response.payload
                    val actualData = resultWrapper.payload
                    assertEquals(expectedData?.bookingData?.size, actualData?.size)
                    expectedData?.bookingData?.forEachIndexed { index, expectedItem ->
                        val actualItem = actualData?.get(index)
                        assertEquals(expectedItem.bookingId, actualItem?.bookingId)
                        assertEquals(expectedItem.bookingCode, actualItem?.bookingCode)
                        assertEquals(expectedItem.userId, actualItem?.userId)
                        assertEquals(expectedItem.flightId, actualItem?.flightId)
                        assertEquals(expectedItem.paymentId, actualItem?.paymentId)
                        assertEquals(expectedItem.bookingDate, actualItem?.bookingDate)
                        assertEquals(expectedItem.isRoundTrip, actualItem?.isRoundTrip)
                        assertEquals(expectedItem.noOfTicket, actualItem?.noOfTicket)
                        assertEquals(expectedItem.status, actualItem?.status)
                        assertEquals(expectedItem.totalPrice, actualItem?.totalPrice)
                        assertEquals(expectedItem.createdAt, actualItem?.createdAt)
                        assertEquals(expectedItem.updatedAt, actualItem?.updatedAt)
                        assertEquals(expectedItem.adultPrice, actualItem?.adultPrice)
                        assertEquals(expectedItem.seatClass, actualItem?.seatClass)
                        assertEquals(expectedItem.totalAdult, actualItem?.totalAdult)
                        assertEquals(expectedItem.totalChild, actualItem?.totalChild)
                        assertEquals(expectedItem.totalBaby, actualItem?.totalBaby)
                    }
                }
                is ResultWrapper.Loading -> delay(100)
                else -> fail("Expected Success result, but got ${result::class.simpleName}")
            }
        }
        coVerify { ds.getBookingData("Bearer $token", bookingId) }
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

        coEvery { ds.createPayment("Bearer $token", paymentId) } returns response
        val result = repo.createPayment(token, paymentId)
        result.collect{ resultWrapper ->
            when (resultWrapper) {
                is ResultWrapper.Success -> {
                assertEquals(response, resultWrapper.payload?.let { ds.createPayment("Bearer $token", paymentId) })
                }
                is ResultWrapper.Loading -> delay(100)
                else -> fail("Expected Success result, but got ${result::class.simpleName}")
            }
        }
        coVerify { ds.createPayment("Bearer $token", paymentId) }
    }
}