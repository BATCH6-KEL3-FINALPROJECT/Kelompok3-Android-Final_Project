package com.project.skypass.presentation.customview

interface DataSelection {
    fun onDateSelected(tag: String, date: String)
    fun onPassengerSelected(tag: String, passenger: String)
}