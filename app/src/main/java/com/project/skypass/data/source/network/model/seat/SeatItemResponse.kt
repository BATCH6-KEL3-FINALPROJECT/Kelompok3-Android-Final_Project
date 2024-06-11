package com.project.skypass.data.source.network.model.seat

data class SeatItemResponse(
    val column: String?,
    val flight_id: String?,
    val is_available: String?,
    val row: Int?,
    val seat_class: String?,
    val seat_id: String?,
    val seat_number: String?
)