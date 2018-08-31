package com.mario.forecast

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.mario.forecast.di.appModules
import org.koin.android.ext.android.startKoin

/**
 * My application
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, modules = appModules)
    }
}