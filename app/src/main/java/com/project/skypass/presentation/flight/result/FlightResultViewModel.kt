package com.project.skypass.presentation.flight.result

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.project.skypass.data.model.Flight

class FlightResultViewModel(
    private val extras: Bundle?,) : ViewModel() {

    var flight = extras?.getParcelable<Flight>(FlightResultActivity.EXTRAS)
}