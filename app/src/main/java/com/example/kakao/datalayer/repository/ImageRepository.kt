package com.example.kakao.datalayer.repository

import android.content.Context
import com.example.kakao.R
import com.example.kakao.datalayer.datasource.LocalImageDataSource
import com.example.kakao.datalayer.datasource.RemoteImageDataSource
import com.example.kakao.uilayer.model.ItemImageUiState
import com.example.kakao.util.convertFormatTo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val remoteImageDataSource: RemoteImageDataSource,
    private val localImageDataSource: LocalImageDataSource,
) {

    fun fetchRemoteImage(keyWord: String): Flow<List<ItemImageUiState>> {
        val itemImageUiStatesForImageApi = remoteImageDataSource.fetchImages(keyWord).map { imageResponseModel ->
            mutableListOf<ItemImageUiState>().apply {
                imageResponseModel.documents.forEach { document ->
                    add(
                        ItemImageUiState(
                            thumbnailUrl = document.thumbnailUrl,
                            date = document.dateTime convertFormatTo context.resources.getString(R.string.searchResultDateFormat),
                            time = document.dateTime convertFormatTo context.resources.getString(R.string.searchResultTimeFormat),
                            isFavorite = false
                        )
                    )
                }
            }
        }
        val itemImageUiStatesForVideoApi = remoteImageDataSource.fetchVideos(keyWord).map { videoResponseModel ->
            mutableListOf<ItemImageUiState>().apply {
                videoResponseModel.documents.forEach { document ->
                    add(
                        ItemImageUiState(
                            thumbnailUrl = document.thumbnail,
                            date = document.dateTime convertFormatTo context.resources.getString(R.string.searchResultDateFormat),
                            time = document.dateTime convertFormatTo context.resources.getString(R.string.searchResultTimeFormat),
                            isFavorite = false
                        )
                    )
                }
            }
        }

        return itemImageUiStatesForImageApi.combine(itemImageUiStatesForVideoApi) { imageApi, videoApi ->
            (imageApi + videoApi)
                .distinctBy(ItemImageUiState::thumbnailUrl)
                .sortedWith(compareByDescending(ItemImageUiState::date).thenByDescending(ItemImageUiState::time))
        }
    }

    val localImages = localImageDataSource.fetchImages()

    fun saveImageToLocal(imageUiState: ItemImageUiState)
        = localImageDataSource.saveImage(imageUiState)

    fun deleteImageToLocal(imageUiState: ItemImageUiState)
        = localImageDataSource.deleteImage(imageUiState)

}