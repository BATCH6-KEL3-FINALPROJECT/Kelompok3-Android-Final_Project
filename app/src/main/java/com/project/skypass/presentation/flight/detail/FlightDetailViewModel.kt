package com.project.skypass.presentation.flight.detail

import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.project.skypass.data.model.Flight
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.flight.FlightRepository
import com.project.skypass.presentation.flight.result.FlightResultActivity
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.convertFlightDetail
import kotlinx.coroutines.Dispatchers
import java.lang.IllegalStateException
import java.time.LocalDate

class FlightDetailViewModel(
    private val flightRepository: FlightRepository,
    //private val extras: Bundle?,
    private val orderHistoryRepository: OrderHistoryRepository,
) : ViewModel() {

    //var orderHistoryData = Bundle().getParcelable<OrderUser>(FlightDetailActivity.EXTRA_FLIGHT)

    var setDepartureCity:String? = null
    var setArrivalCity:String? = null
    var setSeatClass:String? = null
    var setDepartureDate:String? = null
    var setPassenger:String? = null

    private var selectedDate: LocalDate? = null

    val date: String?
        get() = setDepartureDate

    fun saveToOrderHistory(item: OrderUser): LiveData<ResultWrapper<Boolean>> {
           return orderHistoryRepository.createOrderHistoryDb(item).asLiveData(Dispatchers.IO)
    }

    fun getHomeData(item: OrderUser){
        setDepartureCity = item.departureCity
        setArrivalCity = item.arrivalCity
        setSeatClass = item.seatClass
        setDepartureDate = convertFlightDetail(item.departureDate.toString())
        setPassenger = item.passengersTotal
    }

    fun getFlightDetail() = flightRepository.getFlights(
        departureCity = setDepartureCity,
        arrivalCity = setArrivalCity,
        null,
        null,
        seatClass = setSeatClass,
        1,
        10,
        departureDate = date,
        null,
        null
    ).asLiveData(Dispatchers.IO)

    fun setSelectedDate(date: LocalDate) {
        selectedDate = date
    }

    fun getSelectedDate(): LocalDate? {
        return selectedDate
    }

}