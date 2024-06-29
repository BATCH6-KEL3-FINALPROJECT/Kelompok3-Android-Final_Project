package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderPassengers(
    // order name
    val name: String?,
    val familyName: String?,
    val noTelephone: String?,
    val email: String?,
    // order passengers
    val passengers: List<PassengersData>?,
    // order seat
    val seatOrderDeparture: List<String>?,
    val seatOrderArrival: List<String>?,
    // seat id
    val seatIdDeparture: List<String>?,
    val seatIdArrival: List<String>?,
) : Parcelable
