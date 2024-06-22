package com.project.skypass.data.source.network.model.booking


import com.google.gson.annotations.SerializedName

data class Terminal(
    @SerializedName("departure_airport")
    var departureAirport: String?,
    @SerializedName("destination_airport")
    var destinationAirport: String?
)