package com.project.skypass.data.source.network.model.search

data class SearchResponse(
    val code: Int?,
    val data: Data?,
    val is_success: Boolean?,
    val message: String?
)