package com.project.skypass.presentation.flight.detail

import android.service.autofill.UserData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.repository.flight.FlightRepository
import com.project.skypass.utils.convertFlightDetail
import kotlinx.coroutines.Dispatchers

class FlightDetailViewModel(
    private val flightRepository: FlightRepository,
) : ViewModel() {
    var setDepartureCity:String? = null
    var setArrivalCity:String? = null
    var setSeatClass:String? = null
    var setDepartureDate:String? = null


    fun getHomeData(item: OrderUser){
        setDepartureCity = item.departureCity
        setArrivalCity = item.arrivalCity
        setSeatClass = item.seatClass
        setDepartureDate = convertFlightDetail(item.departureDate.toString())

    }
    fun getFlightDetail() = flightRepository.getFlights(
        departureCity = setDepartureCity,
        arrivalCity = setArrivalCity,
        null,
        null,
        null,
        null,
        null,
        seatClass = setSeatClass,
        null,
        null,
        1,
        10,
        departureDate = setDepartureDate
    ).asLiveData(Dispatchers.IO)
}