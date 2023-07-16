package com.example.dentistmonitor

import android.app.Application
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber

class App : Application() {
    class App : Application() {
        override fun onCreate() {
            super.onCreate()
            setupKoin()
            setupTimber()
            FirebaseApp.initializeApp(this)
        }

        private fun setupKoin() {
            stopKoin()
            startKoin {
                androidContext(this@App)
                modules(
                    listOf(
                    )
                )
            }
        }

        private fun setupTimber() {
            Timber.plant(Timber.DebugTree())
        }
    }
}