package com.project.skypass.presentation.flight.detail

import android.os.Bundle
import android.service.autofill.UserData
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

class FlightDetailViewModel(
    private val flightRepository: FlightRepository,
    //private val extras: Bundle?,
    private val orderHistoryRepository: OrderHistoryRepository,
) : ViewModel() {

    var orderHistoryData = Bundle().getParcelable<OrderUser>(FlightDetailActivity.EXTRA_FLIGHT)

    private var setDepartureCity:String? = null
    private var setArrivalCity:String? = null
    private var setSeatClass:String? = null
    private var setDepartureDate:String? = null
    private var setPassenger:String? = null

    fun saveToOrderHistory(): LiveData<ResultWrapper<Boolean>> {
        return orderHistoryData?.let {
            orderHistoryRepository.createOrderHistoryDb(it).asLiveData(Dispatchers.IO)
        } ?: liveData { emit(ResultWrapper.Error(IllegalStateException("Catalog not found"))) }
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