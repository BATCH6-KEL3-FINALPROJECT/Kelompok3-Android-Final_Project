package com.project.skypass.data.source.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OrderHistory")
class OrderHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "order_id")
    var orderId: Int?,
    @ColumnInfo(name = "from")
    var from: String?,
    @ColumnInfo(name = "to")
    var to: String?,
    @ColumnInfo(name = "departure_date")
    var departureDate: String?,
    @ColumnInfo(name = "arrival_date")
    var arrivalDate: String?,
    @ColumnInfo(name = "passengers")
    var passengers: String?,
    @ColumnInfo(name = "seat_class")
    var seatClass: String?,
    @ColumnInfo(name = "order_date")
    var orderDate: String?,
)
