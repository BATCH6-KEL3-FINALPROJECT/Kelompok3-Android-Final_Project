package com.project.skypass.data.repository.profile

import com.project.skypass.data.model.User

interface ProfileRepository {
    fun getProfile(): List<User>
}
