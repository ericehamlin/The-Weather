package com.odiousenterprises.theweather

import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request


class WeatherApiClient {

    suspend fun sendRequest(): WeatherData {
        val apiKey = "c433b09ef235354c97b87ca12dde6d65"
        val url = "https://api.darksky.net/forecast/$apiKey/42.3601,-71.0589"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val weatherData = Gson().fromJson(response.body().string(), WeatherData::class.java)
        return weatherData
    }

}
