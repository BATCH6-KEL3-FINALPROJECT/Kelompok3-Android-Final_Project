package com.project.skypass.presentation.customview

import com.project.skypass.data.model.DateCalendar
import com.project.skypass.data.model.Search
import com.project.skypass.data.model.SeatClass

interface DataSelection {
    fun onDateSelected(tag: String, date: DateCalendar)
    fun onPassengerSelected(tag: String, passenger: String)
    fun onSeatClassSelected(tag: String, seatClass: SeatClass)
    fun onTripSelected(tag: String, trip: Search)
}