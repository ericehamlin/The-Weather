package com.odiousenterprises.theweather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class WeatherViewModel(application: Application): AndroidViewModel(application) {

    private val data: MutableLiveData<WeatherData> = MutableLiveData()

    fun getWeatherData(location: String) {
        val latitude: Float = 12.5f
        val longitude: Float = 13.9f
        loadWeatherData(latitude, longitude)
    }

    fun loadWeatherData(latitude: Float, longitude: Float) {
        GlobalScope.launch {
            val weatherData = async { WeatherApiClient().sendRequest(latitude, longitude) }
            data.postValue(weatherData.await())
        }
    }
}