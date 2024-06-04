package com.project.skypass.data.repository.auth

import com.project.skypass.data.datasource.auth.AuthDataStore
import com.project.skypass.data.source.network.model.login.LoginRequestResponse
import com.project.skypass.data.source.network.model.login.LoginResponse
import com.project.skypass.data.source.network.model.otp.ResendOtpRequestResponse
import com.project.skypass.data.source.network.model.otp.VerifyRequestResponse
import com.project.skypass.data.source.network.model.register.RegisterRequestResponse
import com.project.skypass.data.source.network.model.resetpassword.ResetPasswordRequestResponse
import com.project.skypass.utils.ErrorInterceptor
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(private val dataStore: AuthDataStore): AuthRepository {
    override fun doLogin(email: String, password: String): Flow<ResultWrapper<LoginResponse>> {
        return proceedFlow {
            try {
                dataStore.doLogin(
                    LoginRequestResponse(
                        email = email,
                        password = password
                    )
                )
            } catch (e: ErrorInterceptor.HttpException) {
                throw Exception(e.message)
            }
        }
    }

    override fun doRegister(
        name: String,
        email: String,
        phoneNumber: String,
        password: String
    ): Flow<ResultWrapper<String>> {
        return proceedFlow {
            dataStore.doRegister(
                RegisterRequestResponse(
                    name = name,
                    email = email,
                    phoneNumber = phoneNumber,
                    password = password
                )
            ).status ?: "Failed"
        }
    }

    override fun doVerify(email: String, otp: String): Flow<ResultWrapper<String>> {
        return proceedFlow {
            dataStore.doVerify(
                VerifyRequestResponse(
                    email = email,
                    otp = otp
                )
            ).status ?: "Failed"
        }
    }

    override fun doResendOtp(email: String): Flow<ResultWrapper<String>> {
        return proceedFlow {
            dataStore.doResendOtp(
                ResendOtpRequestResponse(
                    email = email
                )
            ).status ?: "failed"
        }
    }

    override fun doResetPassword(email: String): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            dataStore.doResetPassword(
                ResetPasswordRequestResponse(
                    email = email
                )
            ).is_success ?: false
        }
    }
}