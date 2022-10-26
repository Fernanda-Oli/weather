package com.feandrade.forecast.weather.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feandrade.forecast.weather.R
import com.feandrade.forecast.weather.data.model.newsmodel.Article
import com.feandrade.forecast.weather.data.model.weathermodel.WeatherForecast
import com.feandrade.forecast.weather.presenter.adapter.viewHolder.InfoAddViewHolder
import com.feandrade.forecast.weather.presenter.adapter.viewHolder.InfoBreakNewsViewHolder
import com.feandrade.forecast.weather.presenter.adapter.viewHolder.InfoWeatherViewHolder

class WeatherInfosAdapter(
    val context: Context,
    private val weatherForecast: WeatherForecast,
    private val newsListItem: MutableList<Article>,
    private val itemListener: ((weather: WeatherForecast) -> Unit),
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = when (viewType) {
            0 -> {
                val viewInfoWeather =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_rv_info, parent, false)
                InfoWeatherViewHolder(viewInfoWeather, context)
            }
            1 -> {
                val viewInfoAdd =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_weather_info_additional, parent, false)
                InfoAddViewHolder(viewInfoAdd)
            }
            else -> {
                val viewNews = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_break_news_additional, parent, false)
                InfoBreakNewsViewHolder(viewNews, context)
            }
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return if (position > 1) {
            2
        } else if (position > 0) {
            1
        } else {
            0
        }
    }

    override fun getItemCount(): Int = newsListItem.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InfoWeatherViewHolder -> {
                holder.bind(weatherForecast)
            }
            is InfoAddViewHolder -> {
                holder.bind(weatherForecast)
            }
            is InfoBreakNewsViewHolder -> holder.bind(newsListItem[position])
        }
    }
}