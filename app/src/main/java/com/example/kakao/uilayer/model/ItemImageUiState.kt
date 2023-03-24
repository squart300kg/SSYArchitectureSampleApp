package com.example.kakao.uilayer.model

data class ItemImageUiState(
    val thumbnailUrl: String? = null,
    val date: String? = null,
    val time: String? = null,
    // TODO: 꼭 이방법밖에 없었는지?
    var isFavorite: Boolean = false
)