package com.example.kakao.di

import com.example.kakao.datalayer.datasource.LocalImageDataSource
import com.example.kakao.datalayer.datasource.RemoteImageDataSource
import com.example.kakao.datalayer.repository.ImageRepository
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
        remoteImageDataSource: RemoteImageDataSource,
        localImageDataSource: LocalImageDataSource
    ): ImageRepository = ImageRepository(
        remoteImageDataSource = remoteImageDataSource,
        localImageDataSource = localImageDataSource
    )

}