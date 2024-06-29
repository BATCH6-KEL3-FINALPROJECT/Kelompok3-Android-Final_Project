package com.project.skypass.data.source.network.model.history.userhistory

import com.google.gson.annotations.SerializedName

data class FlightDescription(
    @SerializedName("details")
    var details: List<Detail>?,
)
