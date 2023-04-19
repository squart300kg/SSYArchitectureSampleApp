package com.example.kakao.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kakao.data.api.KakaoApi
import com.example.kakao.data.datasource.END_PAGING_COUNT
import com.example.kakao.data.datasource.LocalSearchResultDataSource
import com.example.kakao.data.datasource.RemoteImagePagingSource
import com.example.kakao.ui.model.SearchResultItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchResultRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val kakaoApi: KakaoApi,
    private val localSearchResultDataSource: LocalSearchResultDataSource,
) {

    fun fetchRemoteSearchResultModels(keyWord: String): Flow<PagingData<SearchResultItem>>
        = Pager(PagingConfig(
            pageSize = END_PAGING_COUNT,
            enablePlaceholders = false
        )) {
            RemoteImagePagingSource(
                kakaoApi = kakaoApi,
                context = context,
                keyWord = keyWord
            )
        }.flow

    val localSearchResultModels: Flow<List<SearchResultItem>>
        = localSearchResultDataSource.searchResultModels

    fun updateSearchResultToLocal(imageUiState: SearchResultItem)
        = localSearchResultDataSource.updateSearchResultToLocal(imageUiState)


}