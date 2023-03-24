package com.example.kakao.datalayer.repository

import android.content.Context
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.kakao.datalayer.api.KakaoApi
import com.example.kakao.datalayer.datasource.END_PAGING_COUNT
import com.example.kakao.datalayer.datasource.LocalImageDataSource
import com.example.kakao.datalayer.datasource.RemoteImagePagingSource
import com.example.kakao.uilayer.model.ItemImageUiState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
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

    fun localImages(): Flow<List<ItemImageUiState>> {
        return localImageDataSource.fetchImages().map {
            Log.i("updateTest", "in repo fetch local img : "+it.toString())
            it
        }
    }

    // TODO: 반환형 지우는거 고려
    fun saveImageToLocal(imageUiState: ItemImageUiState): Flow<ItemImageUiState> {
        return localImageDataSource.saveImage(imageUiState).map {
            Log.i("updateTest", "in repo save result : "+it.toString())
            Log.i("updateTest", "in repo save count : "+localImageDataSource.fetchImages2().count().toString())
            it
        }
    }

    fun deleteImageToLocal(imageUiState: ItemImageUiState): Flow<ItemImageUiState> {
        return localImageDataSource.deleteImage(imageUiState).map {
            Log.i("updateTest", "in repo dele result : "+it.toString())
            Log.i("updateTest", "in repo dele count : "+localImageDataSource.fetchImages2().count().toString())
            it
        }
    }


}