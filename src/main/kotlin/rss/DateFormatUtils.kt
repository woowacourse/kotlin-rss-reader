package rss

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

private val formatters =
    listOf(
        DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH),
        DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH),
    )

fun parseSystemDateTime(dateString: String): LocalDateTime {
    formatters.forEach { formatter ->
        try {
            return ZonedDateTime.parse(dateString, formatter).toSystemTime()
        } catch (e: DateTimeParseException) {
            return@forEach
        }
    }
    error("DateString($dateString) can't be parsed")
}

fun formatLocalDateTime(
    dateTime: LocalDateTime,
    pattern: String = "yyyy-MM-dd HH:mm:ss",
): String {
    return dateTime.format(DateTimeFormatter.ofPattern(pattern))
}

private fun ZonedDateTime.toSystemTime(): LocalDateTime {
    return withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
}
