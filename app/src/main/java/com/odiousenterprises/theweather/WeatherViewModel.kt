package com.odiousenterprises.theweather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class WeatherViewModel(application: Application): AndroidViewModel(application) {

    private val weatherData: MutableLiveData<WeatherData> = MutableLiveData()

}