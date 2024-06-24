package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckoutPayment(
    val totalAmount: Int,
    val departureFlightId: String,
    val returnFlightId: String,
    val passengersData: List<PassengersData>?,
    val noOfPassenger: Int
): Parcelable
