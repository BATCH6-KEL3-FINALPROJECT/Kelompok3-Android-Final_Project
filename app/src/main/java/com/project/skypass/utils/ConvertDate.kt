package com.project.skypass.utils

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun convertDateFormat(inputDate: String): String {
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

fun convertDestinationFavorite(inputDate: String): String {
    val defaultLocale = Locale.getDefault()
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", defaultLocale)
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

fun convertDateNotification(apiString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    return try {
        val parsedDate = inputFormat.parse(apiString)
        parsedDate?.let {
            outputFormat.format(parsedDate)
        } ?: apiString
    } catch (e: Exception) {
        apiString
    }
}

fun convertDateMouth(apiString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    return try {
        val parsedDate = inputFormat.parse(apiString)
        parsedDate?.let {
            outputFormat.format(parsedDate)
        } ?: apiString
    } catch (e: Exception) {
        apiString
    }
}

fun convertDateText(apiString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    return try {
        val parsedDate = inputFormat.parse(apiString)
        parsedDate?.let {
            outputFormat.format(parsedDate)
        } ?: apiString
    } catch (e: Exception) {
        apiString
    }
}

fun convertDateTextApi(apiString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    return try {
        val parsedDate = inputFormat.parse(apiString)
        parsedDate?.let {
            outputFormat.format(parsedDate)
        } ?: apiString
    } catch (e: Exception) {
        apiString
    }
}

fun convertFlightDetail(inputDate: String): String {
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

fun formatDatesDestinationFavorite(
    departureDate: String,
    returnDate: String,
): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd", Locale.getDefault())
    val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    val departureDateParsed = inputFormat.parse(departureDate)
    val returnDateParsed = inputFormat.parse(returnDate)

    val departureDay = departureDateParsed?.let { outputFormat.format(it) }
    val returnDay = returnDateParsed?.let { outputFormat.format(it) }
    val monthYear = returnDateParsed?.let { monthYearFormat.format(it) }

    return "$departureDay - $returnDay $monthYear"
}

fun formatDateNotification(inputDate: String): String {
    val now = LocalDateTime.now(ZoneId.of("UTC"))
    val time = LocalDateTime.parse(inputDate, DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("UTC")))
    val duration = Duration.between(time, now)

    return when {
        duration.toMinutes() < 1 -> "Baru saja"
        duration.toMinutes() < 60 -> "${duration.toMinutes()} menit yang lalu"
        duration.toHours() < 24 -> "${duration.toHours()} jam yang lalu"
        duration.toDays() < 7 -> "${duration.toDays()} hari yang lalu"
        duration.toDays() < 30 -> "${duration.toDays() / 7} minggu yang lalu"
        duration.toDays() < 365 -> "${duration.toDays() / 30} bulan yang lalu"
        else -> "${duration.toDays() / 365} tahun yang lalu"
    }
}