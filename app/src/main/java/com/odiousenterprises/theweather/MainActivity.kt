package com.odiousenterprises.theweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val map = MapFragment.newInstance()
        fragmentManager.beginTransaction()
            .add(R.id.map, map, "foo").commit()
        map.getMapAsync({googleMap ->
            println("hammed")
        })

        val model = ViewModelProviders.of(this)[WeatherViewModel::class.java]
        model.getCurrentWeatherData().observe(this, Observer<WeatherData> { weatherData ->
            val currentWeather = weatherData.currently
            findViewById<TextView>(R.id.display_temperature).text =
                currentWeather.temperature.toString() + 176.toChar()
            findViewById<TextView>(R.id.humidity).text = currentWeather.humidity.toString()
            findViewById<TextView>(R.id.precipitation).text = currentWeather.precipProbability.toString()
        })

    }

    fun onButtonClicked(view: View) {
        var model = ViewModelProviders.of(this)[WeatherViewModel::class.java]
        val location = findViewById<TextView>(R.id.input_location_text).text.toString()
        model.getWeatherData(location)
    }
}
