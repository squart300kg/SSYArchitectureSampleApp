package com.example.kakao.datalayer.repository

import android.util.Log
import com.example.kakao.datalayer.datasource.LocalImageDataSource
import com.example.kakao.datalayer.datasource.RemoteImageDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val remoteImageDataSource: RemoteImageDataSource,
    private val localImageDataSource: LocalImageDataSource,
) {

    // TODO: 프로퍼티로 get을 수행할 수 있도록
    val remoteImages: List<String> = emptyList()
    val localImages: List<String> = emptyList()

    fun addFavoriteImageInLocal(id: String) {}
    fun deleteFavoriteImageInLocal(id: String) {}

    fun fetchImages(keyWord: String): Flow<String> {
        return flow {
            Log.i("key", keyWord)
            remoteImageDataSource.fetchImages(keyWord)
            emit("Hello")
        }
    }
}