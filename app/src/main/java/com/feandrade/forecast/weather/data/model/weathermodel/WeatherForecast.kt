package com.feandrade.forecast.weather.data.model.weathermodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherForecast(
    val coord: Coord,
    val weather: List<WeatherDescription>,
    val base: String?,
    val main : ForecastTemp,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val sys : SysInf,
    val id: Int,
    val name: String,
    val cod: Int
) : Parcelable

