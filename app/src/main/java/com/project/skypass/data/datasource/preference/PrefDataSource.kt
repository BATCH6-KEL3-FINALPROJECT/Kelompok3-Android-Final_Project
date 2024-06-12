package com.project.skypass.data.datasource.preference

interface PrefDataSource {
    fun isFirstRun(): Boolean
    fun setFirstRun(isFirstRun: Boolean)

    fun isLogin(): Boolean
    fun setLogin(isLogin: Boolean)

    fun getUserID(): String
    fun setUserID(userID: String)

    fun getToken(): String
    fun setToken(token: String)

}