package com.project.skypass.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.project.skypass.data.repository.pref.PrefRepository

class OnboardingViewModel(private val repository: PrefRepository) : ViewModel() {
    fun setFirstRun(isFirstRun: Boolean) {
        return repository.setFirstRun(isFirstRun)
    }
}
