package com.feandrade.forecast.weather.appaplication

import android.app.Application
import com.feandrade.forecast.weather.koin.RetrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(RetrofitModule.retrofitModules)
        }

    }
}