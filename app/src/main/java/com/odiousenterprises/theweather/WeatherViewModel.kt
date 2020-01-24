package com.odiousenterprises.theweather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import java.io.IOException
import java.net.ConnectException

class WeatherViewModel(application: Application): AndroidViewModel(application) {

    private val geocodingData: MutableLiveData<GeocodingData> = MutableLiveData()
    private val weatherData: MutableLiveData<WeatherData> = MutableLiveData()

    fun getCurrentWeatherData(): MutableLiveData<WeatherData> {
        return weatherData;
    }

    fun getCurrentGeocodingData(): MutableLiveData<GeocodingData> {
        return geocodingData;
    }

    fun getWeatherData(location: String, handler: CoroutineExceptionHandler) {
        GlobalScope.launch(handler) {
            val geo = GeocodingApiClient.sendRequest(location)
            val latitude = geo.results[0].geometry.location.lat
            val longitude  = geo.results[0].geometry.location.lng
            loadWeatherData(latitude, longitude)
            geocodingData.postValue(geo)
        }
    }

    suspend fun loadWeatherData(latitude: Float, longitude: Float) {
        coroutineScope {
            val response = async { WeatherApiClient.sendRequest(latitude, longitude) }
            weatherData.postValue(response.await())
        }
    }
}