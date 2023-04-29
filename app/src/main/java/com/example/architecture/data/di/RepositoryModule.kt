package com.example.architecture.data.di

import com.example.architecture.data.datasource.LocalSearchResultDataSourceImpl
import com.example.architecture.data.datasource.RemoteSearchResultPagingSourceImpl
import com.example.architecture.data.repository.SearchResultRepository
import com.example.architecture.data.repository.SearchResultRepositoryImpl
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