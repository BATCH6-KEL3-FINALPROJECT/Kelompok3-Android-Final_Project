package com.project.skypass.presentation.checkout.checkoutSeat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.project.skypass.data.model.Seat
import com.project.skypass.data.repository.seat.SeatRepository
import com.project.skypass.data.repository.seat.SeatsRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class CheckoutSeatViewModel(
    private val seatRepository: SeatRepository,
    private val seatsRepository: SeatsRepository
): ViewModel() {
    fun getSeat(flightId: String, seatClass: String): LiveData<PagingData<Seat>> {
        return seatRepository.getSeat(flightId, seatClass).asLiveData(Dispatchers.IO)
    }

    fun getSeats(seatsClass: String, flightId: String, page: Int): LiveData<ResultWrapper<List<Seat>>> {
        return seatsRepository.getSeats(seatsClass, flightId, page).asLiveData(Dispatchers.IO)
    }
}