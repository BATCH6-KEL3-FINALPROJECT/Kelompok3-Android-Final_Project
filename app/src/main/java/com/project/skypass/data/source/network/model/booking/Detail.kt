package com.project.skypass.data.source.network.model.booking


import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: Int?
)