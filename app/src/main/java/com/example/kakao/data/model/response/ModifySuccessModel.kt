package com.example.kakao.data.model.response

enum class ModifySuccessModel(val isFavorite: Boolean) {
    SAVE_SUCCESS(true),
    DELETE_SUCCESS(false),
}