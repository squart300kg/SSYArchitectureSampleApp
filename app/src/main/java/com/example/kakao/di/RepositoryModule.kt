package com.example.kakao.di

import android.content.Context
import com.example.kakao.datalayer.api.KakaoApi
import com.example.kakao.datalayer.datasource.LocalImageDataSource
import com.example.kakao.datalayer.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideImageRepository(
        @ApplicationContext context: Context,
        kakaoApi: KakaoApi,
        localImageDataSource: LocalImageDataSource
    ): ImageRepository = ImageRepository(
        context = context,
        kakaoApi = kakaoApi,
        localImageDataSource = localImageDataSource
    )

}