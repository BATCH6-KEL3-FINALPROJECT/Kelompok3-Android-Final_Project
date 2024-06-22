package com.project.skypass.data.source.network.model.search.posthistory.request


import com.google.gson.annotations.SerializedName

data class HistoryRequestResponse(
    @SerializedName("history")
    var history: String?
)