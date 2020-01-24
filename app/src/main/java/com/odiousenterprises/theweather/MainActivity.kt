package com.odiousenterprises.theweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.coroutines.CoroutineExceptionHandler

class MainActivity : AppCompatActivity() {

    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this)[WeatherViewModel::class.java]

        val mapFragment = MapFragment.newInstance()
        fragmentManager.beginTransaction()
            .add(R.id.map, mapFragment).commit()

        mapFragment.getMapAsync({map ->
            googleMap = map

            googleMap.setOnMapLoadedCallback {
                model.getCurrentGeocodingData().observe(this, Observer<GeocodingData> { geocodingData ->
                    googleMap.moveCamera(
                        CameraUpdateFactory.newLatLngBounds(
                            LatLngBounds(
                                LatLng(
                                    geocodingData.results[0].geometry.viewport.southwest.lat.toDouble(),
                                    geocodingData.results[0].geometry.viewport.southwest.lng.toDouble()
                                ),
                                LatLng(
                                    geocodingData.results[0].geometry.viewport.northeast.lat.toDouble(),
                                    geocodingData.results[0].geometry.viewport.northeast.lng.toDouble()
                                )
                            ),
                            0
                        )
                    )
                })
            }
        })


        model.getCurrentWeatherData().observe(this, Observer<WeatherData> { weatherData ->
            val currentWeather = weatherData.currently
            val DEGREE_SYMBOL = 176.toChar()
            findViewById<TextView>(R.id.display_temperature).text =
                currentWeather.temperature.toString() + DEGREE_SYMBOL + "F"
            findViewById<TextView>(R.id.display_summary).text = currentWeather.summary
            findViewById<TextView>(R.id.display_feels_like).text =
                "Feels like " + currentWeather.apparentTemperature.toString()  + DEGREE_SYMBOL + "F"
            findViewById<TextView>(R.id.humidity).text = currentWeather.humidity.toString()
            findViewById<TextView>(R.id.precipitation).text = currentWeather.precipProbability.toString()
            findViewById<TextView>(R.id.uv).text = currentWeather.uvIndex.toString() + " of 10"

            showData()

        })

    }

    fun showData() {
        findViewById<LinearLayout>(R.id.loading_view).visibility = View.GONE
        findViewById<LinearLayout>(R.id.weather_data_view).visibility = View.VISIBLE
        findViewById<LinearLayout>(R.id.error_view).visibility = View.GONE
    }

    fun showLoading() {
        findViewById<LinearLayout>(R.id.loading_view).visibility = View.VISIBLE
        findViewById<LinearLayout>(R.id.weather_data_view).visibility = View.GONE
        findViewById<LinearLayout>(R.id.error_view).visibility = View.GONE
    }

    fun showError() {
        findViewById<LinearLayout>(R.id.loading_view).visibility = View.GONE
        findViewById<LinearLayout>(R.id.weather_data_view).visibility = View.GONE
        findViewById<LinearLayout>(R.id.error_view).visibility = View.VISIBLE
    }

    fun onButtonClicked(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)

        var model = ViewModelProviders.of(this)[WeatherViewModel::class.java]
        val location = findViewById<TextView>(R.id.input_location_text).text.toString()
        showLoading()

        val handler = CoroutineExceptionHandler { _, exception ->
            this@MainActivity.runOnUiThread({
                showError()
            })

        }
        model.getWeatherData(location, handler)
    }
}
