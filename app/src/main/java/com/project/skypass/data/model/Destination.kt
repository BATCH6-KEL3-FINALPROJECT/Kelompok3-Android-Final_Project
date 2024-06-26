package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Destination(
    var id: String = UUID.randomUUID().toString(),
    val airline: String,
    val continent: String,
    val departureDate: String,
    val discount: String,
    val from: String,
    val imageUrl: String,
    val isRoundTrip: Boolean,
    val passengersAdult: Int,
    val passengersBaby: Int,
    val passengersChild: Int,
    val passengersTotal: Int,
    val price: Int,
    val returnDate: String,
    val seatClass: String,
    val to: String
): Parcelable
