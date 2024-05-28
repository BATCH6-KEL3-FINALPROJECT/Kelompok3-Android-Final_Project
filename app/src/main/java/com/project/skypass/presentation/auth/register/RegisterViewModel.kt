package com.project.skypass.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.auth.AuthRepository
import kotlinx.coroutines.Dispatchers

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {
    fun doRegister(
        fullName: String,
        email: String,
        phoneNumber: String,
        password: String,
    ) = repository.doRegister(fullName, email, phoneNumber, password).asLiveData(Dispatchers.IO)
}