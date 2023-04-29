package com.example.architecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class Application: Application() {

    override fun onCreate() {
        super.onCreate()

    }

}

