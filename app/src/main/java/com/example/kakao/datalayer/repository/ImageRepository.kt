package com.example.kakao.datalayer.repository

import javax.inject.Inject

class ImageRepository @Inject constructor() {

    // TODO: 프로퍼티로 get을 수행할 수 있도록
    private val remoteImages: List<String> = emptyList()
    private val localImages: List<String> = emptyList()

    fun addFavoriteImageInLocal(id: String) {}
    fun deleteFavoriteImageInLocal(id: String) {}
}