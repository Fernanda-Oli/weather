package com.feandrade.forecast.weather.data.repository

import com.feandrade.forecast.weather.data.model.weathermodel.WeatherForecast

interface WeatherRepository{
    suspend fun getAllWeatherData(q : String, apiKey : String) : WeatherForecast
}