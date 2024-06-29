package com.project.skypass.data.source.network.model.search.gethistory

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("history")
    var history: List<GetHistoryItemResponse>?,
)
