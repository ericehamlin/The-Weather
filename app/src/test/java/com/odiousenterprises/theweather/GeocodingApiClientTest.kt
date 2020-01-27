package com.odiousenterprises.theweather

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GeocodingApiClientTest {

    @Test(expected=RuntimeException::class)
    fun sendRequest_withInvalidLocation_throwsAnException() {
        runBlocking { GeocodingApiClient.sendRequest("Dagobah") }
    }
}
