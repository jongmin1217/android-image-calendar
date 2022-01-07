package com.bellminp.imagecalendar

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.annotation.NonNull
import timber.log.Timber

class MyApp : Application() {
    companion object{
        lateinit var mInstance: MyApp
        fun get(): MyApp {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()

        mInstance = this
        setUpTimber()
    }

    private fun setUpTimber(){
        val pm = packageManager
        try {
            val appInfo = pm.getApplicationInfo(packageName, 0)
            if (0 != appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) {
                Timber.plant(Timber.DebugTree())
            } else {
                Timber.plant(CrashReportingTree())
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.plant(CrashReportingTree())
        }
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, @NonNull message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
        }
    }
}