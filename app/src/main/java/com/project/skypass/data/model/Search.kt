package com.project.skypass.data.model

import java.util.UUID

data class Search(
    var id: String = UUID.randomUUID().toString(),
    val airportId: String,
    val airportName: String,
    val city: String,
    val cityCode: String,
    val continent: String,
    val iataCode: String,
    val country: String
)