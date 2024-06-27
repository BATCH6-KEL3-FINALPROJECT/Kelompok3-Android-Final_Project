package com.project.skypass.data.repository.history

import com.project.skypass.data.datasource.history.HistoryDataSource
import com.project.skypass.data.mapper.toAllHistory
import com.project.skypass.data.mapper.toBookingHistory
import com.project.skypass.data.mapper.toDetailHistory
import com.project.skypass.data.model.History
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class HistoryRepositoryImpl(private val historyDataSource: HistoryDataSource): HistoryRepository {
    override fun getHistory(token: String): Flow<ResultWrapper<List<History>>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            historyDataSource.getHistory(tokenBearer).data.toAllHistory()
        }
    }

    override fun getBookingHistory(
        token: String,
        search: String?,
        date: String?,
        until: String?
    ): Flow<ResultWrapper<List<History>>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            historyDataSource.getBookingHistory(tokenBearer, search, date, until).data.toBookingHistory()
        }
    }

    override fun getDetailHistory(token: String, id: String): Flow<ResultWrapper<History>> {
        return proceedFlow {
            val tokenBearer = "Bearer $token"
            historyDataSource.getDetailHistory(tokenBearer, id).data.toDetailHistory()
        }
    }

}
