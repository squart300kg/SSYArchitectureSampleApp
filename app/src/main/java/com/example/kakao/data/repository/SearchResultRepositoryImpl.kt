package com.example.kakao.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.kakao.data.datasource.END_PAGING_COUNT
import com.example.kakao.data.datasource.LocalSearchResultDataSource
import com.example.kakao.data.datasource.RemoteSearchResultPagingSource
import com.example.kakao.ui.model.SearchResultItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchResultRepositoryImpl @Inject constructor(
    private val localSearchResultDataSource: LocalSearchResultDataSource,
    private val remoteImagePagingSource: RemoteSearchResultPagingSource,
): SearchResultRepository {

    override fun fetchRemoteSearchResultModels(keyWord: String): Flow<PagingData<SearchResultItem>>
        = Pager(PagingConfig(
            pageSize = END_PAGING_COUNT,
            enablePlaceholders = false
        )) {
            remoteImagePagingSource.apply { this.keyWord = keyWord }
        }.flow

    override val localSearchResultModels: Flow<List<SearchResultItem>>
        = localSearchResultDataSource.searchResultModels

    override fun updateSearchResultToLocal(imageUiState: SearchResultItem)
        = localSearchResultDataSource.updateSearchResult(imageUiState)


}