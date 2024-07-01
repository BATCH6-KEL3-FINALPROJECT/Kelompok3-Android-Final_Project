package com.project.skypass.data.source.network.model.booking

import com.google.gson.annotations.SerializedName

data class FlightDescription(
    @SerializedName("details")
    var details: List<Detail>?,
)
