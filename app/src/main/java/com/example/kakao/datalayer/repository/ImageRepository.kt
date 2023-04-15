package com.example.kakao.datalayer.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.kakao.datalayer.api.KakaoApi
import com.example.kakao.datalayer.datasource.END_PAGING_COUNT
import com.example.kakao.datalayer.datasource.LocalImageDataSource
import com.example.kakao.datalayer.datasource.RemoteImagePagingSource
import com.example.kakao.uilayer.model.ItemImageUiState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import java.io.IOException
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

    fun localImages() = localImageDataSource.fetchImages()

    fun saveImageToLocal(imageUiState: ItemImageUiState)
        = localImageDataSource.saveImage(imageUiState)

    fun deleteImageToLocal(imageUiState: ItemImageUiState)
        = localImageDataSource.deleteImage(imageUiState)

}