package com.example.kakao.datalayer.datasource

import com.example.kakao.datalayer.api.KakaoApi
import com.example.kakao.uilayer.model.ItemImageUiState
import com.example.kakao.util.extractDate
import com.example.kakao.util.extractTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteImageDataSource @Inject constructor(
    private val kakaoApi: KakaoApi,
) {

    // TODO: 서버 timezone표시방법 고민!!
    fun fetchImages(keyWord: String): Flow<List<ItemImageUiState>> {
        return flow {
            val itemImageUiStatesForImageApi = mutableListOf<ItemImageUiState>().apply {
                kakaoApi.fetchImages(keyWord = keyWord).documents.forEach { image ->
                    add(
                        ItemImageUiState(
                            thumbnailUrl = image.thumbnailUrl,
                            date = image.dateTime.extractDate(),
                            time = image.dateTime.extractTime(),
                            isFavorite = false
                        )
                    )
                }
            }
            val itemImageUiStatesForVideoApi = mutableListOf<ItemImageUiState>().apply {
                kakaoApi.fetchVideos(keyWord = keyWord).documents.forEach { image ->
                    add(
                        ItemImageUiState(
                            thumbnailUrl = image.thumbnail,
                            date = image.dateTime.extractDate(),
                            time = image.dateTime.extractTime(),
                            isFavorite = false
                        )
                    )
                }
            }

            val itemImageUiStates = (itemImageUiStatesForImageApi + itemImageUiStatesForVideoApi)
                .distinct()
                .sortedWith(compareByDescending(ItemImageUiState::date).thenByDescending(ItemImageUiState::time))
            emit(itemImageUiStates)
        }
    }
}