package com.project.skypass.data.repository.user

import com.project.skypass.data.datasource.user.UserDataSource
import com.project.skypass.data.model.Response
import com.project.skypass.data.model.User
import com.project.skypass.data.source.network.model.user.deleteuser.DeleteUserResponse
import com.project.skypass.data.source.network.model.user.detailuser.Data
import com.project.skypass.data.source.network.model.user.detailuser.UserItemResponse
import com.project.skypass.data.source.network.model.user.detailuser.UserResponse
import com.project.skypass.data.source.network.model.user.edituser.EditUserItemResponse
import com.project.skypass.data.source.network.model.user.edituser.EditUserResponse
import com.project.skypass.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
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
import java.io.IOException
import java.lang.Error

class UserRepositoryImplTest {

    @MockK
    lateinit var ds: UserDataSource
    @MockK
    private lateinit var userRepository: UserRepository
    private lateinit var repo: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = UserRepositoryImpl(ds)
    }

    @Test
    fun `isUsingDarkMode returns true`() = runBlocking {
        every { ds.isUsingDarkMode() } returns true
        assertTrue(repo.isUsingDarkMode())
        verify { ds.isUsingDarkMode() }
    }

    @Test
    fun `isUsingDarkMode returns false`() = runBlocking {
        every { ds.isUsingDarkMode() } returns false
        assertFalse(repo.isUsingDarkMode())
        verify { ds.isUsingDarkMode() }
    }

    @Test
    fun `setUsingDarkMode sets true`() = runBlocking {
        every { ds.setUsingDarkMode(true) } returns Unit
        repo.setUsingDarkMode(true)
        verify { ds.setUsingDarkMode(true) }
    }

    @Test
    fun `setUsingDarkMode sets false`() = runBlocking {
        every { ds.setUsingDarkMode(false) } returns Unit
        repo.setUsingDarkMode(false)
        verify { ds.setUsingDarkMode(false) }
    }

    @Test
    fun getUser() = runBlocking {
        val userItemResponse = UserItemResponse(
            createdAt = "createdAt",
            email = "email",
            image_id = "imageId",
            image_url = "imageUrl",
            is_verified = true,
            name = "name",
            phone_number = "123456",
            role = "role",
            updatedAt = "updatedAt",
            user_id = "userId"
        )
        val dataUser = Data(userItemResponse)
        val userResponse = UserResponse(200, dataUser, true, "Success")
        coEvery { ds.getUser("id") } returns userResponse
        repo.getUser("id").collect { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    val userData = result.payload
                    val expectedUserItemResponse = UserItemResponse(
                        createdAt = userData?.createdAt,
                        email = userData?.email,
                        image_id = userData?.imageId,
                        image_url = userData?.photoUrl,
                        is_verified = userData?.isVerified,
                        name = userData?.name,
                        phone_number = userData?.phoneNumber,
                        role = userData?.role,
                        updatedAt = userData?.updatedAt,
                        user_id = userData?.userId
                    )
                    assertEquals(userItemResponse, expectedUserItemResponse)
                }
                is ResultWrapper.Loading -> delay(100)
                else -> fail("Expected Success result, but got ${result::class.simpleName}")
            }
        }
        coVerify { ds.getUser("id") }
    }

    @Test
    fun editUser() = runBlocking {
        val token = "token"
        val id = "id"
        val name = "name"
        val email = "email"
        val phoneNumber = "phoneNumber"
        val photo = File("photo.jpg")
        val editUserItemResponse = EditUserItemResponse(
            createdAt = "createdAt",
            email = "email",
            image_id = "imageId",
            image_url = "imageUrl",
            is_verified = true,
            name = "name",
            password = "password",
            phone_number = "123456",
            refresh_token = "refreshToken",
            role = "role",
            updatedAt = "updatedAt",
            user_id = "userId"
        )
        val editUser = com.project.skypass.data.source.network.model.user.edituser.Data(editUserItemResponse)
        val editUserResponse = Response(true, "Success", editUser)
        coEvery {
            ds.editUser(
                "Bearer $token",
                id,
                name.toRequestBody("text/plain".toMediaTypeOrNull()),
                email.toRequestBody("text/plain".toMediaTypeOrNull()),
                phoneNumber.toRequestBody("text/plain".toMediaTypeOrNull()),
                MultipartBody.Part.createFormData("images", photo.name, photo.asRequestBody("image/jpeg".toMediaTypeOrNull()))
            )
        } returns editUserResponse
        repo.editUser(token, id, name, email, phoneNumber, photo).collect { result ->
            when (result) {
                is ResultWrapper.Success -> assertEquals(editUserResponse, result.payload)
                is ResultWrapper.Loading -> delay(100)
                is ResultWrapper.Error -> assertNull(result.message)
                else -> fail("Expected Success result, but got ${result::class.simpleName}")
            }
        }
        coVerify {
            ds.editUser(
                "Bearer $token",
                id,
                any<RequestBody>(),
                any<RequestBody>(),
                any<RequestBody>(),
                any<MultipartBody.Part>()
            )
        }
    }

    @Test
    fun deleteUser() = runBlocking {
        val id = "id"
        val deleteUserResponse = DeleteUserResponse("success", "success")
        coEvery { ds.deleteUser(id) } returns deleteUserResponse
        repo.deleteUser(id).collect { result ->
            when (result) {
                is ResultWrapper.Success -> assertEquals(deleteUserResponse, result.payload)
                is ResultWrapper.Loading -> delay(100)
                else -> fail("Expected Success result, but got ${result::class.simpleName}")
            }
        }
        coVerify { ds.deleteUser(id) }
    }
}