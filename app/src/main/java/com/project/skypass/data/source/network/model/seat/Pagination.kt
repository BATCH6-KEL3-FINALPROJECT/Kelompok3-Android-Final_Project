package com.project.skypass.data.source.network.model.seat

data class Pagination(
    val limitData: Int?,
    val pageNum: Int?,
    val totalData: Int?,
    val totalPages: Int?
)