package com.project.skypass.data.source.network.model.flight.flightdata

import com.google.gson.annotations.SerializedName

data class GetAllFlightItemResponse(
    @SerializedName("airline_code")
    var airlineCode: String?,
    @SerializedName("airline_name")
    var airlineName: String?,
    @SerializedName("arrival_airport_name")
    var arrivalAirportName: String?,
    @SerializedName("arrival_city")
    var arrivalCity: String?,
    @SerializedName("arrival_date")
    var arrivalDate: String?,
    @SerializedName("arrival_iata_code")
    var arrivalIataCode: String?,
    @SerializedName("arrival_time")
    var arrivalTime: String?,
    @SerializedName("departure_airport_name")
    var departureAirportName: String?,
    @SerializedName("departure_city")
    var departureCity: String?,
    @SerializedName("departure_date")
    var departureDate: String?,
    @SerializedName("departure_iata_code")
    var departureIataCode: String?,
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
    @SerializedName("plane_type")
    var planeType: String?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("price_for_child")
    var priceForChild: Int?,
    @SerializedName("price_for_infant")
    var priceForInfant: Int?,
    @SerializedName("seat_class")
    var seatClass: String?,
    @SerializedName("seats_available")
    var seatsAvailable: Int?,
    @SerializedName("terminal")
    var terminal: Boolean?,
)
