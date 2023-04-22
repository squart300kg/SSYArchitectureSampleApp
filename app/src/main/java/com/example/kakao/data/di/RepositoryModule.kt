package com.example.kakao.data.di

import com.example.kakao.data.datasource.LocalSearchResultDataSourceImpl
import com.example.kakao.data.datasource.RemoteSearchResultPagingSourceImpl
import com.example.kakao.data.repository.SearchResultRepository
import com.example.kakao.data.repository.SearchResultRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideImageRepository(
        localSearchResultDataSource: LocalSearchResultDataSourceImpl,
        remoteImagePagingSource: RemoteSearchResultPagingSourceImpl
    ): SearchResultRepository = SearchResultRepositoryImpl(
        localSearchResultDataSource = localSearchResultDataSource,
        remoteImagePagingSource = remoteImagePagingSource
    )

}