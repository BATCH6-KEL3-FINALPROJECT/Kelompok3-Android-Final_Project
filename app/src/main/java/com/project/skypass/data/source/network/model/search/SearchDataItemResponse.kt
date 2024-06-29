package com.project.skypass.data.source.network.model.search

data class SearchDataItemResponse(
    val airport: List<SearchItemResponse>?,
    val pagination: Pagination?,
)
