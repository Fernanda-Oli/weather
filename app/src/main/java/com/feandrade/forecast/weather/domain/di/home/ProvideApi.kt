package com.feandrade.forecast.weather.domain.di.home

import com.feandrade.forecast.weather.BuildConfig
import com.feandrade.forecast.weather.data.network.ApiService
import com.feandrade.forecast.weather.data.network.NewsApi
import com.feandrade.forecast.weather.data.network.WeatherApi

fun provideApi(apiService: ApiService) : WeatherApi =
    apiService.service(BuildConfig.BASE_URL, WeatherApi::class.java)

fun provideNewsApi(apiService: ApiService) : NewsApi =
    apiService.service(BuildConfig.BASE_URL_NEWS, NewsApi::class.java)