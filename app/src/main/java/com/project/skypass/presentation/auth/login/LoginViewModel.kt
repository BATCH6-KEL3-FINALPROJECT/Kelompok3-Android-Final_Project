package com.project.skypass.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.auth.AuthRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repository: AuthRepository): ViewModel() {
    fun doLogin(
        email: String,
        password: String
    ): LiveData<ResultWrapper<String>> {
        return repository.doLogin(email, password).asLiveData(Dispatchers.IO)
    }
}