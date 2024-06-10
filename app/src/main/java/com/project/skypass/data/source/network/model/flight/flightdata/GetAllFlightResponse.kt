package com.project.skypass.data.source.network.model.flight.flightdata

data class GetAllFlightResponse(
    val code: Int?,
    val `data`: Data?,
    val is_success: Boolean?,
    val message: String?
)