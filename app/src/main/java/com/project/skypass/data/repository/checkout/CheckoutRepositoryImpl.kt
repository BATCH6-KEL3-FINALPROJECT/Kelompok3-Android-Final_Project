package com.project.skypass.data.repository.checkout

import com.project.skypass.data.datasource.checkout.CheckoutDataSource
import com.project.skypass.data.mapper.getListBookingData
import com.project.skypass.data.mapper.toPayment
import com.project.skypass.data.model.Booking
import com.project.skypass.data.model.PassengersData
import com.project.skypass.data.model.Payment
import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.checkout.request.BuyerData
import com.project.skypass.data.source.network.model.checkout.request.CheckoutRequestResponse
import com.project.skypass.data.source.network.model.checkout.request.PassengerData
import com.project.skypass.data.source.network.model.checkout.response.DataCheckout
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.convertFlightDetail
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class CheckoutRepositoryImpl(private val dataSource: CheckoutDataSource) : CheckoutRepository {
    override fun createBooking(
        token: String,
        totalAmount: Int,
        departureFlightId: String,
        returnFlightId: String?,
        fullName: String?,
        familyName: String?,
        email: String?,
        phone: String?,
        passenger: List<PassengersData>,
    ): Flow<ResultWrapper<Response<DataCheckout>>> {
        return proceedFlow {
            val buyerData =
                BuyerData(
                    email = email,
                    familyName = familyName,
                    fullName = fullName,
                    phone = phone,
                )
            val request =
                CheckoutRequestResponse(
                    buyerData = buyerData,
                    departureFlightId = departureFlightId,
                    noOfPassenger = passenger.size,
                    passengersData =
                        passenger.map {
                            PassengerData(
                                dateOfBirth = convertFlightDetail(it.dateOfBirth),
                                departureSeats = it.seatsDepartureId,
                                email = it.email,
                                firstName = it.firstName,
                                issuingCountry = convertFlightDetail(it.issuingCountry),
                                lastName = it.lastName,
                                nationality = it.nationality,
                                passengerType = it.passengerType,
                                passportNo = it.passportNo,
                                phoneNumber = it.phoneNumber,
                                returnSeats = it.seatsArrivalId,
                                title = it.title,
                                validUntil = convertFlightDetail(it.validUntil),
                            )
                        },
                    returnFlightId = returnFlightId,
                    totalAmount = totalAmount,
                )
            val tokenBearer = "Bearer $token"
            dataSource.createBooking(token = tokenBearer, request)
        }
    }

    override fun getBookingData(
        token: String,
        bookingId: String,
    ): Flow<ResultWrapper<List<Booking>>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            dataSource.getBookingData(tokenBearer, bookingId).data?.bookingData.getListBookingData()
        }
    }

    override fun createPayment(
        token: String,
        paymentId: String,
    ): Flow<ResultWrapper<Payment>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            dataSource.createPayment(tokenBearer, paymentId).data.toPayment()
        }
    }
}
