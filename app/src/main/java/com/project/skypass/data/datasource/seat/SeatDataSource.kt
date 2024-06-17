package com.project.skypass.data.datasource.seat

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.skypass.data.mapper.toSeatList
import com.project.skypass.data.model.Seat
import com.project.skypass.data.source.network.service.ApiService

class SeatDataSource(
    private val service: ApiService,
    private val flightId: String,
    private val seatClass: String?
): PagingSource<Int, Seat>() {
    override fun getRefreshKey(state: PagingState<Int, Seat>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Seat> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val response = service.getSeatData(
                flightId = flightId,
                seatClass = seatClass,
                page = page
            )

            LoadResult.Page(
                data = response.data?.seats.toSeatList() ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data?.seats?.isEmpty() == true) null else page + 1,
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}