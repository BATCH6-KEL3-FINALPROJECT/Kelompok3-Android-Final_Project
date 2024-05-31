package com.project.skypass.data.datasource.user

interface UserDataSource {

    fun isUsingDarkMode() : Boolean

    fun setUsingDarkMode(isUsingDarkMode : Boolean)
}