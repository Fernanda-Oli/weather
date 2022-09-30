package com.feandrade.forecast.weather.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class WeatherDescription(
    val id : Int,
    val main : String,
    val description: String,
    val icon : String
) : Parcelable
