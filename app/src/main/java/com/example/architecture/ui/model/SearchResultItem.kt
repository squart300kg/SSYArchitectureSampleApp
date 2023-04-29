package com.example.architecture.ui.model

data class SearchResultItem(
    val thumbnailUrl: String? = null,
    val date: String? = null,
    val time: String? = null,
    val title: String? = null,
    val playTime: Int? = null,
    val author: String? = null,
    var isFavorite: Boolean = false
)