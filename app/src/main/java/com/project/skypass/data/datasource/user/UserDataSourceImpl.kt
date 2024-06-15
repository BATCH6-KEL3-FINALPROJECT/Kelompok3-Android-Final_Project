package com.project.skypass.data.datasource.user

import com.project.skypass.data.source.local.pref.UserPreference
import com.project.skypass.data.source.network.model.user.deleteuser.DeleteUserResponse
import com.project.skypass.data.source.network.model.user.detailuser.UserResponse
import com.project.skypass.data.source.network.model.user.edituser.EditUserResponse
import com.project.skypass.data.source.network.service.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class UserDataSourceImpl (
    private val userPreference: UserPreference,
    private val apiService: ApiService
) : UserDataSource {
    override fun isUsingDarkMode(): Boolean {
        return userPreference.isUsingDarkMode()
    }

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        userPreference.setUsingDarkMode(isUsingDarkMode)
    }

    override suspend fun getUser(id: String): UserResponse {
        return apiService.getUserData(id)
    }

    override suspend fun editUser(
        token: String,
        id: String,
        name: String?,
        email: String?,
        phoneNumber: String?,
        password: String?,
        photo: File?
    ): EditUserResponse {
        val namePart = name?.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailPart = email?.toRequestBody("text/plain".toMediaTypeOrNull())
        val phoneNumberPart = phoneNumber?.toRequestBody("text/plain".toMediaTypeOrNull())
        val passwordPart = password?.toRequestBody("text/plain".toMediaTypeOrNull())
        val photoRequestBody = photo?.asRequestBody("image/*".toMediaTypeOrNull())
        val photoPart = photoRequestBody?.let { MultipartBody.Part.createFormData("image", photo.name, it) }
        return apiService.updateUserData(token, id, namePart, emailPart, phoneNumberPart, passwordPart, photoPart)
    }

    override suspend fun deleteUser(id: String): DeleteUserResponse {
        return apiService.deleteUser(id)
    }
}