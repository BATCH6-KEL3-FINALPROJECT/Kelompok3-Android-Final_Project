package com.project.skypass.presentation.main

import androidx.lifecycle.ViewModel
import com.project.skypass.data.repository.pref.PrefRepository

class MainViewModel (private val repo: PrefRepository) : ViewModel() {
    fun isUserLoggedIn() = repo.isLogin()
}