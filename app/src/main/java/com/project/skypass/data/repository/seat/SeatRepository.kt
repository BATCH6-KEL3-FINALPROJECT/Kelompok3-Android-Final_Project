package com.project.skypass.data.repository.seat

import androidx.paging.PagingData
import com.project.skypass.data.model.Seat
import kotlinx.coroutines.flow.Flow

interface SeatRepository {
    fun getSeat(
        flightId: String,
        seatClass: String?,
    ): Flow<PagingData<Seat>>
}
