package com.example.architecture.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.architecture.ui.model.SearchResultItem

abstract class RemoteSearchResultPagingSource: PagingSource<Int, SearchResultItem>() {
    open var keyWord: String = ""

    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResultItem>

    abstract override fun getRefreshKey(state: PagingState<Int, SearchResultItem>): Int?
}