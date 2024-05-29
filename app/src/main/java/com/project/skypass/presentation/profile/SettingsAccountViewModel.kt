package com.project.skypass.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.skypass.data.repository.user.UserRepository

class SettingsAccountViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _isUsingDarkMode = MutableLiveData<Boolean>()
    val isUsingDarkMode: LiveData<Boolean> get() = _isUsingDarkMode

    init {
        _isUsingDarkMode.value = userRepository.isUsingDarkMode()
    }

    fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        userRepository.setUsingDarkMode(isUsingDarkMode)
        _isUsingDarkMode.value = isUsingDarkMode
    }
}
