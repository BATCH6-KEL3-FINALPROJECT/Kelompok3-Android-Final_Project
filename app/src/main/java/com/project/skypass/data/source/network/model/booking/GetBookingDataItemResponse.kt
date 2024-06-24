package com.project.skypass.data.source.network.model.booking


import com.google.gson.annotations.SerializedName

data class GetBookingDataItemResponse(
    @SerializedName("adultPrice")
    var adultPrice: Int?,
    @SerializedName("adultTotalPrice")
    var adultTotalPrice: Int?,
    @SerializedName("babyTotalPrice")
    var babyTotalPrice: Int?,
    @SerializedName("booking_code")
    var bookingCode: String?,
    @SerializedName("booking_date")
    var bookingDate: String?,
    @SerializedName("booking_id")
    var bookingId: String?,
    @SerializedName("childTotalPrice")
    var childTotalPrice: Int?,
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("flightData")
    var flightData: FlightData?,
    @SerializedName("flight_id")
    var flightId: String?,
    @SerializedName("is_round_trip")
    var isRoundTrip: Boolean?,
    @SerializedName("no_of_ticket")
    var noOfTicket: Int?,
    @SerializedName("payment_id")
    var paymentId: String?,
    @SerializedName("seatClass")
    var seatClass: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("totalAdult")
    var totalAdult: Int?,
    @SerializedName("totalBaby")
    var totalBaby: Int?,
    @SerializedName("totalChild")
    var totalChild: Int?,
    @SerializedName("total_price")
    var totalPrice: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: String?
)