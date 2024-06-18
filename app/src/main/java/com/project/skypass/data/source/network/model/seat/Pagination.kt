package com.project.skypass.data.source.network.model.seat


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("limitData")
    var limitData: Int?,
    @SerializedName("pageNum")
    var pageNum: Int?,
    @SerializedName("totalData")
    var totalData: Int?,
    @SerializedName("totalPages")
    var totalPages: Int?
)