package com.example.architecture.util

import java.text.SimpleDateFormat
import java.util.*

infix fun String.convertFormatTo(inputPattern: String): String {
    val inputDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.KOREA).parse(this)
    val outputDateFormat = SimpleDateFormat(inputPattern, Locale.KOREA)
    return outputDateFormat.format(inputDate)
}