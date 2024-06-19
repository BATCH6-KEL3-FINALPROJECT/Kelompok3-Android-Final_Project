package com.project.skypass.data.source.network.model.seat


import com.google.gson.annotations.SerializedName

data class SeatResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: Data?,
    @SerializedName("is_success")
    var isSuccess: Boolean?
)