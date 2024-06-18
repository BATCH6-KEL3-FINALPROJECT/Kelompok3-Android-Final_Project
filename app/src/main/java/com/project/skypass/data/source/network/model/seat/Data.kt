package com.project.skypass.data.source.network.model.seat


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("pagination")
    var pagination: Pagination?,
    @SerializedName("seats")
    var seats: List<SeatItemResponse>?
)