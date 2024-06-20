package com.project.skypass.data.source.network.model.flight.flightdata


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("flights")
    var flights: List<GetAllFlightItemResponse>?,
    @SerializedName("pagination")
    var pagination: Pagination?
)