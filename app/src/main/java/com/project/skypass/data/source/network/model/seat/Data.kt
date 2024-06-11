package com.project.skypass.data.source.network.model.seat

data class Data(
    val pagination: Pagination?,
    val seats: List<SeatItemResponse>?
)