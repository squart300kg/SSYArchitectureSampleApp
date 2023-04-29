package com.example.architecture.data.model.response

enum class ModifySuccessModel(val isFavorite: Boolean) {
    SAVE_SUCCESS(true),
    DELETE_SUCCESS(false),
}