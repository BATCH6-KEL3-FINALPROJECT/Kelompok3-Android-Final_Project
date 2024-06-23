package com.project.skypass.data.source.network.model.checkout.request


import com.google.gson.annotations.SerializedName

data class BuyerData(
    @SerializedName("email")
    var email: String?,
    @SerializedName("familyName")
    var familyName: String?,
    @SerializedName("fullName")
    var fullName: String?,
    @SerializedName("phone")
    var phone: String?
)