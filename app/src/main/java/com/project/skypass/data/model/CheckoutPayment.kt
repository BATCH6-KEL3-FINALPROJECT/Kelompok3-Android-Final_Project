package com.project.skypass.data.model

data class CheckoutPayment(
    val totalAmount: Int,
    val departureFlightId: String,
    val returnFlightId: String,
    val passengersData: List<PassengersData>?,
    val noOfPassenger: Int
)
