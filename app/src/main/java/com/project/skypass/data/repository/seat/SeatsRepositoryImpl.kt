package com.project.skypass.data.repository.seat

import com.project.skypass.data.datasource.seat.SeatsDataSource
import com.project.skypass.data.mapper.toSeatList
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class SeatsRepositoryImpl(private val dataSource: SeatsDataSource) : SeatsRepository {
    override fun getSeats(
        seatsClass: String,
        flightId: String,
        page: Int,
    ): Flow<ResultWrapper<Triple<String, List<String>, List<String>>>> {
        return proceedFlow {
            val dataSeats = dataSource.getSeats(seatsClass, flightId, page).data?.seats.toSeatList()

            val chunkedData =
                dataSeats.chunked(6).map { chunk ->

                    val seatStatusStr =
                        chunk.map { it.isAvailable }
                            .chunked(3)
                            .map { it.joinToString("") }
                            .joinToString("_")

                    val getSeatId = chunk.map { it.seatId }

                    val centerIndex = if (chunk.size > 3) 3 else chunk.size

                    val seatLabel =
                        chunk.map { it.seatNumber}
                            .toMutableList()
                            .apply {
                                add(centerIndex, "")
                                add(0, "/")
                            }
                    Triple(seatStatusStr, getSeatId, seatLabel)
                }
            val status = chunkedData.map { it.first }.joinToString("/", "/")

            val seatId = chunkedData.map { it.second }.flatten()

            val title = chunkedData.map { it.third }.flatten()

            println(status)
            println(seatId)
            println(title)

            Triple(status, seatId, title)
        }
    }
}
