package com.example.kakao.datalayer.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.example.kakao.datalayer.model.response.Documents.VideoDocument

data class VideoResponseModel(

    @Expose
    @SerializedName("meta")
    val meta: Meta,
    @Expose
    @SerializedName("documents")
    val documents: List<VideoDocument>

)
