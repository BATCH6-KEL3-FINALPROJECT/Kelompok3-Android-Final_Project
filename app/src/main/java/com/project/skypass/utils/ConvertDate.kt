package com.project.skypass.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun convertDateFormat(inputDate: String): String {
    val inputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale("id", "ID"))

    return try {
        val date = inputFormat.parse(inputDate)
        date?.let {
            outputFormat.format(it)
        } ?: inputDate
    } catch (e: Exception) {
        inputDate
    }
}