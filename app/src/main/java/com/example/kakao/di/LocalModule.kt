package com.example.kakao.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val PREFERENCE_NAME = "LOCAL_IMAGE_PREFERENCE"

const val LOCAL_IMAGE_ITEMS = "LOCAL_IMAGE_ITEMS"

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context)
        = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

}