package com.project.skypass.utils

import java.text.NumberFormat
import java.util.Locale

fun Int?.integerToCurrency(
    language: String,
    country: String,
): String? {
    return try {
        val localeID = Locale(language, country)
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.format(this).toString().replace("Rp", "Rp ")
    } catch (e: Exception) {
        null
    }
}

fun Int?.toIndonesianFormat() = this.integerToCurrency("in", "ID")
