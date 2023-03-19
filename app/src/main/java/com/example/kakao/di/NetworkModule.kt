package com.example.kakao.di

import android.util.Log
import com.example.kakao.BuildConfig
import com.example.kakao.datalayer.api.KakaoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RequestInterceptor

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiLogInterceptor

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    const val TIMEOUT = 10L

    @RequestInterceptor
    @Provides
    fun provideRequestInterceptor(): Interceptor {
        return object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain
                    .request()
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        "KakaoAK ${BuildConfig.KakaoRestApiKey}"
                    )
                    .build()
                return chain.proceed(request)
            }

        }
    }

    @ApiLogInterceptor
    @Provides
    fun provideApiLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("API", message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(
        @RequestInterceptor requestInterceptor: Interceptor,
        @ApiLogInterceptor apiLogInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
            .addInterceptor(apiLogInterceptor)
            .build()
    }

    @Provides
    fun provideAatApi(
        okHttpClient: OkHttpClient
    ) : KakaoApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.KakaoApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoApi::class.java)
    }
}