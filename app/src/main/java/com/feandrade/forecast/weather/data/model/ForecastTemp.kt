package com.feandrade.forecast.weather.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastTemp(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    val tempMin: Double,
    val tempMax: Double,
) : Parcelable
