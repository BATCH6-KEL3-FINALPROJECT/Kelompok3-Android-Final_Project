package com.project.skypass.data.repository.user

import com.project.skypass.data.datasource.user.UserDataSource
import com.project.skypass.data.mapper.toUser
import com.project.skypass.data.model.User
import com.project.skypass.data.source.network.model.user.edituser.EditUserResponse
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow
import java.io.File

class UserRepositoryImpl (private val dataSource: UserDataSource) : UserRepository {
    override fun isUsingDarkMode(): Boolean {
        return dataSource.isUsingDarkMode()
    }

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        dataSource.setUsingDarkMode(isUsingDarkMode)
    }

    override fun getUser(id: String): Flow<ResultWrapper<User>> {
        return proceedFlow {
            dataSource.getUser(id).data?.user.toUser()
        }
    }

    override fun editUser(
        token: String,
        id: String,
        name: String?,
        email: String?,
        phoneNumber: String?,
        password: String?,
        photo: File?
    ): Flow<ResultWrapper<EditUserResponse>> {
        return proceedFlow {
            dataSource.editUser(token, id, name, email, phoneNumber, password, photo)
        }
    }
}