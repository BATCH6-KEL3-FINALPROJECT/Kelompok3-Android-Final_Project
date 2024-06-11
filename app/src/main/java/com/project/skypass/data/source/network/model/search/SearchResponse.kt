package com.project.skypass.data.source.network.model.search

data class SearchResponse(
    val code: Int?,
    val data: SearchDataItemResponse?,
    val is_success: Boolean?,
    val message: String?
)