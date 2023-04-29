package com.example.architecture.data.api

import com.example.architecture.data.model.response.VideoResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {

    @GET("search/vclip")
    suspend fun fetchVideos(
        @Query("query") keyWord: String,
        @Query("sort") sortType: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): VideoResponseModel

}