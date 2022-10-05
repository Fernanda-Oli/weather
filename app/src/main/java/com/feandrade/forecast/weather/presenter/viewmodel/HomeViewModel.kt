package com.feandrade.forecast.weather.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feandrade.forecast.weather.core.State
import com.feandrade.forecast.weather.data.model.WeatherForecast
import com.feandrade.forecast.weather.data.repository.WeatherRepository
import com.feandrade.forecast.weather.data.sharedpreference.DataStorage
import com.feandrade.forecast.weather.data.sharedpreference.SharedPreference
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: WeatherRepository,
    private val cache : DataStorage
) : ViewModel(){

    private val _response = MutableLiveData<State<WeatherForecast>>()
    val response : LiveData<State<WeatherForecast>>
    get() = _response

    private val _enterSaveCity = MutableLiveData<String>()

    val _latLng = MutableLiveData<LatLng>()
//    val latLng = LiveData<LatLng>
//    get() = _latLng

    fun getAllWeatherData(q: String, apiKey: String) = viewModelScope.launch {
        try {
            _response.value = State.loading(true)
            val response = withContext(ioDispatcher) {
                repository.getAllWeatherData(q, apiKey)
            }
            _response.value = State.success(response)
        } catch (e: Throwable) {
            _response.value = State.error(e)
        }
    }

    fun getLatLng(lat : Double, lng : Double){
        _latLng.value = LatLng(lat, lng)
    }

    fun getSaveCity() : String{
        cache.getData(SharedPreference.CITY)?.let {
            _enterSaveCity.value = it
        }
        return _enterSaveCity.value.toString()
    }

    fun saveUserCity(city: String) {
        if (city != _enterSaveCity.value)
            cache.saveData(SharedPreference.CITY, city)
    }
}




