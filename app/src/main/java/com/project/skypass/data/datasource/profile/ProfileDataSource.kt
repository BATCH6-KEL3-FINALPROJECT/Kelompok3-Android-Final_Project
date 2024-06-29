package com.project.skypass.data.datasource.profile

import com.project.skypass.data.model.User

interface ProfileDataSource {
    fun getProfile(): List<User>
}
