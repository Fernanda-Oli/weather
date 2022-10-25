package com.feandrade.forecast.weather.data.model.weathermodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    val speed : Double,
    val deg : Double
) : Parcelable