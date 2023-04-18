package com.example.kakao.data.repository

import android.content.Context
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.kakao.data.api.KakaoApi
import com.example.kakao.data.datasource.END_PAGING_COUNT
import com.example.kakao.data.datasource.LocalImageDataSource
import com.example.kakao.data.datasource.RemoteImagePagingSource
import com.example.kakao.ui.model.SearchResultItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val kakaoApi: KakaoApi,
    private val localImageDataSource: LocalImageDataSource,
) {

    fun fetchRemoteImage(keyWord: String) =
        Pager(PagingConfig(
            pageSize = END_PAGING_COUNT,
            enablePlaceholders = false
        )) {
            RemoteImagePagingSource(
                kakaoApi = kakaoApi,
                context = context,
                keyWord = keyWord
            )
        }.flow

    val localImages = localImageDataSource.fetchImages()

    suspend fun saveImageToLocal(imageUiState: SearchResultItem)
        = localImageDataSource.saveImage(imageUiState)

    suspend fun deleteImageToLocal(imageUiState: SearchResultItem)
        = localImageDataSource.deleteImage(imageUiState)

}