package com.project.skypass.utils

fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")