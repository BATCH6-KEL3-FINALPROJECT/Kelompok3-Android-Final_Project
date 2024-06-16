package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderPassangers(
    // order name
    val name: String?,
    val familyName: String?,
    val noTelephone: String?,
    val email: String?,

    // order passengers
    val passengers: List<Passenger>?
):Parcelable
