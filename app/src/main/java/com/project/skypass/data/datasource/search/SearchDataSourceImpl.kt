package com.project.skypass.data.datasource.search

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.search.SearchItemResponse
import com.project.skypass.data.source.network.service.ApiService

class SearchDataSourceImpl(private val service: ApiService): SearchDataSource {
    override suspend fun getSearchResults(query: String?): Response<List<SearchItemResponse>?> {
        return service.searchDestination(query)
    }
}