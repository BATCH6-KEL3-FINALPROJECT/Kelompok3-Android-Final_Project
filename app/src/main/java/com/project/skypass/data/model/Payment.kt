package com.project.skypass.data.model

import java.util.UUID

data class Payment(
    var id: String = UUID.randomUUID().toString(),
    val tokenMidtrans: String,
    val urlMidtrans: String,
)
