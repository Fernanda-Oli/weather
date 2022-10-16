package com.feandrade.forecast.weather.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feandrade.forecast.weather.BuildConfig
import com.feandrade.forecast.weather.R
import com.feandrade.forecast.weather.data.model.WeatherForecast

class WeatherInfosAdapter(
    val context: Context,
    private val listWeatherForecast: WeatherForecast,
    private val itemListener: ((weather: WeatherForecast) -> Unit),
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = when (viewType) {
            0 -> {
                val viewInfoWeather =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_rv_info, parent, false)
                InfoWeatherViewHolder(viewInfoWeather, context)
            }
            1 -> {
                val viewInfoAdd =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_weather_info_additional, parent, false)
                InfoAddViewHolder(viewInfoAdd)
            }
            else -> {
                val viewNews = LayoutInflater.from(parent.context).inflate(R.layout.item_break_news_additional ,parent, false)
                InfoBreakNewsViewHolder(viewNews, context)
            }
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return if(position > 1){
            2
        } else if (position > 0) {
            1
        } else {
            0
        }
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InfoWeatherViewHolder -> {
                holder.bind(listWeatherForecast)
            }
            is InfoAddViewHolder -> {
                holder.bind(listWeatherForecast)
            }
            is InfoBreakNewsViewHolder -> holder.bind()
        }
    }


    class InfoWeatherViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val nameCity: TextView = view.findViewById(R.id.txt_title)
        private val icon: ImageView = view.findViewById(R.id.imgV_icon)
        private val temp: TextView = view.findViewById(R.id.txt_temp)
        private val tempMin: TextView = view.findViewById(R.id.txt_temp_min_value)
        private val tempMax: TextView = view.findViewById(R.id.txt_temp_max_value)

        fun bind(weather: WeatherForecast) {
            weather.let {
                nameCity.text = it.name
                temp.text = it.main.temp.toString()
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

    class InfoBreakNewsViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        fun bind() {
            val imageNews : ImageView = itemView.findViewById(R.id.imageNews)
            val textTitle : TextView = itemView.findViewById(R.id.textTitle)

            textTitle.text = "NEWS APP"

            Glide.with(context)
                .load(R.drawable.ic_launcher_foreground)
                .into(imageNews)
        }

    }



}