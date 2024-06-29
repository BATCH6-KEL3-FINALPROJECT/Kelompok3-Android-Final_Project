package com.project.skypass.presentation.flight.result

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.project.skypass.data.model.Flight
import com.project.skypass.data.repository.pref.PrefRepository

class FlightResultViewModel(
    // private val extras: Bundle?,
    private val prefRepository: PrefRepository,
) : ViewModel() {
    var flight = Bundle().getParcelable<Flight>(FlightResultActivity.EXTRAS)

    fun isLogin(): Boolean {
        return prefRepository.isLogin()
    }
}
