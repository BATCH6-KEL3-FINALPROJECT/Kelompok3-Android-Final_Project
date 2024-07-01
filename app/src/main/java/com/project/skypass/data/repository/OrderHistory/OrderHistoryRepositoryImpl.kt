package com.project.skypass.data.repository.OrderHistory

import com.project.skypass.data.datasource.home.orderHistory.OrderHistoryDataSource
import com.project.skypass.data.mapper.toOrderHistoryEntity
import com.project.skypass.data.mapper.toOrderHistoryList
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.source.local.database.entity.OrderHistoryEntity
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceed
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.lang.IllegalStateException

class OrderHistoryRepositoryImpl(private val orderHistoryDataSource: OrderHistoryDataSource) : OrderHistoryRepository {
    override fun getUserOrderHistoryData(): Flow<ResultWrapper<Pair<List<OrderUser>, Double>>> {
        return orderHistoryDataSource.getAllOrderHistory()
            .map {
                // mapping into cart list and sum the total price
                proceed {
                    val result = it.toOrderHistoryList()
                    val totalPassenger = 2.5
                    Pair(result, totalPassenger)
                }
            }.map {
                // map to check when list is empty
                if (it.payload?.first?.isEmpty() == false) return@map it
                ResultWrapper.Empty(it.payload)
            }.onStart {
                emit(ResultWrapper.Loading())
                delay(2000)
            }
    }

    override fun createOrderHistoryDb(item: OrderUser): Flow<ResultWrapper<Boolean>> {
        return item.id?.let { itemId ->
            // when id is not null
            proceedFlow {
                val affectedRow =
                    orderHistoryDataSource.insertOrderHistory(
                        OrderHistoryEntity(
                            from = item.departureCity.toString(),
                            to = item.arrivalCity.toString(),
                            departureDate = item.departureDate.toString(),
                            arrivalDate = item.arrivalDate.toString(),
                            passengers = item.passengersTotal.toString(),
                            seatClass = item.seatClass.toString(),
                            orderDate = item.orderDate.toString(),
                            orderId = item.id,
                        ),
                    )
                delay(2000)
                affectedRow > 0
            }
        } ?: flow {
            // when id is doesnt exist
            emit(ResultWrapper.Error(IllegalStateException("catalog ID not found")))
        }
    }

    override fun deleteOrderHistory(item: OrderUser): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { orderHistoryDataSource.deleteOrderHistory(item.toOrderHistoryEntity()) > 0 }
    }

    override fun deleteAllOrderHistory(): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            orderHistoryDataSource.deleteAllOrderHistory()
            true
        }
    }
}
