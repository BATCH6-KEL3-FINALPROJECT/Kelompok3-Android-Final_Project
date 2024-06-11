package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormHome(
    val isRoundTrip: Boolean,
    val from: String,
    val to: String,
    val departureDate: String,
    val returnDate: String,
    val passengers: Int,
    val seatClass: String
): Parcelable
