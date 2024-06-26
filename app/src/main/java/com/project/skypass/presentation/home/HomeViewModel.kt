package com.project.skypass.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.favoritedestination.FavoriteDestinationRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val favoriteRepository: FavoriteDestinationRepository, private val orderHistoryRepository: OrderHistoryRepository): ViewModel() {
    fun getFavoriteDestination() = favoriteRepository.getFavoriteDestination()
    fun getAllOrderHistory() = orderHistoryRepository.getUserOrderHistoryData().asLiveData(Dispatchers.IO)
    fun removeCart(item: OrderUser) = orderHistoryRepository.deleteOrderHistory(item).asLiveData(Dispatchers.IO)
    fun deleteAllOrderHistory() = orderHistoryRepository.deleteAllOrderHistory().asLiveData(Dispatchers.IO)
}