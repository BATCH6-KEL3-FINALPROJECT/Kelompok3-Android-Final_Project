package com.project.skypass.data.datasource.auth

import com.project.skypass.data.source.network.model.login.LoginRequestResponse
import com.project.skypass.data.source.network.model.login.LoginResponse
import com.project.skypass.data.source.network.model.otp.ResendOtpRequestResponse
import com.project.skypass.data.source.network.model.otp.ResendOtpResponse
import com.project.skypass.data.source.network.model.otp.VerifyRequestResponse
import com.project.skypass.data.source.network.model.otp.VerifyResponse
import com.project.skypass.data.source.network.model.register.RegisterRequestResponse
import com.project.skypass.data.source.network.model.register.RegisterResponse
import com.project.skypass.data.source.network.model.resetpassword.ResetPasswordRequestResponse
import com.project.skypass.data.source.network.model.resetpassword.ResetPasswordResponse

interface AuthDataStore {
    suspend fun doRegister(payload: RegisterRequestResponse): RegisterResponse
    suspend fun doVerify(payload: VerifyRequestResponse): VerifyResponse
    suspend fun doLogin(payload: LoginRequestResponse): LoginResponse
    suspend fun doResendOtp(payload: ResendOtpRequestResponse): ResendOtpResponse
    suspend fun doResetPassword(payload: ResetPasswordRequestResponse): ResetPasswordResponse
}