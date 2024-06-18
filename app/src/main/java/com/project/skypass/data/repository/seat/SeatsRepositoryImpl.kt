package com.project.skypass.data.repository.seat

import com.project.skypass.data.datasource.seat.SeatsDataSource
import com.project.skypass.data.mapper.toSeatList
import com.project.skypass.data.model.Seat
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class SeatsRepositoryImpl(private val dataSource: SeatsDataSource): SeatsRepository {
    override fun getSeats(
        seatsClass: String,
        flightId: String,
        page: Int
    ): Flow<ResultWrapper<List<Seat>>> {
        return proceedFlow {
            dataSource.getSeats(seatsClass, flightId, page).data?.seats.toSeatList()
        }
    }
}