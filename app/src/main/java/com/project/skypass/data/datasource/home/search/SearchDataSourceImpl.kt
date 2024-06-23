package com.project.skypass.data.datasource.home.search

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.search.SearchResponse
import com.project.skypass.data.source.network.model.search.deletehistory.DeleteHistorySearchResponse
import com.project.skypass.data.source.network.model.search.gethistory.GetHistoryItemResponse
import com.project.skypass.data.source.network.model.search.posthistory.PostHistoryRespomse
import com.project.skypass.data.source.network.model.search.posthistory.request.HistoryRequestResponse
import com.project.skypass.data.source.network.service.ApiService

class SearchDataSourceImpl(private val service: ApiService): SearchDataSource {
    override suspend fun getSearchResults(query: String?): SearchResponse {
        return service.searchDestination(query)
    }

    override suspend fun getAllHistorySearch(token: String): Response<List<GetHistoryItemResponse>?> {
        return service.getAllHistorySearchHome(token)
    }

    override suspend fun createHistorySearch(
        token: String,
        payload: HistoryRequestResponse
    ): PostHistoryRespomse {
        return service.createHistorySearchHome(token, payload)
    }

    override suspend fun deleteHistorySearch(
        token: String,
        id: String
    ): DeleteHistorySearchResponse {
        return service.deleteHistorySearchHome(token, id)
    }
}