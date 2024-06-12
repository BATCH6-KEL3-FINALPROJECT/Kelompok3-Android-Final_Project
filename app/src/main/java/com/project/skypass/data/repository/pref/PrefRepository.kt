package com.project.skypass.data.repository.pref

interface PrefRepository {
    fun isFirstRun(): Boolean
    fun setFirstRun(isFirstRun: Boolean)

    fun isLogin(): Boolean
    fun setLogin(isLogin: Boolean)

    fun getToken(): String
    fun setToken(token: String)

    fun getUserID(): String
    fun setUserID(userID: String)
}