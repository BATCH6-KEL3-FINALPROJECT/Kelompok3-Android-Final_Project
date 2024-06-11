package com.project.skypass.presentation.flight.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.flight.FlightRepository
import kotlinx.coroutines.Dispatchers

class FlightDetailViewModel(
    private val flightRepository: FlightRepository,
) : ViewModel() {
    fun getFlightDetail() = flightRepository.getFlights(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        1,
        10,
        "2024-06-11"
    ).asLiveData(Dispatchers.IO)
}