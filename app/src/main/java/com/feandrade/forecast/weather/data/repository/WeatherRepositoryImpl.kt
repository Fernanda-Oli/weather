package com.feandrade.forecast.weather.data.repository

import com.feandrade.forecast.weather.data.model.WeatherForecast
import com.feandrade.forecast.weather.data.network.Service

class WeatherRepositoryImpl(private val weatherApi : Service) : WeatherRepository {
    override suspend fun getAllWeatherData(q: String, apiKey: String): WeatherForecast =
        weatherApi.getAllWeatherData(q, apiKey)
}