package com.example.kakao.repository

import com.example.kakao.model.response.YoutubeResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by sangyoon on 2021/07/27
 */
interface ExampleRepository {

    fun exampleFun(playListId: String, apiKey: String): Flow<YoutubeResponse>

}