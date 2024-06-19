package com.project.skypass.presentation.checkout.checkoutSeat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import androidx.paging.map
import com.project.skypass.data.model.Seat
import com.project.skypass.data.repository.seat.SeatRepository
import com.project.skypass.data.repository.seat.SeatsRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.collections.joinToString

class CheckoutSeatViewModel(
    private val seatRepository: SeatRepository,
    private val seatsRepository: SeatsRepository
): ViewModel() {

    fun getSeats(seatsClass: String, flightId: String, page: Int): LiveData<ResultWrapper<Triple<String,List<String>, List<String>>>> {
        return seatsRepository.getSeats(seatsClass, flightId, page).asLiveData(Dispatchers.IO)
    }

}