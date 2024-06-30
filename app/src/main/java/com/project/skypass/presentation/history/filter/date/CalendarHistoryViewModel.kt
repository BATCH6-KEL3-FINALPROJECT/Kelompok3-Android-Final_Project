package com.project.skypass.presentation.history.filter.date

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class CalendarHistoryViewModel : ViewModel() {

    private val _selectedDateDeparture = MutableLiveData<LocalDate?>()
    val selectedDateDeparture: LiveData<LocalDate?> get() = _selectedDateDeparture

    private val _selectedDateReturn = MutableLiveData<LocalDate?>()
    val selectedDateReturn: LiveData<LocalDate?> get() = _selectedDateReturn

    fun selectDepartureDate(date: LocalDate) {
        _selectedDateDeparture.value = date
    }

    fun selectReturnDate(date: LocalDate?) {
        _selectedDateReturn.value = date
    }
}