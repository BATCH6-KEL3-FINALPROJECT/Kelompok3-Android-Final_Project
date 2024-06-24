package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class TicketHistory (
    var id: String = UUID.randomUUID().toString(),
    val passengerId: String,
    val seatNumber: String,
    val seatClass: String,
    val passengerFirstName: String,
    val passengerLastName: String
): Parcelable