package com.project.skypass.presentation.flight.filter

import androidx.lifecycle.ViewModel
import com.project.skypass.data.model.FilterFlight
import com.project.skypass.data.repository.flight.FlightRepository

class FilterViewModel(private val flightRepository: FlightRepository): ViewModel() {
    fun getFilter(): List<FilterFlight> {
        return flightRepository.filterFlights()
    }
}