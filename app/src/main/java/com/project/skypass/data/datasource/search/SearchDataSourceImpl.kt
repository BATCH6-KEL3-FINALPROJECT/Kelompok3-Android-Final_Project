package com.project.skypass.data.datasource.search

import com.project.skypass.data.source.network.model.search.SearchResponse
import com.project.skypass.data.source.network.service.ApiService

class SearchDataSourceImpl(private val service: ApiService): SearchDataSource {
    override suspend fun getSearchResults(query: String?): SearchResponse {
        return service.searchDestination(query)
    }
}