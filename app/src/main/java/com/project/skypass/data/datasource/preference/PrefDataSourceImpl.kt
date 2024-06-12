package com.project.skypass.data.datasource.preference

import com.project.skypass.data.source.local.pref.UserPreference

class PrefDataSourceImpl(private val userPreference: UserPreference): PrefDataSource {
    override fun isFirstRun(): Boolean {
        return userPreference.isFirstRun()
    }

    override fun setFirstRun(isFirstRun: Boolean) {
        return userPreference.setFirstRun(isFirstRun)
    }

    override fun isLogin(): Boolean {
        return userPreference.isLogin()
    }

    override fun setLogin(isLogin: Boolean) {
        return userPreference.setLogin(isLogin)
    }

    override fun getUserID(): String {
        return userPreference.getUserID()
    }

    override fun setUserID(userID: String) {
        return userPreference.setUserID(userID)
    }

    override fun getToken(): String {
        return userPreference.getToken()
    }

    override fun setToken(token: String) {
        return userPreference.setToken(token)
    }
    override fun clearAll() {
        userPreference.clearAll()
    }
}