package com.project.skypass.data.datasource.user

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.local.pref.UserPreference
import com.project.skypass.data.source.network.model.user.deleteuser.DeleteUserResponse
import com.project.skypass.data.source.network.model.user.detailuser.UserItemResponse
import com.project.skypass.data.source.network.model.user.detailuser.UserResponse
import com.project.skypass.data.source.network.model.user.edituser.EditUserItemResponse
import com.project.skypass.data.source.network.service.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.File
import com.project.skypass.data.source.network.model.user.deleteuser.Data

class UserDataSourceImplTest {
    @MockK
    lateinit var pref: UserPreference

    @MockK
    lateinit var service: ApiService
    private lateinit var ds: UserDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = UserDataSourceImpl(pref, service)
    }

    @Test
    fun isUsingDarkMode() {
        every { pref.isUsingDarkMode() } returns true
        val result = ds.isUsingDarkMode()
        assertEquals(true, result)
        verify { pref.isUsingDarkMode() }
    }

    @Test
    fun setUsingDarkMode() {
        every { pref.setUsingDarkMode(any()) } just runs
        ds.setUsingDarkMode(true)
        verify { pref.setUsingDarkMode(true) }
    }

    @Test
    fun getUser() =
        runBlocking {
            val userId = "user123"
            val userItemResponse =
                UserItemResponse(
                    createdAt = "2024-06-19",
                    email = "test@example.com",
                    image_id = "image123",
                    image_url = "https://example.com/image.jpg",
                    is_verified = true,
                    name = "Test User",
                    phone_number = "1234567890",
                    role = "user",
                    updatedAt = "2024-06-19",
                    user_id = "user123",
                )
            val data =
                com.project.skypass.data.source.network.model.user.detailuser.Data(userItemResponse)
            val userResponse =
                UserResponse(
                    code = 200,
                    data = data,
                    is_success = true,
                    message = "User data retrieved successfully.",
                )
            coEvery { service.getUserData(userId) } returns userResponse
            val result = ds.getUser(userId)
            assertEquals(userResponse, result)
        }

    @Test
    fun editUser() =
        runBlocking {
            val token = "token123"
            val userId = "user123"
            val name = "Test User"
            val email = "test@example.com"
            val phoneNumber = "1234567890"
            val password = "password"
            val photo = File("path/to/photo.jpg")
            val id = "user123"

            val namePart = name.toRequestBody("text/plain".toMediaTypeOrNull())
            val emailPart = email.toRequestBody("text/plain".toMediaTypeOrNull())
            val phoneNumberPart = phoneNumber.toRequestBody("text/plain".toMediaTypeOrNull())
            val photoRequestBody = photo.asRequestBody("image/*".toMediaTypeOrNull())
            val photoPart = MultipartBody.Part.createFormData("image", photo.name, photoRequestBody)

            val editUserItemResponse =
                EditUserItemResponse(
                    createdAt = "2024-06-19",
                    email = email,
                    image_id = "image123",
                    image_url = "https://example.com/image.jpg",
                    is_verified = true,
                    name = name,
                    password = password,
                    phone_number = phoneNumber,
                    refresh_token = null,
                    role = "user",
                    updatedAt = "2024-06-19",
                    user_id = userId,
                )
            val editUserData = com.project.skypass.data.source.network.model.user.edituser.Data(editUserItemResponse)
            val editUserResponse = Response(true, "Success", editUserData)
            coEvery {
                service.updateUserData(
                    token = token,
                    id = userId,
                    name = any<RequestBody>(),
                    email = any<RequestBody>(),
                    phoneNumber = any<RequestBody>(),
                    image = any<MultipartBody.Part>(),
                )
            } returns editUserResponse

            val result = ds.editUser(token, id, namePart, emailPart, phoneNumberPart, photoPart)
            assertEquals(editUserResponse, result)
        }

    @Test
    fun deleteUser() =
        runBlocking {
            val userId = "user123"
            val response = Data()
            val deleteUserResponse = DeleteUserResponse(200, response, true, "Success")

            coEvery { service.deleteUser(userId) } returns deleteUserResponse

            val result = ds.deleteUser(userId)
            assertEquals(deleteUserResponse, result)
        }
}
