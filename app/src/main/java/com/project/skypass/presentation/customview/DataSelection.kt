package com.project.skypass.presentation.customview

import com.project.skypass.data.model.SeatClass

interface DataSelection {
    fun onDateSelected(tag: String, date: String)
    fun onPassengerSelected(tag: String, passenger: String)
    fun onSeatClassSelected(tag: String, seatClass: SeatClass)
}