package com.project.skypass.data.source.network.model.history.detailhistory

import com.google.gson.annotations.SerializedName

data class Seat(
    @SerializedName("seat_class")
    var seatClass: String?,
)
