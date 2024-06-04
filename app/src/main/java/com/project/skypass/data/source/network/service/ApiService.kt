package com.project.skypass.data.source.network.service

import com.project.skypass.BuildConfig
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
import com.project.skypass.utils.ErrorInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiService {

    @POST("auth/register")
    suspend fun doRegister(
        @Body registerRequest: RegisterRequestResponse
    ): RegisterResponse

    @POST("auth/verify")
    suspend fun doVerify(
        @Body verifyRequest: VerifyRequestResponse
    ): VerifyResponse

    @POST("auth/login")
    suspend fun doLogin(
        @Body loginRequest: LoginRequestResponse
    ):LoginResponse

    @POST("auth/resend-otp")
    suspend fun doResendOtp(
        @Body resendCodeRequest: ResendOtpRequestResponse
    ):ResendOtpResponse

    @POST("auth/reset-password")
    suspend fun doResetPassword(
        @Body resetPasswordRequest: ResetPasswordRequestResponse
    ): ResetPasswordResponse

    companion object {
        @JvmStatic
        operator fun invoke(): ApiService {
            val levelInterceptor = HttpLoggingInterceptor.Level.BODY
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(levelInterceptor)
            val okHttpClient =
                OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(ErrorInterceptor())
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}