package com.example.kakao.datalayer.repository

import com.example.kakao.datalayer.datasource.LocalImageDataSource
import com.example.kakao.datalayer.datasource.RemoteImageDataSource
import javax.inject.Inject

class ImageRepository @Inject constructor(
    remoteImageDataSource: RemoteImageDataSource,
    localImageDataSource: LocalImageDataSource,
) {

    // TODO: 프로퍼티로 get을 수행할 수 있도록
    private val remoteImages: List<String> = emptyList()
    private val localImages: List<String> = emptyList()

    fun addFavoriteImageInLocal(id: String) {}
    fun deleteFavoriteImageInLocal(id: String) {}
}