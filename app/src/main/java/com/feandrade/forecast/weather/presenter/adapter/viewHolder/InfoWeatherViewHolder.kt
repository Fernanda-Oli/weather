package com.feandrade.forecast.weather.presenter.adapter.viewHolder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feandrade.forecast.weather.BuildConfig
import com.feandrade.forecast.weather.R
import com.feandrade.forecast.weather.data.model.weathermodel.WeatherForecast

class InfoWeatherViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
    private val nameCity: TextView = view.findViewById(R.id.txt_title)
    private val icon: ImageView = view.findViewById(R.id.imgV_icon)
    private val tempValue: TextView = view.findViewById(R.id.txt_temp_value)
    private val tempMin: TextView = view.findViewById(R.id.txt_temp_min_value)
    private val tempMax: TextView = view.findViewById(R.id.txt_temp_max_value)

    fun bind(weather: WeatherForecast) {
        weather.let {
            nameCity.text = it.name
            tempValue.text = it.main.temp.toString()
            tempMin.text = it.main.tempMin.toString()
            tempMax.text = it.main.tempMax.toString()
            val imgBase = String.format(
                BuildConfig.BASE_URL_IMAGE,
                it.weather.first().icon
            )
            Glide.with(context)
                .load(imgBase)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(icon)
        }
    }
}