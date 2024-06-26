package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class PassengersData(
    var id: String? = UUID.randomUUID().toString(),
    var title: String,
    var firstName: String,
    var lastName: String,
    var dateOfBirth: String,
    var email: String,
    var phoneNumber: String,
    var nationality: String,
    var passportNo: String,
    val passengerType: String,
    var issuingCountry: String,
    var validUntil: String,
    var seatsDepartureId: String? = null,
    var seatsArrivalId: String? = null
) : Parcelable
