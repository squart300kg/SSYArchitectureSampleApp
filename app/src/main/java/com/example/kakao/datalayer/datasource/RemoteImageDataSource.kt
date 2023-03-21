package com.example.kakao.datalayer.datasource

import com.example.kakao.datalayer.api.KakaoApi
import com.example.kakao.datalayer.model.SortType
import com.example.kakao.datalayer.model.response.ImageResponseModel
import com.example.kakao.datalayer.model.response.VideoResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteImageDataSource @Inject constructor(
    private val kakaoApi: KakaoApi,
) {

    fun fetchImages(keyWord: String): Flow<ImageResponseModel> {
        return flow {
            emit(kakaoApi.fetchImages(keyWord = keyWord, sortType = SortType.RECENCY.value))
        }
    }

    fun fetchVideos(keyWord: String): Flow<VideoResponseModel> {
        return flow {
            emit(kakaoApi.fetchVideos(keyWord = keyWord, sortType = SortType.RECENCY.value))
        }
    }
}