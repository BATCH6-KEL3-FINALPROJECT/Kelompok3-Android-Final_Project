package com.project.skypass.data.source.network.model.history


import com.google.gson.annotations.SerializedName

data class ArrivingAirport(
    @SerializedName("city")
    var city: String?
)