package com.project.skypass.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Passenger(
    var id: Int?,
    val name: String,
    val familyName: String,
    val citizen: String,
    val cardIdentity: String,
    val countryCreated: String,
    val dueDate: String
): Parcelable
