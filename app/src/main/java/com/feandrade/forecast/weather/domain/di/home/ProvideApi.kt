package com.feandrade.forecast.weather.domain.di.home

import com.feandrade.forecast.weather.BuildConfig
import com.feandrade.forecast.weather.data.network.ApiService
import com.feandrade.forecast.weather.data.network.Service

fun provideApi(apiService: ApiService) : Service =
    apiService.service(BuildConfig.BASE_URL, Service::class.java)