package com.project.skypass.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.User
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.favoritedestination.FavoriteDestinationRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val favoriteRepository: FavoriteDestinationRepository,
    private val orderHistoryRepository: OrderHistoryRepository,
    private val prefRepository: PrefRepository,
    private val userRepository: UserRepository,): ViewModel() {
    fun getFavoriteDestination() = favoriteRepository.getFavoriteDestination()
    fun getAllOrderHistory() = orderHistoryRepository.getUserOrderHistoryData().asLiveData(Dispatchers.IO)
    fun deleteAllOrderHistory() = orderHistoryRepository.deleteAllOrderHistory().asLiveData(Dispatchers.IO)

    fun getUserId(): String {
        return prefRepository.getUserID()
    }

    fun showDataUser(id: String): LiveData<ResultWrapper<User>> {
        return userRepository.getUser(id).asLiveData(Dispatchers.IO)
    }
}