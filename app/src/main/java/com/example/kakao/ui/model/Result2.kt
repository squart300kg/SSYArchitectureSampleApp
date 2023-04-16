package com.example.kakao.ui.model

sealed interface Result2<out T> {
    data class Success<T>(val data: T) : Result2<T>
    data class Error(val exception: Throwable) : Result2<Nothing>
    object Loading : Result2<Nothing>
}