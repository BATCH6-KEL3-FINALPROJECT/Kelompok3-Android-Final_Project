package com.project.skypass.data.source.network.model.search

data class Pagination(
    val limitData: Int?,
    val pageNum: Int?,
    val totalData: Int?,
    val totalPages: Int?
)