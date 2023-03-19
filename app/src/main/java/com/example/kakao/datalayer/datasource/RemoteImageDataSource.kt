package com.example.kakao.datalayer.datasource

import android.util.Log
import com.example.kakao.datalayer.api.KakaoApi
import com.example.kakao.datalayer.model.SortType
import com.example.kakao.datalayer.model.request.KakaoRequestModel
import com.example.kakao.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteImageDataSource @Inject constructor(
    private val kakaoApi: KakaoApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchImages(keyWord: String) {

        Log.i("key", keyWord)
        withContext(ioDispatcher) {
            val requestBody = KakaoRequestModel(
                query = keyWord,
                sort = SortType.RECENCY.value,
                page = 1,
                size = 10
            )
            val images = kakaoApi.fetchImages(keyWord = keyWord)
            val videos = kakaoApi.fetchVideos(keyWord = keyWord)

            Log.i("apiTest", "iamges : " + images.toString())
            Log.i("apiTest", "vidoes : " + videos.toString())
        }
    }
}