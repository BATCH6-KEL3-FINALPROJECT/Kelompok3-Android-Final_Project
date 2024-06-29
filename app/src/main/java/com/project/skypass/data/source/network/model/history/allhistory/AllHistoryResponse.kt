package com.project.skypass.data.source.network.model.history.allhistory

import com.google.gson.annotations.SerializedName

data class AllHistoryResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: List<AllHistoryItemResponse>?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?,
)
