package com.virgen.peregrina.demo.util

import com.virgen.peregrina.demo.data.model.UserModel
import org.apache.commons.logging.LogFactory
import java.util.*

inline fun <reified T> getLog() = LogFactory.getLog(T::class.java)

fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    return formatLessThanTen(calendar.get(Calendar.HOUR_OF_DAY)) +
            DOUBLE_POINT + formatLessThanTen(calendar.get(Calendar.MINUTE)) +
            DOUBLE_POINT + formatLessThanTen(calendar.get(Calendar.SECOND))
}

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    return formatLessThanTen(calendar.get(Calendar.DAY_OF_MONTH)) +
            SLASH + formatLessThanTen(calendar.get(Calendar.MONTH)) +
            SLASH + calendar.get(Calendar.YEAR)
}

fun formatLessThanTen(value: Int): String {
    return if (value < 10) "0$value" else "$value"
}