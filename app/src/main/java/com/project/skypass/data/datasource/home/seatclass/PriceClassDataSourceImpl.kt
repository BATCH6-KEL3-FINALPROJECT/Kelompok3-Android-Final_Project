package com.project.skypass.data.datasource.home.seatclass

import com.project.skypass.data.model.SeatClass

class PriceClassDataSourceImpl : PriceClassDataSource {
    override fun getPriceClassData(): List<SeatClass> {
        return listOf(
            SeatClass(
                classType = "economy",
                price = null,
            ),
            SeatClass(
                classType = "premium economy",
                price = null,
            ),
            SeatClass(
                classType = "business",
                price = null,
            ),
            SeatClass(
                classType = "first class",
                price = null,
            ),
        )
    }
}
