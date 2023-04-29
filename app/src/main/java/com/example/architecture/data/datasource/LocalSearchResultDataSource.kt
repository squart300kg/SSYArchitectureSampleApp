package com.example.architecture.data.datasource

import com.example.architecture.ui.model.SearchResultItem
import kotlinx.coroutines.flow.Flow

interface LocalSearchResultDataSource {
    fun updateSearchResult(searchResultItem: SearchResultItem)

    val searchResultModels: Flow<List<SearchResultItem>>
}