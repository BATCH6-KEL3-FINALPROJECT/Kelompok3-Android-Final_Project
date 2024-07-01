package com.project.skypass.core

import androidx.lifecycle.ViewModel
import com.project.skypass.data.repository.pref.PrefRepository

class BaseViewModel(private val prefRepository: PrefRepository) : ViewModel() {
    fun clearSession() {
        prefRepository.clearAll()
    }
}
