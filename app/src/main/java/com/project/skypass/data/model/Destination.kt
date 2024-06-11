package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val isRoundTrip: Boolean,
    val from: String,
    val to: String,
    val departureDate: String,
    val returnDate: String,
    val passengers: String,
    val seatClass: String,
    val discount: Int,
    val price: Double
): Parcelable
