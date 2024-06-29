package com.project.skypass.data.source.network.model.flight.flightdata

import com.google.gson.annotations.SerializedName

data class GetAllFlightResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: Data?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?,
)
