package com.project.skypass.data.repository.seatclass

import com.project.skypass.data.datasource.home.seatclass.PriceClassDataSource
import com.project.skypass.data.model.SeatClass

class SeatClassRepositoryImpl(private val dataSource: PriceClassDataSource): SeatClassRepository {
    override fun getPriceSeatData(): List<SeatClass> {
        return dataSource.getPriceClassData()
    }

}