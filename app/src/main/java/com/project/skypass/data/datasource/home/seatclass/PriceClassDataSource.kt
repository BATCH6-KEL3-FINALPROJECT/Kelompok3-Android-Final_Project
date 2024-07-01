package com.project.skypass.data.datasource.home.seatclass

import com.project.skypass.data.model.SeatClass

interface PriceClassDataSource {
    fun getPriceClassData(): List<SeatClass>
}
