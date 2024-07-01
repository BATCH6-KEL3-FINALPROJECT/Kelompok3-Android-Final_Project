package com.project.skypass.data.repository.history

import com.project.skypass.data.model.History
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getHistory(token: String): Flow<ResultWrapper<List<History>>>

    fun getBookingHistory(
        token: String,
        search: String?,
        date: String?,
        until: String?,
    ): Flow<ResultWrapper<List<History>>>

    fun getDetailHistory(
        token: String,
        id: String,
    ): Flow<ResultWrapper<History>>
}
