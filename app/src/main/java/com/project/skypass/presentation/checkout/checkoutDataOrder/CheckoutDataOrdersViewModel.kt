package com.project.skypass.presentation.checkout.checkoutDataOrder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.User
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.profile.ProfileRepository
import com.project.skypass.data.repository.user.UserRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class CheckoutDataOrdersViewModel(
    private val userRepository: UserRepository,
    private val prefRepository: PrefRepository,
    ): ViewModel() {
    fun showDataUser(id: String): LiveData<ResultWrapper<User>> {
        return userRepository.getUser(id).asLiveData(Dispatchers.IO)
    }

    fun getUserId(): String {
        return prefRepository.getUserID()
    }

}