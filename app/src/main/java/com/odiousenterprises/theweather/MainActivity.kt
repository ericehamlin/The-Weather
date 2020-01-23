package com.odiousenterprises.theweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var vm = ViewModelProviders.of(this)[WeatherViewModel::class.java]

    }

    fun onButtonClicked(view: View) {
        var vm = ViewModelProviders.of(this)[WeatherViewModel::class.java]
        val location = findViewById<TextView>(R.id.input_location_text).text.toString()
        vm.getWeatherData(location)
    }
}
