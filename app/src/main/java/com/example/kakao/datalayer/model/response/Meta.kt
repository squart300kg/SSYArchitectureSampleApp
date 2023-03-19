package com.example.kakao.datalayer.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
