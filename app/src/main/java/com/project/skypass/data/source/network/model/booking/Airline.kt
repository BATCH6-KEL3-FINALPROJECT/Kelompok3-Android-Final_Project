package com.project.skypass.data.source.network.model.booking


import com.google.gson.annotations.SerializedName

data class Airline(
    @SerializedName("airline_code")
    var airlineCode: String?,
    @SerializedName("airline_id")
    var airlineId: String?,
    @SerializedName("airline_name")
    var airlineName: String?,
    @SerializedName("country")
    var country: String?
)