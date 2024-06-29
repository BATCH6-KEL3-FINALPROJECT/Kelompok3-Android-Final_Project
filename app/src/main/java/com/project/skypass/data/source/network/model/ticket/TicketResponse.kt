package com.project.skypass.data.source.network.model.ticket

data class TicketResponse(
    val code: Int?,
    val `data`: Data?,
    val is_success: Boolean?,
    val message: String?,
)
