package com.project.skypass.data.source.network.model.history.detailhistory


import com.google.gson.annotations.SerializedName

data class DepartingAirport(
    @SerializedName("city")
    var city: String?
)