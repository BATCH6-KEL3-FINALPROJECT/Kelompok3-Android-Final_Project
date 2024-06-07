package com.project.skypass.presentation.home.flightclass

import androidx.lifecycle.ViewModel
import com.project.skypass.data.model.SeatClass
import com.project.skypass.data.repository.seatclass.SeatClassRepository

class FlightClassViewModel(private val flightClassRepository: SeatClassRepository): ViewModel() {
    fun getFlightClass(): List<SeatClass> {
        return flightClassRepository.getPriceSeatData()
    }
}