package com.project.skypass.data.source.local.pref

import android.content.SharedPreferences
import android.util.Log
import com.project.skypass.utils.SharedPreferenceUtils.set

class UserPreferenceImpl(private val pref: SharedPreferences): UserPreference {
    override fun isLogin(): Boolean {
        return pref.getBoolean(KEY_IS_LOGIN, false)
    }

    override fun getToken(): String {
        return pref.getString(KEY_TOKEN, "").toString()
    }

    override fun setToken(token: String) {
        pref[KEY_TOKEN] = token
        Log.e(TAG, "Saving value user pref $token")
    }

    override fun isFirstRun(): Boolean {
        return pref.getBoolean(KEY_IS_FIRST_RUN, false)
    }

    override fun setLogin(isLogin: Boolean) {
        pref[KEY_IS_LOGIN] = isLogin
        Log.e(TAG, "Saving value user pref $isLogin")
    }

    override fun setFirstRun(isFirstRun: Boolean) {
        pref[KEY_IS_FIRST_RUN] = isFirstRun
        Log.e(TAG, "Saving value user pref $isFirstRun")
    }
    override fun isUsingDarkMode(): Boolean = pref.getBoolean(KEY_IS_USING_DARK_MODE, false)

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        pref[KEY_IS_USING_DARK_MODE] = isUsingDarkMode
    }

    companion object {
        const val TAG = "User Preferences"
        const val PREF_NANE = "flightapp-pref"
        const val KEY_IS_FIRST_RUN = "KEY_IS_FIRST_RUN"
        const val KEY_IS_LOGIN = "KEY_IS_LOGIN"
        const val KEY_TOKEN = "KEY_TOKEN"
        const val KEY_IS_USING_DARK_MODE = "KEY_IS_USING_DARK_MODE"
    }
}