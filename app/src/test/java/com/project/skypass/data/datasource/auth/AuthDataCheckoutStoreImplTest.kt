package com.project.skypass.data.datasource.auth

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.login.LoginItemResponse
import com.project.skypass.data.source.network.model.login.LoginRequestResponse
import com.project.skypass.data.source.network.model.otp.Data
import com.project.skypass.data.source.network.model.otp.ResendOtpRequestResponse
import com.project.skypass.data.source.network.model.otp.ResendOtpResponse
import com.project.skypass.data.source.network.model.otp.VerifyRequestResponse
import com.project.skypass.data.source.network.model.otp.VerifyResponse
import com.project.skypass.data.source.network.model.register.RegisterItemResponse
import com.project.skypass.data.source.network.model.register.RegisterNewUserResponse
import com.project.skypass.data.source.network.model.register.RegisterRequestResponse
import com.project.skypass.data.source.network.model.resetpassword.ResetPasswordRequestResponse
import com.project.skypass.data.source.network.model.resetpassword.ResetPasswordResponse
import com.project.skypass.data.source.network.service.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthDataStoreImplTest {
    @MockK
    lateinit var service: ApiService
    private lateinit var ds: AuthDataStore

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = AuthDataStoreImpl(service)
    }

    @Test
    fun doRegister() =
        runBlocking {
            val payload = RegisterRequestResponse("test@example.com", "John Doe", "123456", "123456")
            val response =
                RegisterItemResponse(
                    "test@example.com",
                    RegisterNewUserResponse(
                        "123456",
                        "test@example.com",
                        true,
                        "John Doe",
                        "123456",
                        "123456",
                        "123456",
                        "user",
                        "123456",
                        "123456",
                    ),
                )
            val expectedResponse = Response(true, "Success", response)
            coEvery { service.doRegister(any()) } returns expectedResponse

            val result = ds.doRegister(payload)

            assertEquals(expectedResponse, result)
            coVerify { service.doRegister(payload) }
        }

    @Test
    fun doVerify() =
        runBlocking {
            val payload = VerifyRequestResponse("test@example.com", "123456")
            val expectedResponse =
                VerifyResponse(
                    123456,
                    List(1) {
                        Data(
                            "123456",
                            "test@example.com",
                            true,
                            "John Doe",
                            "123456",
                            "123456",
                            "123456",
                            "user",
                            "123456",
                            "123456",
                        )
                    },
                    true,
                    "Success",
                )
            coEvery { service.doVerify(payload) } returns expectedResponse

            val result = ds.doVerify(payload)

            assertEquals(expectedResponse, result)
            coVerify { service.doVerify(payload) }
        }

    @Test
    fun doLogin() =
        runBlocking {
            val payload = LoginRequestResponse("test@example.com", "password")
            val loginItemResponse = LoginItemResponse("123456", "123456")
            val expectedResponse = Response(true, "Success", loginItemResponse)
            coEvery { service.doLogin(payload) } returns expectedResponse

            val result = ds.doLogin(payload)

            assertEquals(expectedResponse, result)
            coVerify { service.doLogin(payload) }
        }

    @Test
    fun doResendOtp() =
        runBlocking {
            val payload = ResendOtpRequestResponse("test@example.com")
            val expectedResponse = ResendOtpResponse("true", "Success")
            coEvery { service.doResendOtp(payload) } returns expectedResponse

            val result = ds.doResendOtp(payload)

            assertEquals(expectedResponse, result)
            coVerify { service.doResendOtp(payload) }
        }

    @Test
    fun doResetPassword() =
        runBlocking {
            val payload = ResetPasswordRequestResponse("test@example.com")
            val expectedResponse = ResetPasswordResponse(123456, true, "Success", "123456")
            coEvery { service.doResetPassword(payload) } returns expectedResponse

            val result = ds.doResetPassword(payload)

            assertEquals(expectedResponse, result)
            coVerify { service.doResetPassword(payload) }
        }
}
