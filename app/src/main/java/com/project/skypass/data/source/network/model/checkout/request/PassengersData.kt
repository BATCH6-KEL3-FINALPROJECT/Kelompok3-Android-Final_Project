package com.project.skypass.data.source.network.model.checkout.request


import com.google.gson.annotations.SerializedName

data class PassengersData(
    @SerializedName("date_of_birth")
    var dateOfBirth: String?,
    @SerializedName("departureSeats")
    var departureSeats: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("first_name")
    var firstName: String?,
    @SerializedName("issuing_country")
    var issuingCountry: String?,
    @SerializedName("last_name")
    var lastName: String?,
    @SerializedName("nationality")
    var nationality: String?,
    @SerializedName("passenger_type")
    var passengerType: String?,
    @SerializedName("passport_no")
    var passportNo: String?,
    @SerializedName("phone_number")
    var phoneNumber: String?,
    @SerializedName("returnSeats")
    var returnSeats: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("valid_until")
    var validUntil: String?
)