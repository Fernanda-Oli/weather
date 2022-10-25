package com.feandrade.forecast.weather.data.network

import com.feandrade.forecast.weather.data.model.newsmodel.NewsResponse
import com.feandrade.forecast.weather.data.model.weathermodel.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

        @GET("data/2.5/weather")
        suspend fun getAllWeatherData(
                @Query("q") q : String,
                @Query("appid") appid: String
        ) : WeatherForecast
}