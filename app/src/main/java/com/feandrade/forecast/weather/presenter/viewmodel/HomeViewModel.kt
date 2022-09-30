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

    private val _enterSaveLatLog = MutableLiveData<String>()
    private val _enterSaveLog = MutableLiveData<String>()


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

    fun onMapReady(googleMap: GoogleMap) {
        if (getSaveLatLog().isNotEmpty())
            googleMap.addMarker(
                MarkerOptions()
                    .position()
                    .title("")
            )
    }

    private fun MarkerOptions.position(lat : Double, log : Double): MarkerOptions = position()

    fun getSaveCity() : String{
        cache.getData(SharedPreference.CITY)?.let {
            _enterSaveCity.value = it
        }
        return _enterSaveCity.value.toString()
    }

    fun getSaveLatLog() : String {
        cache.getData(SharedPreference.LAT)?.let {
            _enterSaveLatLog.value = it
        }
        return _enterSaveLatLog.value.toString()
    }

    fun getSaveLog() : String {
        cache.getData(SharedPreference.LOG)?.let {
            _enterSaveLog.value = it
        }
        return _enterSaveLog.value.toString()
    }

    fun saveUserCity(city: String) {
        if (city != _enterSaveCity.value)
            cache.saveData(SharedPreference.CITY, city)
    }

    private fun excludeDataLatLog(){
        if (!cache.deleteData(SharedPreference.LAT).equals(_enterSaveLatLog.value)) {
            cache.deleteData(SharedPreference.LAT)
            cache.deleteData(SharedPreference.LOG)
        }
    }

    fun saveUserLatLog(latLog: String, log: String) {
        if (latLog != _enterSaveLatLog.value)
            excludeDataLatLog()
            cache.saveData(SharedPreference.LAT, latLog)
            cache.saveData(SharedPreference.LOG, log)
    }
}




