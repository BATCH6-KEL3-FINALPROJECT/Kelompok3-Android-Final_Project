package com.project.skypass.presentation.history.detailhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.History
import com.project.skypass.data.model.Payment
import com.project.skypass.data.model.User
import com.project.skypass.data.repository.checkout.CheckoutRepository
import com.project.skypass.data.repository.history.HistoryRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.ticket.PrintTicketRepository
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class DetailHistoryViewModel(
    private val prefRepository: PrefRepository,
    private val detailHistoryRepository: HistoryRepository,
    private val printTicketRepository: PrintTicketRepository,
    private val userRepository: UserRepository,
    private val checkoutRepository: CheckoutRepository,
): ViewModel() {

    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun getEmailUser(): String {
        return prefRepository.getEmail()
    }

    fun getDetailHistory(token: String, idDetail: String): LiveData<ResultWrapper<History>> {
        return detailHistoryRepository.getDetailHistory(token, idDetail).asLiveData(Dispatchers.IO)
    }

    fun printTicket(
        token: String,
        id: String,
        email: String,
    ): LiveData<ResultWrapper<Boolean>> {
        return printTicketRepository.printTicket(token, id, email).asLiveData(Dispatchers.IO)
    }

    fun showDataUser(id: String): LiveData<ResultWrapper<User>> {
        return userRepository.getUser(id).asLiveData(Dispatchers.IO)
    }

    fun getUserId(): String {
        return prefRepository.getUserID()
    }

    fun createPayment(
        token: String,
        paymentId: String,
    ): LiveData<ResultWrapper<Payment>> {
        return checkoutRepository.createPayment(token, paymentId).asLiveData(Dispatchers.IO)
    }
}