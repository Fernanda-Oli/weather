package com.feandrade.forecast.weather.presenter.adapter.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feandrade.forecast.weather.R
import com.feandrade.forecast.weather.data.model.weathermodel.WeatherForecast

class InfoAddViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val description : TextView = view.findViewById(R.id.txt_description)
    private val humidValue : TextView = view.findViewById(R.id.txt_humid_value)
    private val speed: TextView = view.findViewById(R.id.txt_speed_value)
    private val pressure: TextView = view.findViewById(R.id.txt_pressure_value)
    fun bind(weather: WeatherForecast) {
        weather.let {
            description.text = it.weather.first().description
            humidValue.text = it.main.humidity.toString()
            speed.text = it.wind.speed.toString()
            pressure.text = it.main.pressure.toString()
        }
    }

}