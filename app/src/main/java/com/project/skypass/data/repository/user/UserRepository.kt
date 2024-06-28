package com.project.skypass.data.repository.user

import android.net.Uri
import com.project.skypass.data.model.Response
import com.project.skypass.data.model.User
import com.project.skypass.data.source.network.model.user.deleteuser.DeleteUserResponse
import com.project.skypass.data.source.network.model.user.edituser.Data
import com.project.skypass.data.source.network.model.user.edituser.EditUserResponse
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UserRepository {

    fun isUsingDarkMode() : Boolean

    fun setUsingDarkMode(isUsingDarkMode : Boolean)

    fun getUser(id: String): Flow<ResultWrapper<User>>

    fun editUser(
        token: String,
        id: String,
        name: String,
        email: String,
        phoneNumber: String,
        photo: File? = null
    //): Flow<ResultWrapper<EditUserResponse>>
    ): Flow<ResultWrapper<Response<Data>>>

    fun deleteUser(id: String): Flow<ResultWrapper<DeleteUserResponse>>
}