package com.example.kakao.datalayer.datasource

import android.util.Log
import com.example.kakao.datalayer.api.KakaoApi
import com.example.kakao.datalayer.model.SortType
import com.example.kakao.datalayer.model.request.KakaoRequestModel
import com.example.kakao.di.IoDispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class RemoteImageDataSource @Inject constructor(
    private val kakaoApi: KakaoApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchImages(keyWord: String) {
        withContext(ioDispatcher) {
            val images = async {
                kakaoApi.fetchImages(keyWord = keyWord)
            }
            val videos = async {
                kakaoApi.fetchVideos(keyWord = keyWord)
            }

            Log.i("apiTest", "iamges : " + images.toString())
            Log.i("apiTest", "vidoes : " + videos.toString())
        }
    }
}