package com.feandrade.forecast.weather.data.repository

import com.feandrade.forecast.weather.data.model.weathermodel.WeatherForecast
import com.feandrade.forecast.weather.data.network.WeatherApi

class WeatherRepositoryImpl(private val weatherApi : WeatherApi) : WeatherRepository {
    override suspend fun getAllWeatherData(q: String, apiKey: String): WeatherForecast =
        weatherApi.getAllWeatherData(q, apiKey)
}