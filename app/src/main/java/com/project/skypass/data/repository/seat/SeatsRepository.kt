package com.project.skypass.data.repository.seat

import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface SeatsRepository {
    fun getSeats(
        seatsClass: String,
        flightId: String,
        page: Int,
    ): Flow<ResultWrapper<Triple<String, List<String>, List<String>>>>
}
