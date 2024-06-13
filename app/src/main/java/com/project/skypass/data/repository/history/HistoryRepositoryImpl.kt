package com.project.skypass.data.repository.history

import com.project.skypass.data.datasource.history.HistoryDataSource
import com.project.skypass.data.model.History

class HistoryRepositoryImpl(private val historyDataSource: HistoryDataSource): HistoryRepository {
    override fun getHistoryRepository(): List<History> {
        return historyDataSource.getHistory()
    }
}