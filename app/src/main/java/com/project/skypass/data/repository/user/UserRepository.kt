package com.project.skypass.data.repository.user

interface UserRepository {

    fun isUsingDarkMode() : Boolean

    fun setUsingDarkMode(isUsingDarkMode : Boolean)
}