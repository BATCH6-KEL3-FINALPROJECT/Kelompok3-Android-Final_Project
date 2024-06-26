package com.project.skypass.data.repository.history

import com.project.skypass.data.model.History
import com.project.skypass.data.model.Search
import com.project.skypass.data.source.network.model.history.detailhistory.DetailHistoryResponse
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getHistory(token: String): Flow<ResultWrapper<List<History>>>
    fun getBookingHistory(token: String, id: String, search: String): Flow<ResultWrapper<List<History>>>
    fun getDetailHistory(token: String, id: String): Flow<ResultWrapper<History>>
}