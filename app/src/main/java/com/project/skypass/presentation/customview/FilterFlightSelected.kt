package com.project.skypass.presentation.customview

import com.project.skypass.data.model.FilterFlight

interface FilterFlightSelected {
    fun onFilterSelected(tag: String, filter: FilterFlight)
}