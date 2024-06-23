package com.project.skypass.data.model

import java.util.UUID

data class SearchHistoryHome(
    var id: String = UUID.randomUUID().toString(),
    val idHistory: Int,
    val userId: String,
    val history: String,
    val createdAt: String,
    val updatedAt: String
)
