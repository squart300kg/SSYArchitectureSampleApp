package com.example.architecture.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.architecture.data.api.BaseApi
import com.example.architecture.data.datasource.LocalSearchResultDataSource
import com.example.architecture.data.datasource.LocalSearchResultDataSourceImpl
import com.example.architecture.data.datasource.RemoteSearchResultPagingSourceImpl
import com.example.architecture.data.datasource.RemoteSearchResultPagingSource
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
        baseApi: BaseApi,
        @ApplicationContext context: Context,
    ): RemoteSearchResultPagingSource = RemoteSearchResultPagingSourceImpl(
        baseApi = baseApi,
        context = context,
    )

}