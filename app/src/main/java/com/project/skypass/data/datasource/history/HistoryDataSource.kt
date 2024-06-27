package com.project.skypass.data.datasource.history

import com.project.skypass.data.source.network.model.history.allhistory.AllHistoryResponse
import com.project.skypass.data.source.network.model.history.detailhistory.DetailHistoryResponse
import com.project.skypass.data.source.network.model.history.userhistory.UserHistoryResponse

interface HistoryDataSource {
    suspend fun getHistory(token: String): AllHistoryResponse
    suspend fun getBookingHistory(token: String, search: String): UserHistoryResponse
    suspend fun getDetailHistory(token: String, id: String): DetailHistoryResponse
}