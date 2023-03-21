package com.example.kakao.datalayer.api

import com.example.kakao.datalayer.model.SortType
import com.example.kakao.datalayer.model.response.ImageResponseModel
import com.example.kakao.datalayer.model.response.VideoResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApi {

    // TODO: PATH로 줄이기
    @GET("search/image")
    suspend fun fetchImages(@Query("query") keyWord: String, @Query("sort") sortType: String): ImageResponseModel

    @GET("search/vclip")
    suspend fun fetchVideos(@Query("query") keyWord: String, @Query("sort") sortType: String): VideoResponseModel

}