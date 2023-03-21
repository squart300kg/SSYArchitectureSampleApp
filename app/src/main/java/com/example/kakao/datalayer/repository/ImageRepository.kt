package com.example.kakao.datalayer.repository

import com.example.kakao.datalayer.datasource.LocalImageDataSource
import com.example.kakao.datalayer.datasource.RemoteImageDataSource
import com.example.kakao.uilayer.model.ItemImageUiState
import com.example.kakao.util.extractDate
import com.example.kakao.util.extractTime
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ImageRepository @Inject constructor(
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
                            date = document.dateTime.extractDate(),
                            time = document.dateTime.extractTime(),
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
                            date = document.dateTime.extractDate(),
                            time = document.dateTime.extractTime(),
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

    fun addFavoriteImageInLocal(id: String) {}
    fun deleteFavoriteImageInLocal(id: String) {}

}