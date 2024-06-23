package com.project.skypass.data.source.network.model.search.gethistory


import com.google.gson.annotations.SerializedName

data class GetHistoryItemResponse(
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("history")
    var history: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("updatedAt")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: String?
)