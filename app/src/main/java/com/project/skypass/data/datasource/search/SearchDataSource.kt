package com.project.skypass.data.datasource.search

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.search.SearchItemResponse

interface SearchDataSource {
    suspend fun getSearchResults(query: String?): Response<List<SearchItemResponse>?>
}