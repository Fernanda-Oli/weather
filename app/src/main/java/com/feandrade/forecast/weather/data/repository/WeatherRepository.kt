package com.feandrade.forecast.weather.data.repository

import com.feandrade.forecast.weather.data.model.WeatherForecast

interface WeatherRepository{
    suspend fun getAllWeatherData(q : String, apiKey : String) : WeatherForecast
}