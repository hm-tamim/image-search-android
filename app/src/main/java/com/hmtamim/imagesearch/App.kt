package com.hmtamim.imagesearch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setInstance(this)
    }

    companion object {
        private lateinit var sInstance: App

        fun getAppContext(): App {
            return sInstance
        }

        @JvmStatic
        @Synchronized
        private fun setInstance(app: App) {
            sInstance = app
        }
    }

}
