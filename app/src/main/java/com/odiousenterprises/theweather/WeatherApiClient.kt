package com.odiousenterprises.theweather

import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request

// TODO cache
object WeatherApiClient {
    val API_KEY = BuildConfig.WEATHER_API_KEY
    val ENDPOINT = "https://api.darksky.net/forecast"

    suspend fun sendRequest(latitude: Float, longitude: Float): WeatherData {
        val url = "$ENDPOINT/$API_KEY/$latitude, $longitude"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val weatherData = Gson().fromJson(response.body().string(), WeatherData::class.java)
        return weatherData
    }

}
