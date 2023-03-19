package com.example.kakao.datalayer.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoResponseModel(

    @Expose
    @SerializedName("meta")
    val meta: Meta,
    @Expose
    @SerializedName("documents")
    val documents: Documents

) {
    data class Documents(
        @Expose
        @SerializedName("title")
        val title: String,
        @Expose
        @SerializedName("url")
        val url: String,
        @Expose
        @SerializedName("dateTime")
        val dateTime: String,
        @Expose
        @SerializedName("playTime")
        val playTime: Int,
        @Expose
        @SerializedName("thumbnail")
        val thumbnail: String,
        @Expose
        @SerializedName("author")
        val author: String
    )
}
