package com.feandrade.forecast.weather.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService(private val gsonModule: Gson, private val clientModule: OkHttpClient) {

    private fun initRetrofit(baseUrl : String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gsonModule))
            .client(clientModule)
            .build()
    }

    fun <T> service(url: String, api: Class<T>): T = initRetrofit(url).create(api)

}