package com.example.starter.repository

import com.example.starter.model.response.YoutubeResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by sangyoon on 2021/07/27
 */
interface ExampleRepository {

    fun exampleFun(playListId: String, apiKey: String): Flow<YoutubeResponse>

}