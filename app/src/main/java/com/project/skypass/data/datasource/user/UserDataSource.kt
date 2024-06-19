package com.project.skypass.data.datasource.user

import com.project.skypass.data.source.network.model.user.deleteuser.DeleteUserResponse
import com.project.skypass.data.source.network.model.user.detailuser.UserResponse
import com.project.skypass.data.source.network.model.user.edituser.EditUserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import java.io.File

interface UserDataSource {

    fun isUsingDarkMode() : Boolean

    fun setUsingDarkMode(isUsingDarkMode : Boolean)

    suspend fun getUser(id: String): UserResponse

    suspend fun editUser(
        token: String,
        id: String,
        name: RequestBody,
        email: RequestBody,
        phoneNumber: RequestBody,
        photo: MultipartBody.Part?
    ): EditUserResponse

    suspend fun deleteUser(id: String): DeleteUserResponse

}