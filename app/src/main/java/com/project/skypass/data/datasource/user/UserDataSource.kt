package com.project.skypass.data.datasource.user

import com.project.skypass.data.model.Response
import com.project.skypass.data.source.network.model.user.deleteuser.DeleteUserResponse
import com.project.skypass.data.source.network.model.user.detailuser.UserResponse
import com.project.skypass.data.source.network.model.user.edituser.Data
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface UserDataSource {
    fun isUsingDarkMode(): Boolean

    fun setUsingDarkMode(isUsingDarkMode: Boolean)

    suspend fun getUser(id: String): UserResponse

    suspend fun editUser(
        token: String,
        id: String,
        name: RequestBody,
        email: RequestBody,
        phoneNumber: RequestBody,
        photo: MultipartBody.Part?,
    ): Response<Data>

    suspend fun deleteUser(id: String): DeleteUserResponse
}
