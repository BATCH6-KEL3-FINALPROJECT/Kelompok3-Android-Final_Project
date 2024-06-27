package com.project.skypass.data.datasource.history

import com.project.skypass.data.source.network.model.history.allhistory.AllHistoryResponse
import com.project.skypass.data.source.network.model.history.detailhistory.DetailHistoryResponse
import com.project.skypass.data.source.network.model.history.userhistory.UserHistoryResponse
import com.project.skypass.data.source.network.service.ApiService

class HistoryDataSourceImpl(private val service: ApiService) : HistoryDataSource {
    override suspend fun getHistory(token: String): AllHistoryResponse {
        return service.getAllHistory(token)
    }

    override suspend fun getBookingHistory(
        token: String,
        search: String?,
        date: String?,
        until: String?
    ): UserHistoryResponse {
        return service.getBookingHistory(token, search, date, until)
    }

    override suspend fun getDetailHistory(token: String, id: String): DetailHistoryResponse {
        return service.getDetailHistory(token, id)
    }
}
