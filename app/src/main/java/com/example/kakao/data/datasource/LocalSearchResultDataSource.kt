package com.example.kakao.data.datasource

import com.example.kakao.ui.model.SearchResultItem
import kotlinx.coroutines.flow.Flow

interface LocalSearchResultDataSource {
    fun updateSearchResult(searchResultItem: SearchResultItem)

    val searchResultModels: Flow<List<SearchResultItem>>
}