package com.example.kakao.data.repository

import androidx.paging.PagingData
import com.example.kakao.ui.model.SearchResultItem
import kotlinx.coroutines.flow.Flow

interface SearchResultRepository {

    val localSearchResultModels: Flow<List<SearchResultItem>>

    fun fetchRemoteSearchResultModels(keyWord: String): Flow<PagingData<SearchResultItem>>

    fun updateSearchResultToLocal(imageUiState: SearchResultItem)
}