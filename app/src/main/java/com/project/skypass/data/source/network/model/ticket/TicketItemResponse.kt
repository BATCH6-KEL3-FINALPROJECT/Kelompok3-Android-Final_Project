package com.project.skypass.data.source.network.model.ticket

data class TicketItemResponse(
    val TERMINAL: String?,
    val booking_id: String?,
    val createdAt: String?,
    val flight_id: String?,
    val passenger_id: String?,
    val passenger_name: String?,
    val seat_id: String?,
    val seat_number: String?,
    val ticket_code: String?,
    val ticket_id: String?,
    val ticket_status: String?,
    val updatedAt: String?
)