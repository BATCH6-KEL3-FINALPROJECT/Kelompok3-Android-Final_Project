package com.project.skypass.data.datasource.history

import com.project.skypass.data.model.History
import com.project.skypass.data.source.network.model.history.allhistory.AllHistoryResponse
import com.project.skypass.data.source.network.model.history.detailhistory.DetailHistoryResponse

interface HistoryDataSource {
    suspend fun getHistory(token: String): AllHistoryResponse
    suspend fun getDetailHistory(token: String, id: String): DetailHistoryResponse
}