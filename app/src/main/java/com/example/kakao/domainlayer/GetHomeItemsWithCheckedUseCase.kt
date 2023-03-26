package com.example.kakao.domainlayer

import androidx.paging.PagingData
import androidx.paging.map
import com.example.kakao.datalayer.repository.ImageRepository
import com.example.kakao.uilayer.model.ItemImageUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetHomeItemsWithCheckedUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    // TODO: 검색어 하드코딩 지우기
    operator fun invoke(keyWord: String): Flow<PagingData<ItemImageUiState>> {
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