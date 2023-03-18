package com.example.starter.repository

import com.example.starter.api.SampleApi
import com.example.starter.model.response.YoutubeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by sangyoon on 2021/07/27
 */
class YoutubeRepositoryImp(
    private val youtubeApi: SampleApi
): ExampleRepository {

    override fun exampleFun(playListId: String, apiKey: String): Flow<YoutubeResponse> {
        return flow {
            val data = youtubeApi.getFreeLectures(
                playListId = playListId,
                key = apiKey)
            emit(data)
        }
    }

}