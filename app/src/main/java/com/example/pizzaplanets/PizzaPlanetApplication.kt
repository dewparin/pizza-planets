package com.example.pizzaplanets

import android.app.Application
import com.example.pizzaplanets.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PizzaPlanetApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PizzaPlanetApplication)
            modules(appModule)
        }
    }
}