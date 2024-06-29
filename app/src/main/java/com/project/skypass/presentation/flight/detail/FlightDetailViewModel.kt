package com.project.skypass.presentation.flight.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.FilterFlight
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.flight.FlightRepository
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.convertFlightDetail
import kotlinx.coroutines.Dispatchers
import java.time.LocalDate

class FlightDetailViewModel(
    private val flightRepository: FlightRepository,
    // private val extras: Bundle?,
    private val orderHistoryRepository: OrderHistoryRepository,
) : ViewModel() {
    // var orderHistoryData = Bundle().getParcelable<OrderUser>(FlightDetailActivity.EXTRA_FLIGHT)

    private var setDepartureCity: String? = null
    private var setArrivalCity: String? = null
    private var setSeatClass: String? = null
    var setDepartureDate: String? = null
    private var setPassenger: String? = null
    private var setPrice: String? = null
    private var setDepartureTime: String? = null
    private var filterCriteria: String? = null

    private var selectedDate: LocalDate? = null

    val date: String?
        get() = setDepartureDate

    val time: String?
        get() = setDepartureTime

    val price: String?
        get() = setPrice

    fun saveToOrderHistory(item: OrderUser): LiveData<ResultWrapper<Boolean>> {
        return orderHistoryRepository.createOrderHistoryDb(item).asLiveData(Dispatchers.IO)
    }

    fun getHomeData(item: OrderUser) {
        setDepartureCity = item.departureCity
        setArrivalCity = item.arrivalCity
        setSeatClass = item.seatClass
        setDepartureDate = convertFlightDetail(item.departureDate.toString())
        setPassenger = item.passengersTotal
    }

    fun getFlightDetail() =
        flightRepository.getFlights(
            departureCity = setDepartureCity,
            arrivalCity = setArrivalCity,
            null,
            null,
            seatClass = setSeatClass,
            1,
            10,
            departureDate = date,
            departureTime = time,
            price = price,
        ).asLiveData(Dispatchers.IO)

    fun setSelectedDate(date: LocalDate) {
        selectedDate = date
    }

    fun getSelectedDate(): LocalDate? {
        return selectedDate
    }

    fun applyFilter(criteria: FilterFlight) {
        filterCriteria = criteria.criteria
        when (criteria.criteria) {
            "Tidak ada filter" -> {
                setDepartureTime = null
                setPrice = null
            }
            "Tercepat" -> {
                val early = "early"
                setDepartureTime = early
                setPrice = null
            }
            "Terlama" -> {
                val late = "late"
                setDepartureTime = late
                setPrice = null
            }
            "Termurah" -> {
                val cheap = "lowest"
                setDepartureTime = null
                setPrice = cheap
            }
            "Termahal" -> {
                val expensive = "highest"
                setDepartureTime = null
                setPrice = expensive
            }
        }
        getFlightDetail()
    }
}
