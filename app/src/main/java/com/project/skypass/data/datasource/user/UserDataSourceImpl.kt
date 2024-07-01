package com.project.skypass.data.datasource.user

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.local.pref.UserPreference
import com.project.skypass.data.source.network.model.user.deleteuser.DeleteUserResponse
import com.project.skypass.data.source.network.model.user.detailuser.UserResponse
import com.project.skypass.data.source.network.model.user.edituser.Data
import com.project.skypass.data.source.network.service.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UserDataSourceImpl(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
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
        name: RequestBody,
        email: RequestBody,
        phoneNumber: RequestBody,
        photo: MultipartBody.Part?,
    ): Response<Data> {
        return apiService.updateUserData(
            token = token,
            id = id,
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            image = photo,
        )
    }

    override suspend fun deleteUser(token: String): DeleteUserResponse {
        return apiService.deleteUser(token)
    }
}
