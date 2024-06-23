package com.project.skypass.data.source.network.model.booking


import com.google.gson.annotations.SerializedName

data class FlightData(
    @SerializedName("Airline")
    var airline: Airline?,
    @SerializedName("airline_id")
    var airlineId: String?,
    @SerializedName("arrival_airport")
    var arrivalAirport: String?,
    @SerializedName("arrival_airport_id")
    var arrivalAirportId: String?,
    @SerializedName("arrival_date")
    var arrivalDate: String?,
    @SerializedName("arrival_time")
    var arrivalTime: String?,
    @SerializedName("createdAt")
    var createdAt: String?,
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
    @SerializedName("is_promo")
    var isPromo: Boolean?,
    @SerializedName("plane_type")
    var planeType: String?,
    @SerializedName("seats_available")
    var seatsAvailable: Int?,
    @SerializedName("terminal")
    var terminal: Boolean?,
    @SerializedName("updatedAt")
    var updatedAt: String?
)