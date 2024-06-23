package com.project.skypass.data.source.network.model.history.detailhistory


import com.google.gson.annotations.SerializedName

data class Airline(
    @SerializedName("airline_code")
    var airlineCode: String?,
    @SerializedName("airline_id")
    var airlineId: String?,
    @SerializedName("airline_name")
    var airlineName: String?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?
)