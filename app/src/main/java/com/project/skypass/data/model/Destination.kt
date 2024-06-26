package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Destination(
    var id: String = UUID.randomUUID().toString(),
    val isRoundTrip: Boolean,
    val from: String,
    val to: String,
    val departureDate: String,
    val returnDate: String,
    val passengers: Int,
    val seatClass: String,
    val discount: String,
    val price: Double,
    val imageUrl: String,
    val airline: String
): Parcelable
