package com.project.skypass.data.repository.search

import com.project.skypass.data.datasource.home.search.SearchDataSource
import com.project.skypass.data.mapper.toSearchDestination
import com.project.skypass.data.mapper.toSearchHomeListHistory
import com.project.skypass.data.model.Search
import com.project.skypass.data.model.SearchHistoryHome
import com.project.skypass.data.source.network.model.search.posthistory.request.HistoryRequestResponse
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val dataSource: SearchDataSource): SearchRepository {
    override fun getSearchResults(query: String?): Flow<ResultWrapper<List<Search>>> {
        return proceedFlow {
            dataSource.getSearchResults(query).data?.airport.toSearchDestination()
        }
    }

    override fun getAllHistorySearchHome(token: String): Flow<ResultWrapper<List<SearchHistoryHome>>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            dataSource.getAllHistorySearch(tokenBearer).data.toSearchHomeListHistory()
        }
    }

    override fun createHistorySearchHome(
        token: String,
        history: String
    ): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            dataSource.createHistorySearch(
                token = tokenBearer,
                HistoryRequestResponse(
                    history = history
                )
            ).isSuccess ?: false
        }
    }

    override fun deleteHistorySearchHome(token: String, id: String): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            dataSource.deleteHistorySearch(tokenBearer, id).isSuccess ?: false
        }
    }
}