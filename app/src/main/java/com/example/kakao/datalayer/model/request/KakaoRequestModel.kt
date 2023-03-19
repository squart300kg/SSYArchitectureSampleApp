package com.example.kakao.datalayer.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// TODO: 사용 안할경우 지우기
data class KakaoRequestModel(
    val query: String,
    val sort: String,
    val page: Int,
    val size: Int
)
