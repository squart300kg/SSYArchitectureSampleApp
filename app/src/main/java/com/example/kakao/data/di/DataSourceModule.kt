package com.example.kakao.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.kakao.data.api.KakaoApi
import com.example.kakao.data.datasource.LocalSearchResultDataSource
import com.example.kakao.data.datasource.LocalSearchResultDataSourceImpl
import com.example.kakao.data.datasource.RemoteSearchResultPagingSourceImpl
import com.example.kakao.data.datasource.RemoteSearchResultPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalSearchResultDataSource(
        sharedPreferences: SharedPreferences
    ): LocalSearchResultDataSource = LocalSearchResultDataSourceImpl(
        sharedPreferences = sharedPreferences,
    )

    @Provides
    @Singleton
    fun provideRemoteImagePagingSource(
        kakaoApi: KakaoApi,
        @ApplicationContext context: Context,
    ): RemoteSearchResultPagingSource = RemoteSearchResultPagingSourceImpl(
        kakaoApi = kakaoApi,
        context = context,
    )

}