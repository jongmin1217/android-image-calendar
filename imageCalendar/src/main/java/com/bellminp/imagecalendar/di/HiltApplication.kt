package com.bellminp.imagecalendar.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application(){
    companion object{
        lateinit var mInstance: HiltApplication
        fun get(): HiltApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()

        mInstance = this
    }
}