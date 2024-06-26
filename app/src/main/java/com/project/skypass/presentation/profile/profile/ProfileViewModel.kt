package com.project.skypass.presentation.profile.profile

import androidx.lifecycle.ViewModel
import com.project.skypass.data.repository.profile.ProfileRepository

class ProfileViewModel(private val repository: ProfileRepository, ) : ViewModel() {
    fun getProfile() = repository.getProfile()
}
