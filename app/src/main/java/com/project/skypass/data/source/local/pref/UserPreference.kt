package com.project.skypass.data.source.local.pref

interface UserPreference {
    fun isFirstRun(): Boolean
    fun setFirstRun(isFirstRun: Boolean)

    fun isLogin(): Boolean
    fun setLogin(isLogin: Boolean)

    fun getToken(): String
    fun setToken(token: String)

    fun isUsingDarkMode() : Boolean
    fun setUsingDarkMode(isUsingDarkMode : Boolean)
}