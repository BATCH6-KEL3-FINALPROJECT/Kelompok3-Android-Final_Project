package com.project.skypass.data.datasource.home.orderHistory

import com.project.skypass.data.source.local.database.dao.OrderHistoryDao
import com.project.skypass.data.source.local.database.entity.OrderHistoryEntity
import kotlinx.coroutines.flow.Flow

class OrderHistoryDataSourceImpl(private val dao: OrderHistoryDao) : OrderHistoryDataSource {
    override fun getAllOrderHistory(): Flow<List<OrderHistoryEntity>> = dao.getAllOrderHistory()

    override suspend fun insertOrderHistory(item: OrderHistoryEntity): Long =
        dao.insertOrderHistory(item)

    override suspend fun deleteOrderHistory(item: OrderHistoryEntity): Int =
        dao.deleteOrderHistory(item)

    override fun deleteAllOrderHistory() {
        dao.deleteAllOrderHistory()
    }

}