package com.project.skypass.data.repository.checkout

import com.project.skypass.data.model.Booking
import com.project.skypass.data.model.PassengersData
import com.project.skypass.data.model.Payment
import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.checkout.response.CheckoutResponse
import com.project.skypass.data.source.network.model.checkout.response.DataCheckout
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface CheckoutRepository {

    fun createBooking(
        token: String,
        totalAmount: Int,
        departureFlightId: String,
        returnFlightId: String?,
        fullName: String?,
        familyName: String?,
        email: String?,
        phone: String?,
        passenger: List<PassengersData>
    ): Flow<ResultWrapper<Response<DataCheckout>>>

    fun getBookingData(token: String, bookingId: String): Flow<ResultWrapper<List<Booking>>>

    fun createPayment(token: String, paymentId: String): Flow<ResultWrapper<Payment>>
}