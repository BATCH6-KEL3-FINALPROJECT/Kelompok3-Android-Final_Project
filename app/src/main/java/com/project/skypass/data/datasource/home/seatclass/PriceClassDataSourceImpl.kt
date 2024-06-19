package com.project.skypass.data.datasource.home.seatclass

import com.project.skypass.data.model.SeatClass

class PriceClassDataSourceImpl: PriceClassDataSource {
    override fun getPriceClassData(): List<SeatClass> {
        return listOf(
            SeatClass(
                classType = "economy",
                price = 4950000
            ),
            SeatClass(
                classType = "premium economy",
                price = 7550000
            ),
            SeatClass(
                classType = "business",
                price = 29220000
            ),
            SeatClass(
                classType = "first class",
                price = 87620000
            )
        )
    }
}