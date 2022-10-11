package com.feandrade.forecast.weather.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feandrade.forecast.weather.BuildConfig
import com.feandrade.forecast.weather.R
import com.feandrade.forecast.weather.data.model.WeatherForecast
import com.feandrade.forecast.weather.databinding.ItemRvInfoBinding

class WeatherInfosAdapter(
    private val list: MutableList<WeatherForecast>,
    private val itemListener: ((weather: WeatherForecast) -> Unit)
) : RecyclerView.Adapter<WeatherInfosAdapter.AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemBinding =
            ItemRvInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(itemBinding, itemListener)

    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class AdapterViewHolder(
        private val itemWeatherBinding: ItemRvInfoBinding,
        private val itemWListener: ((weather: WeatherForecast) -> Unit)
    ) : RecyclerView.ViewHolder(itemWeatherBinding.root) {
        fun bind(weather: WeatherForecast) {
            itemWeatherBinding.run {
                txtTitle.text = weather.name
                txtTempValue.text = weather.main.temp.toString()
                txtTempMinValue.text = weather.main.tempMin.toInt().toString()
                txtTempMaxValue.text = weather.main.tempMax.toInt().toString()
                var imgBase = String.format(
                    BuildConfig.BASE_URL_IMAGE,
                    weather.weather.first().icon.toString()
                )

                Glide.with(root.context)
                    .load(imgBase)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imgVIcon)
            }
        }
    }
}
