package com.feandrade.forecast.weather.data.network

import com.feandrade.forecast.weather.data.model.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

        @GET("data/2.5/weather")
        suspend fun getAllWeatherData(
                @Query("q") q : String,
                @Query("appid") appid: String
        ) : WeatherForecast
}