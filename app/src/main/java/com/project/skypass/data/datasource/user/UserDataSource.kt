package com.project.skypass.data.datasource.user

import com.project.skypass.data.source.network.model.user.detailuser.UserResponse
import com.project.skypass.data.source.network.model.user.edituser.EditUserResponse
import java.io.File

interface UserDataSource {

    fun isUsingDarkMode() : Boolean

    fun setUsingDarkMode(isUsingDarkMode : Boolean)

    suspend fun getUser(id: String): UserResponse

    suspend fun editUser(
        token: String,
        id: String,
        name: String? = null,
        email: String? = null,
        phoneNumber: String? = null,
        password: String? = null,
        photo: File? = null
    ): EditUserResponse

}