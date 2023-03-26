package com.example.kakao.datalayer.api

import com.example.kakao.datalayer.model.response.ImageResponseModel
import com.example.kakao.datalayer.model.response.VideoResponseModel
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