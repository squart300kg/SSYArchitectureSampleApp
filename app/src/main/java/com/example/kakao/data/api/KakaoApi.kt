package com.example.kakao.data.api

import com.example.kakao.data.model.response.ImageResponseModel
import com.example.kakao.data.model.response.VideoResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApi {

    @GET("search/image")
    suspend fun fetchImages(
        @Query("query") keyWord: String,
        @Query("sort") sortType: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ImageResponseModel

    @GET("search/vclip")
    suspend fun fetchVideos(
        @Query("query") keyWord: String,
        @Query("sort") sortType: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): VideoResponseModel

}