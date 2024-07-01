package com.project.skypass.data.source.network.model.payment

import com.google.gson.annotations.SerializedName

data class PaymentItemResponse(
    @SerializedName("token")
    var token: String?,
    @SerializedName("url")
    var url: String?,
)
