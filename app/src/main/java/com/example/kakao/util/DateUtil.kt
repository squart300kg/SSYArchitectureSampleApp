package com.example.kakao.util

import java.text.SimpleDateFormat
import java.util.*

fun String.extractDate() = this willConvertTo "yyyy년 MM월 dd일"

fun String.extractTime() = this willConvertTo "HH시 mm분 ss초"

private infix fun String.willConvertTo(inputPattern: String): String {
    val inputDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.KOREA).parse(this)
    val outputDateFormat = SimpleDateFormat(inputPattern, Locale.KOREA)
    return outputDateFormat.format(inputDate)
}