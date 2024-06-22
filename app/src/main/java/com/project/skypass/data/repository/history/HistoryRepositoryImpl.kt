package com.project.skypass.data.repository.history

import com.project.skypass.data.datasource.history.HistoryDataSource
import com.project.skypass.data.mapper.toAllHistory
import com.project.skypass.data.mapper.toDetailHistory
import com.project.skypass.data.model.History
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class HistoryRepositoryImpl(private val historyDataSource: HistoryDataSource): HistoryRepository {
    override fun getHistory(token: String): Flow<ResultWrapper<List<History>>> {
        return proceedFlow {
            historyDataSource.getHistory(token).data.toAllHistory()
        }
    }

    override fun getDetailHistory(token: String, id: String): Flow<ResultWrapper<History>> {
        return proceedFlow {
            historyDataSource.getDetailHistory(token, id).data?.booking.toDetailHistory()
        }
    }

}