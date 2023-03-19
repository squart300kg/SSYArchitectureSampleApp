package com.example.kakao.domainlayer

import com.example.kakao.datalayer.repository.ImageRepository
import javax.inject.Inject

class GetSortedHomeImageUseCase @Inject constructor(
    imageRepository: ImageRepository
) {

    operator fun invoke() {}
}