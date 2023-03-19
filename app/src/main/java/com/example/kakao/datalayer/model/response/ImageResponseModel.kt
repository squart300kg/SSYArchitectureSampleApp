package com.example.kakao.datalayer.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageResponseModel(

    @Expose
    @SerializedName("meta")
    val meta: Meta,
    @Expose
    @SerializedName("documents")
    val documents: Documents

) {

    data class Documents(
        @Expose
        @SerializedName("collection")
        val collection: String,
        @Expose
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String,
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
        val docUrl: String,
        @Expose
        @SerializedName("datetime")
        val datetime: String
    )
}
