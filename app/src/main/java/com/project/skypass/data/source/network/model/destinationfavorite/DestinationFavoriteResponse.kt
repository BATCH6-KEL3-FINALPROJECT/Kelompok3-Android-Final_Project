package com.project.skypass.data.source.network.model.destinationfavorite

import com.google.gson.annotations.SerializedName

data class DestinationFavoriteResponse(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("data")
    var data: List<DestinationFavoriteItemResponse>?,
    @SerializedName("is_success")
    var isSuccess: Boolean?,
    @SerializedName("message")
    var message: String?,
)
