package com.project.skypass.data.source.network.model.flight.flightdata

data class Pagination(
    val pageNum: Int?,
    val pageSize: Int?,
    val totalData: Int?,
    val totalPages: Int?
)