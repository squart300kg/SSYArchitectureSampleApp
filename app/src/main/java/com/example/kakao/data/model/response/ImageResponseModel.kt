package com.example.kakao.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.example.kakao.data.model.response.Documents.ImageDocument

data class ImageResponseModel(

    @Expose
    @SerializedName("meta")
    val meta: Meta,
    @Expose
    @SerializedName("documents")
    val documents: List<ImageDocument>

)
