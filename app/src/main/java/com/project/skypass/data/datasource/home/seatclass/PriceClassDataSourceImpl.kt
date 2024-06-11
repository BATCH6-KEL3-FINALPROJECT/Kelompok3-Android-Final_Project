package com.project.skypass.data.datasource.home.seatclass

import com.project.skypass.data.model.SeatClass

class PriceClassDataSourceImpl: PriceClassDataSource {
    override fun getPriceClassData(): List<SeatClass> {
        return listOf(
            SeatClass(
                classType = "Economy",
                price = 4950000
            ),
            SeatClass(
                classType = "Premium Economy",
                price = 7550000
            ),
            SeatClass(
                classType = "Business",
                price = 29220000
            ),
            SeatClass(
                classType = "First Class",
                price = 87620000
            )
        )
    }
}