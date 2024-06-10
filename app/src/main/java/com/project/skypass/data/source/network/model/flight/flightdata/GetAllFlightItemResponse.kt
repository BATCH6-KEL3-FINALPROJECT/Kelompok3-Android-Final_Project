package com.project.skypass.data.source.network.model.flight.flightdata

data class GetAllFlightItemResponse(
    val airline_code: String?,
    val airline_name: String?,
    val arrival_airport_name: String?,
    val arrival_city: String?,
    val arrival_date: String?,
    val arrival_iata_code: String?,
    val arrival_time: String?,
    val departure_airport_name: String?,
    val departure_city: String?,
    val departure_date: String?,
    val departure_iata_code: String?,
    val departure_time: String?,
    val flight_code: String?,
    val flight_description: String?,
    val flight_duration: Int?,
    val flight_id: String?,
    val flight_status: String?,
    val plane_type: String?,
    val price: Int?,
    val price_for_child: Int?,
    val price_for_infant: Int?,
    val seat_class: String?,
    val seats_available: Int?,
    val terminal: Boolean?
)