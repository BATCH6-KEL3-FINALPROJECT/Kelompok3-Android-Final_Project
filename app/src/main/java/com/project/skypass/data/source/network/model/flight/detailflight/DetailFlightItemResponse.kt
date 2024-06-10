package com.project.skypass.data.source.network.model.flight.detailflight

data class DetailFlightItemResponse(
    val Airline: Airline?,
    val airline_id: String?,
    val arrival_airport: String?,
    val arrival_airport_id: String?,
    val arrival_date: String?,
    val arrival_time: String?,
    val arrivingAirport: ArrivingAirport?,
    val createdAt: String?,
    val departingAirport: DepartingAirport?,
    val departure_airport: String?,
    val departure_airport_id: String?,
    val departure_date: String?,
    val departure_time: String?,
    val flight_code: String?,
    val flight_description: String?,
    val flight_duration: Int?,
    val flight_id: String?,
    val flight_status: String?,
    val is_promo: Boolean?,
    val plane_type: String?,
    val seats_available: Int?,
    val terminal: Boolean?,
    val updatedAt: String?
)