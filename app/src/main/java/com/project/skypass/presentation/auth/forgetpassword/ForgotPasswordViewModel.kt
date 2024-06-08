package com.project.skypass.presentation.auth.forgetpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.auth.AuthRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class ForgotPasswordViewModel(private val authRepository: AuthRepository): ViewModel() {
    fun sendRequestEmail(email: String): LiveData<ResultWrapper<Boolean>> {
        return authRepository.doResetPassword(email).asLiveData(Dispatchers.IO)
    }
}