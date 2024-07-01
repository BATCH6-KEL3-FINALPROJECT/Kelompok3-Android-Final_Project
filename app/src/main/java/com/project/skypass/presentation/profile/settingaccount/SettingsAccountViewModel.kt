package com.project.skypass.presentation.profile.settingaccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.User
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.data.source.network.model.user.deleteuser.DeleteUserResponse
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class SettingsAccountViewModel(
    private val userRepository: UserRepository,
    private val prefRepository: PrefRepository,
    private val repositoryOrderHistory: OrderHistoryRepository,
) : ViewModel() {
    private val _isUsingDarkMode = MutableLiveData<Boolean>()
    val isUsingDarkMode: LiveData<Boolean> get() = _isUsingDarkMode

    init {
        _isUsingDarkMode.value = userRepository.isUsingDarkMode()
    }

    fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        userRepository.setUsingDarkMode(isUsingDarkMode)
        _isUsingDarkMode.value = isUsingDarkMode
    }

    fun deleteOrderHistoryUser(): LiveData<ResultWrapper<Boolean>> {
        return repositoryOrderHistory.deleteAllOrderHistory().asLiveData(Dispatchers.IO)
    }

    fun getIdUser(): String {
        return prefRepository.getUserID()
    }

    fun deleteUser(id: String): LiveData<ResultWrapper<DeleteUserResponse>> {
        return userRepository.deleteUser(id).asLiveData(Dispatchers.IO)
    }

    fun getUser(id: String): LiveData<ResultWrapper<User>> {
        return userRepository.getUser(id).asLiveData(Dispatchers.IO)
    }
}
