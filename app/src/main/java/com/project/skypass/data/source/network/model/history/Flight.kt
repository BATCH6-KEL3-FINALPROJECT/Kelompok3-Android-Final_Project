package com.project.skypass.data.source.network.model.history


import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("arrival_airport")
    var arrivalAirport: String?,
    @SerializedName("arrival_airport_id")
    var arrivalAirportId: String?,
    @SerializedName("arrival_date")
    var arrivalDate: String?,
    @SerializedName("arrival_time")
    var arrivalTime: String?,
    @SerializedName("arrivingAirport")
    var arrivingAirport: ArrivingAirport?,
    @SerializedName("departingAirport")
    var departingAirport: DepartingAirport?,
    @SerializedName("departure_airport")
    var departureAirport: String?,
    @SerializedName("departure_airport_id")
    var departureAirportId: String?,
    @SerializedName("departure_date")
    var departureDate: String?,
    @SerializedName("departure_time")
    var departureTime: String?,
    @SerializedName("flight_duration")
    var flightDuration: Int?,
    @SerializedName("flight_id")
    var flightId: String?,
    @SerializedName("flight_status")
    var flightStatus: String?,
    @SerializedName("terminal")
    var terminal: Boolean?
)