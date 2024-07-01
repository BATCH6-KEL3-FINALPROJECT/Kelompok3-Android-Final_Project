package com.project.skypass.presentation.splashscreen

import androidx.lifecycle.ViewModel
import com.project.skypass.data.repository.pref.PrefRepository

class SplashViewModel(private val repository: PrefRepository) : ViewModel() {
    fun isFirstRun(): Boolean {
        return repository.isFirstRun()
    }
}
