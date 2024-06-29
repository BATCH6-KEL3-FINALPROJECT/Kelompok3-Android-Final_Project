package com.project.skypass.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.skypass.data.source.local.database.entity.OrderHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderHistoryDao {
    @Query("SELECT * FROM orderhistory")
    fun getAllOrderHistory(): Flow<List<OrderHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderHistory(item: OrderHistoryEntity): Long

    @Delete
    suspend fun deleteOrderHistory(cart: OrderHistoryEntity): Int

    @Query("DELETE FROM orderhistory")
    fun deleteAllOrderHistory()
}
