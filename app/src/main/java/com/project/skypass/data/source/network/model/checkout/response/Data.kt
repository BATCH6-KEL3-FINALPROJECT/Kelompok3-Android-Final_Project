package com.project.skypass.data.source.network.model.checkout.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("paymentResult")
    var paymentResult: PaymentResult?,
    @SerializedName("token")
    var token: String?,
    @SerializedName("url")
    var url: String?
)