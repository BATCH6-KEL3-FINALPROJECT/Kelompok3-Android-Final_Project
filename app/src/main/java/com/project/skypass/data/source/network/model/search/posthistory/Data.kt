package com.project.skypass.data.source.network.model.search.posthistory

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("existingHistory")
    var existingHistory: ExistingHistory?,
)
