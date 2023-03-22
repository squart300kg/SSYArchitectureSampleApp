package com.example.kakao.domainlayer

import com.example.kakao.datalayer.repository.ImageRepository
import com.example.kakao.uilayer.model.ItemImageUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

// TODO: 네이밍 고려
class GetSortedHomeImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    // TODO: localImages와 remoteImages의 병렬연산을 통해 하나의 리스트로 만들어주기 위한
    //  flatMapConcat or flatMapMerge or flatMapLatest의 사용 고려?
    //  https://kotlinworld.com/263 참고

    // TODO: 검색어 하드코딩 지우기
    operator fun invoke(keyWord: String): Flow<List<ItemImageUiState>> {
        return imageRepository.fetchRemoteImage("설현").combine(imageRepository.localImages) { remoteImages, localImages ->
            remoteImages.map { remoteImage ->
                localImages.find { localImage -> localImage.thumbnailUrl == remoteImage.thumbnailUrl }
                    ?.let { remoteImage.copy(isFavorite = true) }
                    ?: remoteImage
            }
        }
    }
}