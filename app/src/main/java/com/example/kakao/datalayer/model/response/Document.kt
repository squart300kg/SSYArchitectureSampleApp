package com.example.kakao.datalayer.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

sealed interface Documents {
    val thumbnail: String
    val dateTime: String

    data class ImageDocument(
        @Expose
        @SerializedName("thumbnail_url")
        override val thumbnail: String,
        @Expose
        @SerializedName("datetime")
        override val dateTime: String,
        @Expose
        @SerializedName("collection")
        val collection: String,
        @Expose
        @SerializedName("image_url")
        val imageUrl: String,
        @Expose
        @SerializedName("width")
        val width: Int,
        @Expose
        @SerializedName("height")
        val height: Int,
        @Expose
        @SerializedName("display_sitename")
        val displaySitename: String,
        @Expose
        @SerializedName("doc_url")
        val docUrl: String
    ): Documents

    data class VideoDocument(
        @Expose
        @SerializedName("thumbnail")
        override val thumbnail: String,
        @Expose
        @SerializedName("datetime")
        override val dateTime: String,
        @Expose
        @SerializedName("title")
        val title: String,
        @Expose
        @SerializedName("url")
        val url: String,
        @Expose
        @SerializedName("playTime")
        val playTime: Int,
        @Expose
        @SerializedName("author")
        val author: String
    ) : Documents
}
