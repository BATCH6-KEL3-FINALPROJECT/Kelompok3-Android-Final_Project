package com.project.skypass.data.source.network.model.search

data class Data(
    val airport: List<SearchItemResponse>?,
    val pagination: Pagination?
)