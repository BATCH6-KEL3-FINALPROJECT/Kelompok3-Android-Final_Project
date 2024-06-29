package com.project.skypass.data.source.network.model.history.userhistory

import com.google.gson.annotations.SerializedName

data class UserHistoryResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: List<UserHistoryItemResponse>?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?,
)
