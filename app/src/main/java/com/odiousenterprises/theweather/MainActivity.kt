package com.odiousenterprises.theweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = MapFragment.newInstance()
        fragmentManager.beginTransaction()
            .add(R.id.map, mapFragment, "foo").commit()

        mapFragment.getMapAsync({map ->
            googleMap = map
        })


        val model = ViewModelProviders.of(this)[WeatherViewModel::class.java]

        model.getCurrentWeatherData().observe(this, Observer<WeatherData> { weatherData ->
            val currentWeather = weatherData.currently
            val DEGREE_SYMBOL = 176.toChar()
            findViewById<TextView>(R.id.display_temperature).text =
                currentWeather.temperature.toString() + DEGREE_SYMBOL
            findViewById<TextView>(R.id.humidity).text = currentWeather.humidity.toString()
            findViewById<TextView>(R.id.precipitation).text = currentWeather.precipProbability.toString()

            googleMap.moveCamera(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(
                        LatLng(weatherData.latitude.toDouble(), weatherData.longitude.toDouble()),
                        4f
                    )
                )
            )

        })

    }

    fun onButtonClicked(view: View) {
        var model = ViewModelProviders.of(this)[WeatherViewModel::class.java]
        val location = findViewById<TextView>(R.id.input_location_text).text.toString()
        model.getWeatherData(location)
    }
}
