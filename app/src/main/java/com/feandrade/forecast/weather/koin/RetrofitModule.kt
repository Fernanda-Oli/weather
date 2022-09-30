package com.feandrade.forecast.weather.koin

import com.feandrade.forecast.weather.data.network.ApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

object RetrofitModule  {

    private val apiServiceModule = module {
        single {
            ApiService(get(), get())
        }
    }

    private val gsonModule = module {
        single {
            GsonBuilder().setLenient().create()
        }
    }

    private val clientModule = module {
        single {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        }
    }


    val retrofitModules = listOf(
        gsonModule,
        clientModule,
        apiServiceModule
    )

}