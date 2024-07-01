package com.project.skypass.data.source.network.model.booking

import com.google.gson.annotations.SerializedName

data class DepartingAirport(
    @SerializedName("city")
    var city: String?,
)
