package com.kitahara.cardsapitest.domain

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

fun formatTime(dateStr: String): String? {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.ENGLISH)
    return try {
        val zonedDateTime = ZonedDateTime.parse(dateStr, formatter)
        val formattedDate: String = zonedDateTime.format(DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH))
        formattedDate
    } catch (e: DateTimeParseException) {
        null
    }
}