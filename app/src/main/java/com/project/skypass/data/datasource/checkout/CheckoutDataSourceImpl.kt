package com.project.skypass.data.datasource.checkout

import com.project.skypass.data.source.network.model.booking.GetBookingResponse
import com.project.skypass.data.source.network.model.checkout.request.CheckoutRequestResponse
import com.project.skypass.data.source.network.model.checkout.response.CheckoutResponse
import com.project.skypass.data.source.network.model.payment.PaymentResponse
import com.project.skypass.data.source.network.service.ApiService

class CheckoutDataSourceImpl(private val service: ApiService): CheckoutDataSource {
    override suspend fun createBooking(
        token: String,
        bookingPayload: CheckoutRequestResponse
    ): CheckoutResponse {
        return service.bookingTicket(token, bookingPayload)
    }

    override suspend fun getBookingData(token: String, bookingId: String): GetBookingResponse {
        return service.getBooking(token, bookingId)
    }

    override suspend fun createPayment(token: String, paymentId: String): PaymentResponse {
        return service.createPayment(token, paymentId)
    }
}