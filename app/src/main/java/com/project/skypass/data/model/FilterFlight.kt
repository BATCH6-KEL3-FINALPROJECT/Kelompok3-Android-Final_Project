package com.project.skypass.data.model

import java.util.UUID

data class FilterFlight(
    var id: String = UUID.randomUUID().toString(),
    val criteria: String,
)
