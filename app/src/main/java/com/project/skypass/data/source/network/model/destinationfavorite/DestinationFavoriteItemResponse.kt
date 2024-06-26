package com.project.skypass.data.source.network.model.destinationfavorite


import com.google.gson.annotations.SerializedName

data class DestinationFavoriteItemResponse(
    @SerializedName("airline")
    var airline: String?,
    @SerializedName("continent")
    var continent: String?,
    @SerializedName("departureDate")
    var departureDate: String?,
    @SerializedName("discount")
    var discount: String?,
    @SerializedName("from")
    var from: String?,
    @SerializedName("imageUrl")
    var imageUrl: String?,
    @SerializedName("isRoundTrip")
    var isRoundTrip: Boolean?,
    @SerializedName("passengersAdult")
    var passengersAdult: Int?,
    @SerializedName("passengersBaby")
    var passengersBaby: Int?,
    @SerializedName("passengersChild")
    var passengersChild: Int?,
    @SerializedName("passengersTotal")
    var passengersTotal: Int?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("returnDate")
    var returnDate: String?,
    @SerializedName("seatClass")
    var seatClass: String?,
    @SerializedName("to")
    var to: String?
)