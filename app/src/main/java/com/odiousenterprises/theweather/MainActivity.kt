package com.odiousenterprises.theweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonClicked(view: View) {
//        var vm = ViewModelProviders.of(this)[WeatherViewModel::class.java]

        GlobalScope.launch {
            val weatherData = async { WeatherApiClient().sendRequest() }
            println(weatherData.await())
        }
    }
}
