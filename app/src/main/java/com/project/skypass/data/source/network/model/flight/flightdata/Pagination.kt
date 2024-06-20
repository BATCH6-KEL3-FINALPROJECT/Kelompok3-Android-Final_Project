package com.project.skypass.data.source.network.model.flight.flightdata


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("pageNum")
    var pageNum: Int?,
    @SerializedName("pageSize")
    var pageSize: Int?,
    @SerializedName("totalData")
    var totalData: Int?,
    @SerializedName("totalPages")
    var totalPages: Int?
)