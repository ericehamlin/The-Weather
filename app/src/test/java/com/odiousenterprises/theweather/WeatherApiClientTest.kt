package com.odiousenterprises.theweather

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WeatherApiClientTest {
    @Test
    fun sendRequest_withInvalidLatitude_doesSomethingBadNotSureWhatYet() {
        val result = runBlocking { WeatherApiClient.sendRequest(999f, 26f) }
        println(result)
    }
}
