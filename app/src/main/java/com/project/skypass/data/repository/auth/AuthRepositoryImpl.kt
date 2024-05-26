package com.project.skypass.data.repository.auth

import com.project.skypass.data.datasource.auth.AuthDataStore
import com.project.skypass.data.source.network.model.login.LoginRequestResponse
import com.project.skypass.data.source.network.model.login.LoginResponse
import com.project.skypass.data.source.network.model.otp.VerifyRequestResponse
import com.project.skypass.data.source.network.model.register.RegisterRequestResponse
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
        password: String,
        phoneNumber: String,
        role: String,
    ): Flow<ResultWrapper<String>> {
        return proceedFlow {
            dataStore.doRegister(
                RegisterRequestResponse(
                    name = name,
                    email = email,
                    password = password,
                    phoneNumber = phoneNumber,
                    roles = role
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

}