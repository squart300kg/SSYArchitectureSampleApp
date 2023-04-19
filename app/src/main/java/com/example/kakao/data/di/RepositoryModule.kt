package com.example.kakao.data.di

import android.content.Context
import com.example.kakao.data.api.KakaoApi
import com.example.kakao.data.datasource.LocalSearchResultDataSource
import com.example.kakao.data.repository.SearchResultRepository
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
        localSearchResultDataSource: LocalSearchResultDataSource
    ): SearchResultRepository = SearchResultRepository(
        context = context,
        kakaoApi = kakaoApi,
        localSearchResultDataSource = localSearchResultDataSource
    )

}