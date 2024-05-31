package com.project.skypass.data.repository.user

import com.project.skypass.data.datasource.user.UserDataSource

class UserRepositoryImpl (private val dataSource: UserDataSource) : UserRepository {
    override fun isUsingDarkMode(): Boolean {
        return dataSource.isUsingDarkMode()
    }

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        dataSource.setUsingDarkMode(isUsingDarkMode)
    }
}