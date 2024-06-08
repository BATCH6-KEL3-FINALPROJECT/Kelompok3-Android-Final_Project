package com.project.skypass.data.datasource.home

import com.project.skypass.data.model.SeatClass

interface PriceClassDataSource {
    fun getPriceClassData(): List<SeatClass>
}