package com.example.kakao.domainlayer

import com.example.kakao.datalayer.repository.ImageRepository
import com.example.kakao.uilayer.model.ItemImageUiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// TODO: 네이밍 고려
class GetSortedHomeImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    // TODO: localImages와 remoteImages의 병렬연산을 통해 하나의 리스트로 만들어주기 위한
    //  flatMapConcat or flatMapMerge or flatMapLatest의 사용 고려?
    //  https://kotlinworld.com/263 참고
    operator fun invoke(keyWord: String): Flow<List<ItemImageUiState>> {
        return imageRepository.fetchRemoteImage(keyWord)
    }
}