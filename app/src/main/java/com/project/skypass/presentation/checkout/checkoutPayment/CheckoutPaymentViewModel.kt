package com.project.skypass.presentation.checkout.checkoutPayment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.Booking
import com.project.skypass.data.model.PassengersData
import com.project.skypass.data.model.Payment
import com.project.skypass.data.repository.checkout.CheckoutRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class CheckoutPaymentViewModel(
    private val prefRepository: PrefRepository,
    private val checkoutRepository: CheckoutRepository
): ViewModel() {
    private val _passengersData = MutableLiveData<List<PassengersData>>()
    val passengersData: LiveData<List<PassengersData>> get() = _passengersData

    fun setPassengersData(data: List<PassengersData>) {
        _passengersData.value = data
    }

    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun getBooking(token: String, bookingId: String): LiveData<ResultWrapper<List<Booking>>> {
        return checkoutRepository.getBookingData(token, bookingId).asLiveData(Dispatchers.IO)
    }

    fun createPayment(token: String, paymentId: String): LiveData<ResultWrapper<Payment>> {
        return checkoutRepository.createPayment(token, paymentId).asLiveData(Dispatchers.IO)
    }
}