package com.feandrade.forecast.weather.data.model.weathermodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Clouds(
    val all : Int
) : Parcelable