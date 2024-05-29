package com.project.skypass.data.datasource.user

import com.project.skypass.data.source.local.pref.UserPreference

class UserDataSourceImpl (private val userPreference: UserPreference) : UserDataSource {
    override fun isUsingDarkMode(): Boolean {
        return userPreference.isUsingDarkMode()
    }

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        userPreference.setUsingDarkMode(isUsingDarkMode)
    }
}