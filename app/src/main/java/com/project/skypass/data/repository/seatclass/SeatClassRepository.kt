package com.project.skypass.data.repository.seatclass

import com.project.skypass.data.model.SeatClass

interface SeatClassRepository {
    fun getPriceSeatData(): List<SeatClass>
}
