package com.project.skypass.data.datasource.history

import com.project.skypass.data.model.Response
import com.project.skypass.data.model.Search
import com.project.skypass.data.source.network.model.history.allhistory.AllHistoryResponse
import com.project.skypass.data.source.network.model.history.detailhistory.DetailHistoryResponse
import com.project.skypass.data.source.network.model.history.userhistory.UserHistoryItemResponse
import com.project.skypass.data.source.network.model.history.userhistory.UserHistoryResponse

interface HistoryDataSource {
    suspend fun getHistory(token: String): AllHistoryResponse
    suspend fun getBookingHistory(token: String, search: String?, date: String?, until: String?): Response<List<UserHistoryItemResponse>?>
    suspend fun getDetailHistory(token: String, id: String): DetailHistoryResponse
}