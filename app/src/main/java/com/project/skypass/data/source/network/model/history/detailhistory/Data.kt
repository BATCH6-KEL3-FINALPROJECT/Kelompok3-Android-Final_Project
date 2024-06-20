package com.project.skypass.data.source.network.model.history.detailhistory


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("booking")
    var booking: DetailHistoryItemResponse?
)