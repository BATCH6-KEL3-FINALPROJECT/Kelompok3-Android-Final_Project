package com.project.skypass.data.repository.search

import com.google.android.gms.common.api.BooleanResult
import com.project.skypass.data.model.Booking
import com.project.skypass.data.model.Search
import com.project.skypass.data.model.SearchHistoryHome
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchResults(query: String?): Flow<ResultWrapper<List<Search>>>
    fun getAllHistorySearchHome(token: String): Flow<ResultWrapper<List<SearchHistoryHome>>>
    fun createHistorySearchHome(token: String, history: String): Flow<ResultWrapper<Boolean>>
    fun deleteHistorySearchHome(token: String, id: Int): Flow<ResultWrapper<Boolean>>

}