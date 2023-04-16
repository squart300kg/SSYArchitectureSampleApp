package com.example.kakao.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.example.kakao.data.repository.ImageRepository
import com.example.kakao.ui.model.SearchResultItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetHomeItemsWithCheckedUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    operator fun invoke(keyWord: String): Flow<PagingData<SearchResultItem>> {
        return combine(
            imageRepository.fetchRemoteImage(keyWord),
            imageRepository.localImages()
        ) { remoteImages, localImages ->
                remoteImages.map { remoteImage ->
                    val duplicateImage = localImages.find { localImage -> localImage.thumbnailUrl == remoteImage.thumbnailUrl }
                    if (duplicateImage != null) {
                        remoteImage.copy(isFavorite = true)
                    } else {
                        remoteImage
                    }
                }
            }
    }
}