package com.project.skypass.presentation.profile.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.skypass.data.model.User
import com.project.skypass.data.repository.OrderHistory.OrderHistoryRepository
import com.project.skypass.data.repository.profile.ProfileRepository

class ProfileViewModel(private val repository: ProfileRepository, ) : ViewModel() {
    fun getProfile() = repository.getProfile()
}
