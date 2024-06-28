package com.project.skypass.presentation.checkout.checkoutDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.PassengersData
import com.project.skypass.data.model.Response
import com.project.skypass.data.repository.checkout.CheckoutRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.source.network.model.checkout.response.CheckoutResponse
import com.project.skypass.data.source.network.model.checkout.response.DataCheckout
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class CheckoutDetailViewModel(
    private val checkoutRepository: CheckoutRepository,
    private val prefRepository: PrefRepository
): ViewModel() {

    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun createBooking(
        token: String,
        totalAmount: Int,
        departureFlightId: String,
        returnFlightId: String?,
        fullName: String? = null,
        familyName: String? = null,
        email: String? = null,
        phone: String? = null,
        passenger: List<PassengersData>
    ): LiveData<ResultWrapper<Response<DataCheckout>>> {
        return checkoutRepository.createBooking(
            token,
            totalAmount,
            departureFlightId,
            returnFlightId,
            fullName,
            familyName,
            email,
            phone,
            passenger
        ).asLiveData(Dispatchers.IO)
    }
}