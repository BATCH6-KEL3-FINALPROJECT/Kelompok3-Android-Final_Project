package com.project.skypass.utils

import java.text.SimpleDateFormat
import java.util.Date
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

fun convertFlightDetail(inputDate: String): String {
    val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale("id", "ID"))
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale("id", "ID"))

    return try {
        val date = inputFormat.parse(inputDate)
        date?.let {
            outputFormat.format(it)
        } ?: inputDate
    } catch (e: Exception) {
        inputDate
    }
}

fun convertMinutesToHours(minutes: Int): Pair<Int, Int> {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    return Pair(hours, remainingMinutes)
}

fun orderDate(): String {
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return formatter.format(Date())
}


fun formatDatesDestinationFavorite(departureDate: String, returnDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd", Locale.getDefault())
    val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    val departureDateParsed = inputFormat.parse(departureDate)
    val returnDateParsed = inputFormat.parse(returnDate)

    val departureDay = outputFormat.format(departureDateParsed)
    val returnDay = outputFormat.format(returnDateParsed)
    val monthYear = monthYearFormat.format(returnDateParsed)

    return "$departureDay - $returnDay $monthYear"
}