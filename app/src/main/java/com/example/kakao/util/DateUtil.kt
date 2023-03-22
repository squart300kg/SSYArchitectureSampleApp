package com.example.kakao.util

import java.text.SimpleDateFormat
import java.util.*

infix fun String.convertFormatTo(inputPattern: String): String {
    // TODO: 리소스 처리 고민
    val inputDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.KOREA).parse(this)
    val outputDateFormat = SimpleDateFormat(inputPattern, Locale.KOREA)
    return outputDateFormat.format(inputDate)
}