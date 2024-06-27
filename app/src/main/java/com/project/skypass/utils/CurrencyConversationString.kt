package com.project.skypass.utils

import java.text.NumberFormat
import java.util.Locale

fun String?.stringToCurrency(
    language: String,
    country: String,
): String? {
    return try {
        val localeID = Locale(language, country)
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        val amount = this?.toDoubleOrNull()
        if (amount != null) {
            numberFormat.format(amount).toString().replace("Rp", "Rp ")
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

fun String?.toIndonesianFormat() = this.stringToCurrency("in", "ID")
