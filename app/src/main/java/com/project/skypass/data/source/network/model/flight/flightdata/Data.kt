package com.project.skypass.data.source.network.model.flight.flightdata

data class Data(
    val flights: List<GetAllFlightItemResponse>?,
    val pagination: Pagination?
)