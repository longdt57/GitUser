package com.app.androidcompose

import android.app.Application
import com.facebook.stetho.Stetho
import com.google.firebase.FirebaseApp
import com.lyft.kronos.KronosClock
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import timber.log.Timber

@HiltAndroidApp
class AppApplication : Application() {

    @Inject
    lateinit var kronosClock: KronosClock

    override fun onCreate() {
        super.onCreate()
        kronosClock.syncInBackground()
        setupLogging()
        Stetho.initializeWithDefaults(this)
        setupFirebase()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupFirebase() {
        FirebaseApp.initializeApp(this)
    }
}