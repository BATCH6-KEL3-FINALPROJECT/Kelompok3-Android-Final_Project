package com.project.skypass.data.repository.OrderHistory

import com.project.skypass.data.model.OrderUser
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface OrderHistoryRepository {
    fun getUserOrderHistoryData(): Flow<ResultWrapper<Pair<List<OrderUser>, Double>>>
    fun createOrderHistoryDb(item: OrderUser): Flow<ResultWrapper<Boolean>>
    fun deleteOrderHistory(item: OrderUser): Flow<ResultWrapper<Boolean>>

    fun deleteAllOrderHistory(): Flow<ResultWrapper<Boolean>>
}

