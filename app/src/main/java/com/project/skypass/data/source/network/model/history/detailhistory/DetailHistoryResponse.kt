package com.project.skypass.data.source.network.model.history.detailhistory

import com.google.gson.annotations.SerializedName

data class DetailHistoryResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: DetailHistoryItemResponse?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
)
