package com.project.skypass.data.repository.profile

import com.project.skypass.data.datasource.profile.ProfileDataSource
import com.project.skypass.data.model.User

class ProfileRepositoryImpl(private val dataSource: ProfileDataSource) : ProfileRepository {
    override fun getProfile(): List<User> {
        return dataSource.getProfile()
    }
}