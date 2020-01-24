package com.odiousenterprises.theweather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class WeatherViewModel(application: Application): AndroidViewModel(application) {

    private val data: MutableLiveData<WeatherData> = MutableLiveData()

    fun getCurrentWeatherData(): MutableLiveData<WeatherData> {
        return data;
    }

    fun getWeatherData(location: String) {
        GlobalScope.launch {
            val geocodingData = GeocodingApiClient.sendRequest(location)
            val latitude = geocodingData.results[0].geometry.location.lat
            val longitude  = geocodingData.results[0].geometry.location.lng
            loadWeatherData(latitude, longitude)
        }

    }

    suspend fun loadWeatherData(latitude: Float, longitude: Float) {
        coroutineScope {
            val weatherData = async { WeatherApiClient.sendRequest(latitude, longitude) }
            data.postValue(weatherData.await())
        }
    }
}