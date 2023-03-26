package com.example.kakao.uilayer.model

data class ItemImageUiState(
    val thumbnailUrl: String? = null,
    val date: String? = null,
    val time: String? = null,
    var isFavorite: Boolean = false
)