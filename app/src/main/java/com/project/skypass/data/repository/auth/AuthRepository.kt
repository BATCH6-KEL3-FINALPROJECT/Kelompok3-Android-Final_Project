package com.project.skypass.data.repository.auth

import com.project.skypass.data.source.network.model.login.LoginResponse
import com.project.skypass.data.source.network.model.register.RegisterResponse
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun doLogin(
        email: String,
        password: String
    ): Flow<ResultWrapper<LoginResponse>>

    fun doRegister(
        name: String,
        email: String,
        phoneNumber: String,
        password: String
    ): Flow<ResultWrapper<RegisterResponse>>

    fun doVerify(
        email: String,
        otp: String
    ): Flow<ResultWrapper<Boolean>>

    fun doResendOtp(
        email: String
    ): Flow<ResultWrapper<String>>

    fun doResetPassword(
        email: String
    ): Flow<ResultWrapper<Boolean>>
}