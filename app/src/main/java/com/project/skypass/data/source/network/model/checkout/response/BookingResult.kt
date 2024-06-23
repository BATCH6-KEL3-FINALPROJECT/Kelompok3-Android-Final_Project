package com.project.skypass.data.source.network.model.checkout.response


import com.google.gson.annotations.SerializedName

data class BookingResult(
    @SerializedName("booking_code")
    var bookingCode: String?,
    @SerializedName("booking_date")
    var bookingDate: String?,
    @SerializedName("booking_id")
    var bookingId: String?,
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("flight_id")
    var flightId: String?,
    @SerializedName("is_round_trip")
    var isRoundTrip: Boolean?,
    @SerializedName("no_of_ticket")
    var noOfTicket: Int?,
    @SerializedName("payment_id")
    var paymentId: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("total_price")
    var totalPrice: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: String?
)