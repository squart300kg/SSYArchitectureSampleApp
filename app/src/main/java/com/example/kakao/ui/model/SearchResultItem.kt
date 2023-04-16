package com.example.kakao.ui.model

data class SearchResultItem(
    val thumbnailUrl: String? = null,
    val date: String? = null,
    val time: String? = null,
    var isFavorite: Boolean = false
)