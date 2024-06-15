package com.project.skypass.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.User
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ProfileViewModelExample(
    private val prefRepository: PrefRepository,
    private val userRepository: UserRepository,
    private val repositoryOrderHistory: OrderHistoryRepository

): ViewModel() {
    fun getUserId(): String {
        return prefRepository.getUserID()
    }

    fun showDataUser(id: String): LiveData<ResultWrapper<User>> {
        return userRepository.getUser(id).asLiveData(Dispatchers.IO)
    }

    fun deleteOrderHistoryUser() = repositoryOrderHistory.deleteAllOrderHistory().asLiveData(Dispatchers.IO)
}