package com.barreservation

import android.app.Application
import com.barreservation.di.Koin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BarReservation : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BarReservation)
            modules(Koin.modules)
        }
    }
}