package com.project.skypass.data.mapper

import com.project.skypass.data.model.DetailFlight
import com.project.skypass.data.model.Flight
import com.project.skypass.data.source.network.model.flight.detailflight.DetailFlightItemResponse
import com.project.skypass.data.source.network.model.flight.flightdata.GetAllFlightItemResponse

fun GetAllFlightItemResponse?.toFlight() =
    Flight(
        flightId = this?.flightId,
        airlineCode = this?.airlineCode,
        airlineName = this?.airlineName,
        arrivalIATACode = this?.arrivalIataCode,
        arrivalAirportName = this?.arrivalAirportName,
        arrivalTime = this?.arrivalTime,
        departureIATACode = this?.departureIataCode,
        departureAirportName = this?.departureAirportName,
        departureTime = this?.departureTime,
        arrivalCity = this?.arrivalCity,
        departureCity = this?.departureCity,
        arrivalDate = this?.arrivalDate,
        departureDate = this?.departureDate,
        terminal = this?.terminal.toString(),
        flightCode = this?.flightCode,
        flightStatus = this?.flightStatus,
        flightDuration = this?.flightDuration,
        flightDescription = this?.flightDescription?.details?.joinToString { it.description ?: "" },
        planeType = this?.planeType,
        price = this?.price,
        priceForInfant = this?.priceForInfant,
        priceForChild = this?.priceForChild,
        seatClass = this?.seatClass,
        seatsAvailable = this?.seatsAvailable,
    )

fun DetailFlightItemResponse?.toDetailFlight() =
    DetailFlight(
        flightId = this?.flight_id,
        airlineId = this?.airline_id,
        airlineCode = this?.Airline?.airline_code,
        airlineName = this?.Airline?.airline_name,
        arrivalTime = this?.arrival_time,
        arrivalDate = this?.arrival_date,
        departureTime = this?.departure_time,
        departureDate = this?.departure_date,
        terminal = this?.terminal,
        flightCode = this?.flight_code,
        flightStatus = this?.flight_status,
        flightDuration = this?.flight_duration,
        flightDescription = this?.flight_description,
        planeType = this?.plane_type,
        arrivalAirportName = this?.arrival_airport,
        departureAirportName = this?.departure_airport,
        departureCity = this?.departingAirport?.city,
        arrivalCity = this?.arrivingAirport?.city,
        departureContinent = this?.departingAirport?.continent,
        arrivalContinent = this?.arrivingAirport?.continent,
        createdAt = this?.createdAt,
        updatedAt = this?.updatedAt,
        arrivalAirportId = this?.arrival_airport_id,
        departureAirportId = this?.departure_airport_id,
        isPromo = this?.is_promo,
        seatAvailable = this?.seats_available,
    )

fun Collection<GetAllFlightItemResponse>?.toFlightData() = this?.map { it.toFlight() } ?: listOf()

fun Collection<DetailFlightItemResponse>?.toDetailFlightData() = this?.map { it.toDetailFlight() } ?: listOf()
