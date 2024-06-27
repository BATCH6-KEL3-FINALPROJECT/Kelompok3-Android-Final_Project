package com.project.skypass.data.datasource.auth

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.login.LoginItemResponse
import com.project.skypass.data.source.network.model.login.LoginRequestResponse
import com.project.skypass.data.source.network.model.login.LoginResponse
import com.project.skypass.data.source.network.model.otp.ResendOtpRequestResponse
import com.project.skypass.data.source.network.model.otp.ResendOtpResponse
import com.project.skypass.data.source.network.model.otp.VerifyRequestResponse
import com.project.skypass.data.source.network.model.otp.VerifyResponse
import com.project.skypass.data.source.network.model.register.RegisterItemResponse
import com.project.skypass.data.source.network.model.register.RegisterRequestResponse
import com.project.skypass.data.source.network.model.register.RegisterResponse
import com.project.skypass.data.source.network.model.resetpassword.ResetPasswordRequestResponse
import com.project.skypass.data.source.network.model.resetpassword.ResetPasswordResponse
import com.project.skypass.data.source.network.service.ApiService

class AuthDataStoreImpl(private val service: ApiService): AuthDataStore {
    override suspend fun doRegister(payload: RegisterRequestResponse): Response<RegisterItemResponse> {
        return service.doRegister(payload)
    }

    override suspend fun doVerify(payload: VerifyRequestResponse): VerifyResponse {
        return service.doVerify(payload)
    }

    override suspend fun doLogin(payload: LoginRequestResponse): Response<LoginItemResponse> {
        return service.doLogin(payload)
    }

    override suspend fun doResendOtp(payload: ResendOtpRequestResponse): ResendOtpResponse {
        return service.doResendOtp(payload)
    }

    override suspend fun doResetPassword(payload: ResetPasswordRequestResponse): ResetPasswordResponse {
        return service.doResetPassword(payload)
    }
}