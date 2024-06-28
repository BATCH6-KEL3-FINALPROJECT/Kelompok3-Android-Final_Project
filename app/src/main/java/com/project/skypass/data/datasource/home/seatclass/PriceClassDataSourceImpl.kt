package com.project.skypass.data.datasource.home.seatclass

import com.project.skypass.data.model.SeatClass

class PriceClassDataSourceImpl: PriceClassDataSource {
    override fun getPriceClassData(): List<SeatClass> {
        return listOf(
            SeatClass(
                classType = "Economy",
                price = null
            ),
            SeatClass(
                classType = "Premium Economy",
                price = null
            ),
            SeatClass(
                classType = "Business",
                price = null
            ),
            SeatClass(
                classType = "First Class",
                price = null
            )
        )
    }
}