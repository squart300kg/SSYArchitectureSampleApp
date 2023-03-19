package com.example.kakao.di

import com.example.kakao.datalayer.api.KakaoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    const val TIMEOUT = 10L

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideAatApi(
        okHttpClient: OkHttpClient
    ) : KakaoApi {
        // TODO: url설정하기
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoApi::class.java)
    }
}