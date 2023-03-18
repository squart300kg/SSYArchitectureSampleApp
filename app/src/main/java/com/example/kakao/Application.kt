package com.example.kakao

import android.app.Application
import com.example.kakao.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        koinInitialize()

    }

    private fun koinInitialize() {

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(listOf(
                networkModule,
                repositoryModule,
                viewModelModule,
                viewModule))
        }

    }
}

