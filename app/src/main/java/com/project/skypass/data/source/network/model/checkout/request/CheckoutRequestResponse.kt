package com.project.skypass.data.source.network.model.checkout.request


import com.google.gson.annotations.SerializedName

data class CheckoutRequestResponse(
    @SerializedName("buyerData")
    var buyerData: BuyerData?,
    @SerializedName("departureFlightId")
    var departureFlightId: String?,
    @SerializedName("noOfPassenger")
    var noOfPassenger: Int?,
    @SerializedName("passengersData")
    var passengersData: List<PassengersData>?,
    @SerializedName("returnFlightId")
    var returnFlightId: String?,
    @SerializedName("totalAmount")
    var totalAmount: Int?
)