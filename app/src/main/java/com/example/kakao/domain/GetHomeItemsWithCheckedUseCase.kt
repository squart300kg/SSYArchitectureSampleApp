package com.example.kakao.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.example.kakao.data.repository.SearchResultRepository
import com.example.kakao.ui.model.SearchResultItem
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
            remoteSearchResultModels.map { remoteImage ->
                val duplicateSearchResultModel =
                    localSearchResultModels.find { localImage -> localImage.thumbnailUrl == remoteImage.thumbnailUrl }

                if (duplicateSearchResultModel != null) {
                    remoteImage.copy(isFavorite = true)
                } else {
                    remoteImage
                }
            }
        }
    }
}