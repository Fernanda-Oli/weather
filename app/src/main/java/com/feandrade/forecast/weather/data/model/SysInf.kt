package com.feandrade.forecast.weather.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SysInf(
    val type : Int,
    val id : Int,
    val message : Double,
    val country : String,
    val sunrise : Int,
    val sunset : Int
) : Parcelable