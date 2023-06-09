package com.example.architecture.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.example.architecture.data.repository.SearchResultRepository
import com.example.architecture.ui.model.SearchResultItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetHomeItemsWithCheckedUseCase @Inject constructor(
    private val searchResultRepository: SearchResultRepository
) {

    operator fun invoke(keyWord: String): Flow<PagingData<SearchResultItem>> {
        return combine(
            searchResultRepository.fetchRemoteSearchResultModels(keyWord),
            searchResultRepository.localSearchResultModels
        ) { remoteSearchResultModels, localSearchResultModels ->
            remoteSearchResultModels.map { remoteSearchResult ->
                val duplicateSearchResultModel =
                    localSearchResultModels.find { localSearchResult -> localSearchResult.thumbnailUrl == remoteSearchResult.thumbnailUrl }

                if (duplicateSearchResultModel != null) {
                    remoteSearchResult.copy(isFavorite = true)
                } else {
                    remoteSearchResult
                }
            }
        }
    }
}