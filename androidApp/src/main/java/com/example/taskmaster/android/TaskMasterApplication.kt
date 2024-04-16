package com.example.taskmaster.android

import com.example.taskmaster.android.di.presentationModule
import com.example.taskmaster.di.initKoin
import com.example.taskmaster.domain.utils.NapierInit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import android.app.Application
import org.koin.android.BuildConfig

class TaskMasterApplication : Application()   {
    override fun onCreate() {
        super.onCreate()

        val appModules = listOf(presentationModule)

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@TaskMasterApplication)
            modules(appModules)
        }

        if (BuildConfig.DEBUG) {
            NapierInit().init()
        }
    }
}