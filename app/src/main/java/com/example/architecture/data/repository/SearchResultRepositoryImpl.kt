package com.example.architecture.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.architecture.data.datasource.LocalSearchResultDataSource
import com.example.architecture.data.datasource.MAX_PAGE_COUNT_FOR_VIDEO_API
import com.example.architecture.data.datasource.RemoteSearchResultPagingSource
import com.example.architecture.ui.model.SearchResultItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchResultRepositoryImpl @Inject constructor(
    private val localSearchResultDataSource: LocalSearchResultDataSource,
    private val remoteImagePagingSource: RemoteSearchResultPagingSource,
): SearchResultRepository {

    override fun fetchRemoteSearchResultModels(keyWord: String): Flow<PagingData<SearchResultItem>>
        = Pager(PagingConfig(
            pageSize = MAX_PAGE_COUNT_FOR_VIDEO_API,
            enablePlaceholders = false
        )) {
            remoteImagePagingSource.apply { this.keyWord = keyWord }
        }.flow

    override val localSearchResultModels: Flow<List<SearchResultItem>>
        = localSearchResultDataSource.searchResultModels

    override fun updateSearchResultToLocal(imageUiState: SearchResultItem)
        = localSearchResultDataSource.updateSearchResult(imageUiState)
}