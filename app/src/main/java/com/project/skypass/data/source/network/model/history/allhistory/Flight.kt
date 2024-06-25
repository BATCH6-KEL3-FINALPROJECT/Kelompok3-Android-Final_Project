package com.project.skypass.data.source.network.model.history.allhistory


import com.google.gson.annotations.SerializedName
import com.project.skypass.data.source.network.model.history.detailhistory.Airline
import com.project.skypass.data.source.network.model.history.detailhistory.FlightDescription

data class Flight(

    @SerializedName("Airline")
    var airline: Airline?,
    @SerializedName("arrival_airport")
    var arrivalAirport: String?,
    @SerializedName("arrival_airport_id")
    var arrivalAirportId: String?,
    @SerializedName("arrival_date")
    var arrivalDate: String?,
    @SerializedName("arrival_time")
    var arrivalTime: String?,
    @SerializedName("arrivingAirport")
    var arrivingAirport: com.project.skypass.data.source.network.model.history.detailhistory.ArrivingAirport?,
    @SerializedName("departingAirport")
    var departingAirport: com.project.skypass.data.source.network.model.history.detailhistory.DepartingAirport?,
    @SerializedName("departure_airport")
    var departureAirport: String?,
    @SerializedName("departure_airport_id")
    var departureAirportId: String?,
    @SerializedName("departure_date")
    var departureDate: String?,
    @SerializedName("departure_time")
    var departureTime: String?,
    @SerializedName("flight_code")
    var flightCode: String?,
    @SerializedName("flight_description")
    var flightDescription: FlightDescription?,
    @SerializedName("flight_duration")
    var flightDuration: Int?,
    @SerializedName("flight_id")
    var flightId: String?,
    @SerializedName("flight_status")
    var flightStatus: String?,
    @SerializedName("terminal")
    var terminal: Boolean?
)