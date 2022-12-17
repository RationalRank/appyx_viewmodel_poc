package dev.ranjith.appyx.viewmodel.poc

import android.app.Application
import timber.log.Timber

class PocApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}