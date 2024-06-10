package com.project.skypass.data.source.network.model.search

data class SearchItemResponse(
    val airport_id: String?,
    val airport_name: String?,
    val city: String?,
    val city_code: String?,
    val continent: String?,
    val country: String?,
    val iata_code: String?
)