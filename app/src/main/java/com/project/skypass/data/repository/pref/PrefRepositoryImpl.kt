package com.project.skypass.data.repository.pref

import com.project.skypass.data.datasource.preference.PrefDataSource
import com.project.skypass.utils.decodeJWT

class PrefRepositoryImpl(private val dataSource: PrefDataSource): PrefRepository {
    override fun isFirstRun(): Boolean {
        return dataSource.isFirstRun()
    }

    override fun setFirstRun(isFirstRun: Boolean) {
        return dataSource.setFirstRun(isFirstRun)
    }

    override fun isLogin(): Boolean {
        return dataSource.isLogin()
    }

    override fun setLogin(isLogin: Boolean) {
        return dataSource.setLogin(isLogin)
    }

    override fun getToken(): String {
        return dataSource.getToken()
    }

    override fun setToken(token: String) {
        return dataSource.setToken(token)
    }

    override fun getUserID(): String {
        return dataSource.getUserID()
    }

    override fun setUserID(userID: String) {
        val userIdEncode = decodeJWT(userID)
        return dataSource.setUserID(userIdEncode)
    }
}