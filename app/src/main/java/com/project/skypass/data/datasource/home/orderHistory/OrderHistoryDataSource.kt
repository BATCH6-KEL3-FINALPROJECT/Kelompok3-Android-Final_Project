package com.project.skypass.data.datasource.home.orderHistory

import com.project.skypass.data.source.local.database.entity.OrderHistoryEntity
import kotlinx.coroutines.flow.Flow

interface OrderHistoryDataSource {
    fun getAllOrderHistory(): Flow<List<OrderHistoryEntity>>
    suspend fun insertOrderHistory(item: OrderHistoryEntity): Long
    suspend fun deleteOrderHistory(item: OrderHistoryEntity): Int
    fun deleteAllOrderHistory()
}