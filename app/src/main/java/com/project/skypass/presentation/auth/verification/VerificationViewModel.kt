package com.project.skypass.presentation.auth.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.auth.AuthRepository
import kotlinx.coroutines.Dispatchers

class VerificationViewModel(private val repository: AuthRepository) : ViewModel() {
    fun doResendCode(
        email: String
    ) = repository.doResendOtp(email).asLiveData(Dispatchers.IO)

    fun doVerify(
        email: String,
        otp: String
    ) = repository.doVerify(email, otp).asLiveData(Dispatchers.IO)
}