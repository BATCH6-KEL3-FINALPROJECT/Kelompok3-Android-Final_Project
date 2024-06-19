package com.project.skypass.data.repository.seat

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.skypass.data.datasource.seat.SeatDataSource
import com.project.skypass.data.model.Seat
import com.project.skypass.data.source.network.service.ApiService
import kotlinx.coroutines.flow.Flow


class SeatRepositoryImpl(private val service: ApiService): SeatRepository {

    override fun getSeat(flightId: String, seatClass: String?): Flow<PagingData<Seat>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = { SeatDataSource(service, flightId, seatClass) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}