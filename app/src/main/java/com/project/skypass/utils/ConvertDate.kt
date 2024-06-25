package com.project.skypass.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun convertDateFormat(inputDate: String): String {
    /*val inputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("en", "US"))
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale("en", "US"))*/
    val defaultLocale = Locale.getDefault()
    val inputFormat = SimpleDateFormat("dd MMMM yyyy", defaultLocale)
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", defaultLocale)

    return try {
        val date = inputFormat.parse(inputDate)
        date?.let {
            outputFormat.format(it)
        } ?: inputDate
    } catch (e: Exception) {
        inputDate
    }
}
fun convertDateNotification(apiString: String): String{

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    return try {
        val parsedDate = inputFormat.parse(apiString)
        parsedDate?.let {
            outputFormat.format(parsedDate)
        }?: apiString
    } catch (e: Exception) {
        apiString
    }
}

fun convertDateMouth(apiString: String): String{

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    return try {
        val parsedDate = inputFormat.parse(apiString)
        parsedDate?.let {
            outputFormat.format(parsedDate)
        }?: apiString
    } catch (e: Exception) {
        apiString
    }
}

fun convertDateText(apiString: String): String{

    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    return try {
        val parsedDate = inputFormat.parse(apiString)
        parsedDate?.let {
            outputFormat.format(parsedDate)
        }?: apiString
    } catch (e: Exception) {
        apiString
    }
}

fun convertDateTextApi(apiString: String): String{

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    return try {
        val parsedDate = inputFormat.parse(apiString)
        parsedDate?.let {
            outputFormat.format(parsedDate)
        }?: apiString
    } catch (e: Exception) {
        apiString
    }
}


fun convertFlightDetail(inputDate: String): String {
    /*val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale("id", "ID"))
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale("id", "ID"))*/
    val defaultLocale = Locale.getDefault()
    val inputFormat = SimpleDateFormat("dd-MM-yyyy", defaultLocale)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", defaultLocale)

    return try {
        val date = inputFormat.parse(inputDate)
        date?.let {
            outputFormat.format(it)
        } ?: inputDate
    } catch (e: Exception) {
        inputDate
    }
}

fun convertDateCalendar(inputDate: String): String {
    val defaultLocale = Locale.getDefault()
    val inputFormat = SimpleDateFormat("dd MMMM yyyy", defaultLocale)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", defaultLocale)
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