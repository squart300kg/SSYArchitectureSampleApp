package com.example.kakao.datalayer.datasource

import android.util.Log
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
//                    Log.i("timeMethodTest", "basic : ${image.dateTime}")
//                    Log.i("timeMethodTest", "method 1 : ${image.dateTime.extractDate()}")
//                    Log.i("timeMethodTest", "method 2 : ${image.dateTime.extractTime()}")
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
                    Log.i("timeMethodTest", "basic : ${image.dateTime}")
                    Log.i("timeMethodTest", "method 1 : ${image.dateTime.extractDate()}")
                    Log.i("timeMethodTest", "method 2 : ${image.dateTime.extractTime()}")
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
            Log.i("timeMethodTest", "imageApi"+itemImageUiStatesForImageApi.toString())
            Log.i("timeMethodTest", "videoApi"+itemImageUiStatesForVideoApi.toString())

            val itemImageUiStates = (itemImageUiStatesForImageApi + itemImageUiStatesForVideoApi)
                .distinct()
                .sortedWith(compareBy(ItemImageUiState::date, ItemImageUiState::time))
            emit(itemImageUiStates)
        }
    }
}