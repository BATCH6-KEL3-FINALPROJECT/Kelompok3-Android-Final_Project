package com.project.skypass.data.repository.search

import com.project.skypass.data.datasource.search.SearchDataSource
import com.project.skypass.data.mapper.toSearchDestination
import com.project.skypass.data.model.Search
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val dataSource: SearchDataSource): SearchRepository {
    override fun getSearchResults(query: String?): Flow<ResultWrapper<List<Search>>> {
        return proceedFlow {
            dataSource.getSearchResults(query).data.toSearchDestination()
        }
    }
}