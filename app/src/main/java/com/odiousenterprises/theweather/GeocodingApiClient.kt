package com.odiousenterprises.theweather

import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request

// TODO singleton, cache
class GeocodingApiClient {

    val ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/json"
    val API_KEY = "AIzaSyBnYhVeooAzlhJ-psVUsNyYwLAdlYwUb8A"

    suspend fun sendRequest(location:String): GeocodingData {
        val url = "$ENDPOINT?address=$location&key=$API_KEY"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val geocodingData = Gson().fromJson(response.body().string(), GeocodingData::class.java)
        return geocodingData
    }
}