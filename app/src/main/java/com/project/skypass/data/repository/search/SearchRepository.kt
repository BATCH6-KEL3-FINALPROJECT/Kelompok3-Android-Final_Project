package com.project.skypass.data.repository.search

import com.project.skypass.data.model.Search
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchResults(query: String?): Flow<ResultWrapper<List<Search>>>
}