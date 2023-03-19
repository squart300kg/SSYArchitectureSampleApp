package com.example.kakao.datalayer.api

import com.example.kakao.datalayer.model.response.ImageResponseModel
import com.example.kakao.datalayer.model.response.VideoResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApi {

    @GET("search/vclip")
    suspend fun fetchVideos(@Query("query") keyWord: String): VideoResponseModel

    @GET("search/image")
    suspend fun fetchImages(@Query("query") keyWord: String): ImageResponseModel

}