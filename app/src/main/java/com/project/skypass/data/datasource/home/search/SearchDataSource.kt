package com.project.skypass.data.datasource.home.search

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.search.SearchResponse
import com.project.skypass.data.source.network.model.search.deletehistory.DeleteHistorySearchResponse
import com.project.skypass.data.source.network.model.search.gethistory.GetHistoryItemResponse
import com.project.skypass.data.source.network.model.search.gethistory.GetHistoryResponse
import com.project.skypass.data.source.network.model.search.posthistory.PostHistoryRespomse
import com.project.skypass.data.source.network.model.search.posthistory.request.HistoryRequestResponse

interface SearchDataSource {
    suspend fun getSearchResults(query: String?): SearchResponse
    suspend fun getAllHistorySearch(token: String): GetHistoryResponse
    suspend fun createHistorySearch(token: String, payload: HistoryRequestResponse): PostHistoryRespomse
    suspend fun deleteHistorySearch(token: String, id: Int): DeleteHistorySearchResponse
}