package com.example.architecture.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoResponseModel(

    @Expose
    @SerializedName("meta")
    val meta: Meta,
    @Expose
    @SerializedName("documents")
    val documents: List<VideoDocument>

) {
    data class Meta(
        @Expose
        @SerializedName("total_count")
        val totalCount: Int,
        @Expose
        @SerializedName("pageable_count")
        val pageableCount: Int,
        @Expose
        @SerializedName("is_end")
        val isEnd: Boolean,
    )

    data class VideoDocument(
        @Expose
        @SerializedName("thumbnail")
        val thumbnail: String,
        @Expose
        @SerializedName("datetime")
        val dateTime: String,
        @Expose
        @SerializedName("title")
        val title: String,
        @Expose
        @SerializedName("url")
        val url: String,
        @Expose
        @SerializedName("play_time")
        val playTime: Int,
        @Expose
        @SerializedName("author")
        val author: String
    )

}
