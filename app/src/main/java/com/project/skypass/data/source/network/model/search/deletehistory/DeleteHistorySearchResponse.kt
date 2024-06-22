package com.project.skypass.data.source.network.model.search.deletehistory


import com.google.gson.annotations.SerializedName

data class DeleteHistorySearchResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?
)