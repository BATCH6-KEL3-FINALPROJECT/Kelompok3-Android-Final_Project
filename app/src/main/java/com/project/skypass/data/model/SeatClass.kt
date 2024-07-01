package com.project.skypass.data.model

import java.util.UUID

data class SeatClass(
    var id: String = UUID.randomUUID().toString(),
    val classType: String,
    val price: Int?,
)
