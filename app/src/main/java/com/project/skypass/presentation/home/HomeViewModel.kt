package com.project.skypass.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.project.skypass.data.model.BannerHome
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.User
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.bannerHome.BannerHomeRepository
import com.project.skypass.data.repository.favoritedestination.FavoriteDestinationRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val favoriteRepository: FavoriteDestinationRepository,
    private val orderHistoryRepository: OrderHistoryRepository,
    private val prefRepository: PrefRepository,
    private val bannerHome: BannerHomeRepository,
    private val userRepository: UserRepository,): ViewModel() {


    fun getBannerHome() = bannerHome.getBannerData()
    fun getFavoriteDestination() = favoriteRepository.getFavoriteDestination().asLiveData(Dispatchers.IO)
    fun getAllOrderHistory() = orderHistoryRepository.getUserOrderHistoryData().asLiveData(Dispatchers.IO)
    fun deleteAllOrderHistory() = orderHistoryRepository.deleteAllOrderHistory().asLiveData(Dispatchers.IO)

    fun getUserId(): String {
        return prefRepository.getUserID()
    }

    fun showDataUser(id: String): LiveData<ResultWrapper<User>> {
        return userRepository.getUser(id).asLiveData(Dispatchers.IO)
    }

    fun isLogin(): Boolean {
        return prefRepository.isLogin()
    }
}